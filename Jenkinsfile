pipeline {
    agent any
    environment {
        MAVEN_HOME = tool 'Maven'
    }
    stages {
        stage('Cleanup') {
            steps {
                deleteDir()
            }
        }
        stage('Checkout') {
            steps {
                git url: 'https://github.com/ll-laila/GestionBibliotheque.git', branch: 'main'
            }
        }
        stage('Build') {
            steps {
                bat "${MAVEN_HOME}\\bin\\mvn clean compile"
            }
        }
        stage('Test') {
            steps {
                bat "${MAVEN_HOME}\\bin\\mvn test"
            }
        }
        stage('Quality Analysis') {
            steps {
                withSonarQubeEnv('SonarQube_server') {
                    bat "${MAVEN_HOME}\\bin\\mvn sonar:sonar"
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Déploiement simulé réussi'
            }
        }
    }

   post {
        success {
             echo 'Build SUCCESSFUL'
             slackSend (
                   channel: '#test',
                   message: "*${currentBuild.currentResult}:* Job ${env.JOB_NAME}"
           )
        }
        failure {
            echo  "Build FAILED"
            slackSend (
                   channel: '#test',
                   message: "*${currentBuild.currentResult}:* Job ${env.JOB_NAME}"
            )    
        }
    }

}
