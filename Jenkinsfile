pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    stages {
        stage ("Cloning the repo"){
            steps {
                git 'https://github.com/nikolasoricCT/document-management-software'
            }
        }
        stage ("Maven Build"){
            steps {
                sh 'mvn -f /var/lib/jenkins/workspace/logicaldoc/build/poms/pom.xml clean install'
            }
        }
        stage ("Test - leave for later"){
            steps {
                echo 'skip!'
            }
        }
        stage ('Build Docker image'){
            steps{
                script {
                    sh 'docker build -t nikolasoric/logicaldocimage:1.0 .'
                }
            }
        }
        stage ('Push Docker image'){
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerhubpw', variable: 'dockerhubpwd')]) {
                        sh 'docker push -u nikolasoric -p ${dockerhubpwd}'
                        sh 'docker push nikolasoric/logicaldocimage:1.0'
                    }
                }
            }
        }
    }
}
