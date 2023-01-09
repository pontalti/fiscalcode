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
          - name: dind
            image: docker:18.05-dind
            securityContext:
              privileged: true
            volumeMounts:
              - name: dind-storage
                mountPath: /var/lib/docker
          volumes:
            - name: dind-storage
              emptyDir: {}
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
            sh 'mvn compile test'
          }
        }
      }
    }
    stage('Maven build and package') {
      steps {
        container('devops') {
          script{
            sh 'mvn clean compile package -DskipTests'
          }
        }
      }
    }
    stage('Docker'){
      steps{
        container('dind'){
          script{
            sh 'pwd'
            sh 'docker version'
          }
        }
      }
    }
  }
}