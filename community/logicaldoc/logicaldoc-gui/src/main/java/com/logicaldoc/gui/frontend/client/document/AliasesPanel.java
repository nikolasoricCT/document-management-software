package com.logicaldoc.gui.frontend.client.document;

import com.logicaldoc.gui.common.client.beans.GUIDocument;
import com.logicaldoc.gui.common.client.data.DocumentAliasesDS;
import com.logicaldoc.gui.common.client.i18n.I18N;
import com.logicaldoc.gui.common.client.widgets.grid.FileNameListGridField;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * This panel shows the aliases of a folder
 * 
 * @author Marco Meschieri - LogicalDOC
 * @since 7.5
 */
public class AliasesPanel extends DocumentDetailTab {

	private DocumentAliasesDS dataSource;

	private ListGrid listGrid;

	public AliasesPanel(final GUIDocument document) {
		super(document, null);
	}

	@Override
	protected void onDraw() {
		ListGridField id = new ListGridField("id", I18N.message("id"), 50);
		id.setHidden(true);

		ListGridField folderId = new ListGridField("folderId", I18N.message("id"), 50);
		folderId.setHidden(true);

		FileNameListGridField filename = new FileNameListGridField();
		ListGridField path = new ListGridField("path", I18N.message("path"));

		listGrid = new ListGrid();
		listGrid.setEmptyMessage(I18N.message("notitemstoshow"));
		listGrid.setCanFreezeFields(true);
		listGrid.setAutoFetchData(true);
		dataSource = new DocumentAliasesDS(document.getId());
		listGrid.setDataSource(dataSource);
		listGrid.setFields(id, filename, path, folderId);
		addMember(listGrid);

		listGrid.addDoubleClickHandler(new DoubleClickHandler() {

			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				DocumentsPanel.get().openInFolder(listGrid.getSelectedRecord().getAttributeAsLong("folderId"),
						listGrid.getSelectedRecord().getAttributeAsLong("id"));
			}
		});
	}

	@Override
	public void destroy() {
		super.destroy();
		if (dataSource != null)
			dataSource.destroy();
	}
}