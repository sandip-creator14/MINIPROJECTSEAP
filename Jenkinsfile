pipeline {
    agent any
    tools { maven 'Maven3.9.16'; jdk 'JDK25' }
    environment { IMAGE = 'shopping-app'; CONTAINER = 'shopping-app' }

    stages {
        stage('Checkout') { steps { checkout scm } }

        stage('Build & Unit Tests') {
            steps { bat 'mvn -B clean package' }
            post { always { junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml' } }
        }

        stage('Archive WAR') {
            steps { archiveArtifacts artifacts: 'target/shopping-app.war', fingerprint: true }
        }

        stage('Docker Build') {
            steps { bat 'docker build -t %IMAGE%:%BUILD_NUMBER% -t %IMAGE%:latest .' }
        }

        stage('Deploy') {
    steps {
        bat 'docker rm -f shopping-app || exit 0'
        bat 'docker run -d -p 8080:8080 --name shopping-app shopping-app:5'
    }
}

        stage('Selenium Tests') {
            steps { bat 'mvn -B test -Pselenium -Dtest="Selenium*Test"' }
            post { always { junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml' } }
        }
    }
    post { failure { echo 'Pipeline failed - check the logs above.' } }
}
