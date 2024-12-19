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
            emailext(
                subject: "Build SUCCESSFUL",
                body: """<p>Good news! The build succeeded.</p>
                        <p>Status: SUCCESS</p>""",
                to: 'lailatimasli1@gmail.com'
            )
        }
        failure {
            emailext(
                subject: "Build FAILED",
                body: """<p>Unfortunately, the build has failed.</p>
                        <p>Status: FAILURE</p>""",
                to: 'lailatimasli1@gmail.com'
            )
        }
    }
}
