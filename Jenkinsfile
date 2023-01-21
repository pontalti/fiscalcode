pipeline {
  agent {
    kubernetes {
      yaml '''
        apiVersion: v1
        kind: Pod
        spec:
          containers:
          - name: devops
            image: pontalti/devops:0.1
            command:
            - cat
            tty: true
        '''
    }
  }
  stages {

    stage('Maven compile'){
      steps{
        container('devops'){
          script{
            sh 'mvn compile'
          }
        }
      }
    }

    stage('Maven Junit'){
      steps{
        container('devops'){
          script{
            sh 'mvn test'
          }
        }
      }
    }

    stage('Maven build and package') {
      steps {
        container('devops') {
          script{
            sh 'mvn clean package -DskipTests'
          }
        }
      }
    }

  }
}