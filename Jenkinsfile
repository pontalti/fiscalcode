pipeline {
  environment { 
    registry = "pontalti/fiscalcode" 
    registryCredential = 'Docker-user' 
    dockerImage = '' 
  }
  agent {
    kubernetes {
      yaml '''
apiVersion: v1
kind: Pod
spec:
  #volumes:
  #  - name: docker-pv-storage
  #    hostPath:
  #      path: /var/run/docker.sock
  #      type: Directory
  containers:
  - name: devops
    image: pontalti/devops:0.1
    command:
    - cat
    tty: true
  #- name: docker
  #  image: docker:dind
  #  tty: true
  #  securityContext:
  #    privileged: true
  #  #volumeMounts:
  #  #- mountPath: /var/run/docker.sock
  #  #  name: docker-pv-storage
'''
    }
  }
  stages {
/*
    stage('maven: Build'){
      steps{
        container('devops'){
          script{
            sh 'mvn clean compile package -DskipTests'
          }
        }
      }
    }
*/
  
    stage('Docker: Building image'){
      node('slave') {
        steps{
            sh(script: """
              docker run --rm alpine /bin/sh -c "echo hello world"
            """)
        }
      }
    }
  

/*
    stage('Docker: Building image'){
      steps{
        container('alpine'){
          script{
            dockerImage = docker.build registry + ":$BUILD_NUMBER"
          }
        }
      }
    }

    stage('Docker: Deploy image') {
      steps {
        container('alpine'){
          script {
            docker.withRegistry( '', registryCredential ) { 
                dockerImage.push() 
            }
          }
        }
      }
    }
*/
  }
}