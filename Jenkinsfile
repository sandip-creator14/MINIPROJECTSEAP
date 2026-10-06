pipeline {
    agent any
    tools { maven 'Maven3.9.16'; jdk 'JDK25' }
    environment {
        IMAGE     = 'sandipkc14/shopping-app'
        CONTAINER = 'shopping-app'
    }

    stages {
        stage('Checkout') { steps { checkout scm } }

        stage('Build & Unit Tests') {
            steps { bat 'mvn -B clean package' }
            post { always { junit 'target/surefire-reports/*.xml' } }
        }

        stage('Archive WAR') {
            steps { archiveArtifacts artifacts: 'target/shopping-app.war', fingerprint: true }
        }

        stage('Docker Build') {
            steps { bat 'docker build -t %IMAGE%:%BUILD_NUMBER% -t %IMAGE%:latest .' }
        }

        stage('Deploy') {
            steps {
                bat 'docker rm -f %CONTAINER% || exit 0'
                bat 'docker run -d --name %CONTAINER% -p 8081:8080 %IMAGE%:latest'
                bat 'ping -n 16 127.0.0.1 > nul'
            }
        }

        stage('Selenium Tests') {
            steps { bat 'mvn -B test -Pselenium -Dtest="Selenium*Test"' }
            post { always { junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml' } }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds',
                                 usernameVariable: 'DH_USER', passwordVariable: 'DH_PASS')]) {
                    bat 'echo %DH_PASS%| docker login -u %DH_USER% --password-stdin'
                    bat 'docker push %IMAGE%:%BUILD_NUMBER%'
                    bat 'docker push %IMAGE%:latest'
                }
            }
        }
    }

    post {
        always {
            bat 'docker rm -f %CONTAINER% || exit 0'
            bat 'docker logout || exit 0'
        }
        failure { echo 'Pipeline failed - check the logs above.' }
    }
}
