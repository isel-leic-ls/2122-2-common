pipeline {
    agent any 
    stages {
        stage('Build') {
            steps {
                sh "chmod +x -R ./gradlew"
                sh './gradlew build'
            }
        }
        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }

    }
}
