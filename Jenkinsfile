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
  containers:
  - name: dind
    image: docker:dind
    tty: true
    securityContext:
      privileged: true
'''
    }
  }
  stages {
    stage('Docker: Building image'){
    agent any
      steps{
        script{
          sh 'echo "Buildingggggg" '
          //dockerImage = docker.build registry + ":$BUILD_NUMBER"
        }        
      }
    }
    stage('Docker: Deploy image') {
      agent any 
      steps { 
        script {
          sh 'echo "deployyyyyyyy" '
          /* 
          docker.withRegistry( '', registryCredential ) { 
              dockerImage.push() 
          }*/
        } 
      }
    } 
  }
}