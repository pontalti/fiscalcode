pipeline {
  agent {
    kubernetes {
      yaml '''
        apiVersion: v1
        kind: Pod
        spec:
          containers:
          - name: devops
            image: maven:3.8.4-openjdk-17-slim
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
    /*      
    stage('Docker'){
      steps{
        container('dind'){
          withCredentials([usernamePassword(credentialsId: 'docker_id', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
            //sh "docker login -u ${dockerHubUser} -p ${dockerHubPassword}"
            sh 'docker build -f Dockerfile . -t pontalti/fiscalcode:latest'
            sh 'docker push pontalti/fiscalcode:latest'
          }
        }
      }
    }*/
  }
}