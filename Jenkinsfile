pipeline {
    triggers {
        pollSCM '* * * * *'
    }
    agent any
    stages {
        stage('build') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }
        stage('test') {
            steps {
                sh 'chmod 777 ./mvnw'
                sh './mvnw clean test'
            }
        }
    }
}