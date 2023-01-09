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
            securityContext:
              privilege: true
          - name: docker
            image: docker:20.10.22-dind-alpine3.17
            command:
            - cat
            tty: true
            securityContext:
              privilege: true
        '''
    }
  }
  stages {
    stage('Maven build and package') {
      steps {
        container('devops') {
          script{
            sh 'mvn clean package -DskipTests'
          }
        }
      }
    }
    stage('Docker'){
      steps{
        container('devops'){
          script{
            sh 'pwd'
          }
        }
      }
    }
  }
}