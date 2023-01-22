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
            image: docker:latest
            command:
            - cat
            tty: true
            securityContext:
              privileged: true
            volumeMounts:
             - mountPath: /var/run/docker.sock
               name: docker-sock
          volumes:
          - name: docker-sock
            hostPath:
              path: /var/run/docker.sock
        '''
    }
  }
  stages {

    stage('Maven compile'){
      steps{
        container('devops'){
          script{
            sh 'mvn compile -DskipTests'
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
   
    stage('Docker login'){
      steps{
        container('docker'){
            withCredentials([usernamePassword(credentialsId: 'Docker-user', passwordVariable: 'password', usernameVariable: 'username')]) {
              sh "docker login -u ${username} -p ${password}"
            }
        }
      }
    }

    stage('Docker build'){
      steps{
        container('docker'){
          sh 'docker build -t pontalti/fiscalcode:latest .'
        }
      }
    }

    stage('Docker push'){
      steps{
        container('docker'){
          sh 'docker push pontalti/fiscalcode:latest'
        }
      }
    }
    
    stage('Docker logout'){
      steps{
        container('docker'){
          sh 'docker logout'
        }
      }
    }

  }
}
