pipeline {
    agent any
   tools { maven 'Maven3.9.16'; jdk 'JDK25' }   // configure these names in Jenkins > Global Tool Configuration
    environment { IMAGE = 'shopping-app'; CONTAINER = 'shopping-app' }

    stages {
        stage('Checkout') { steps { checkout scm } }
        stage('Build & Unit Tests') {
            steps { sh 'mvn -B clean package' }
            post { always { junit 'target/surefire-reports/*.xml' } }
        }
        stage('Archive WAR') { steps { archiveArtifacts artifacts: 'target/shopping-app.war', fingerprint: true } }
        stage('Docker Build') { steps { sh 'docker build -t $IMAGE:${BUILD_NUMBER} -t $IMAGE:latest .' } }
        stage('Deploy') {
            steps {
                sh 'docker rm -f $CONTAINER || true'
                sh 'docker run -d --name $CONTAINER -p 8080:8080 $IMAGE:latest'
                sh 'sleep 15'
            }
        }
        stage('Selenium Tests') {
            steps { sh 'mvn -B test -Pselenium -Dtest="Selenium*Test"' }
            post { always { junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml' } }
        }
    }
    post { failure { echo 'Pipeline failed - check the logs above.' } }
}
