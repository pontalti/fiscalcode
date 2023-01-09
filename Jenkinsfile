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
          - name: docker
            image: pontalti/docker:latest
            command:
            - cat
            tty: true
        '''
    }
  }
  stages {
    stage('Maven build and package') {
      steps {
        container('devops') {
          script{
            sh 'mvn clean package -DskipTests'
            sh 'pwd'
          }
        }
      }
    }
    stage('docker'){
      steps{
        container('docker'){
          script{
            sh 'docker version'
          }
        }
      }
    }
  }
}