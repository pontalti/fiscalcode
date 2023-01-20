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
  - name: devops
    image: pontalti/devops:0.1
    command:
    - cat
    tty: true
  - name: dind
    image: docker:dind
    tty: true
    securityContext:
      privileged: true
'''
    }
  }
  stages {

    stage('maven: Build'){
      steps{
        container('devops'){
          script{
            sh 'mvn clean compile package -DskipTests'
          }
        }
      }
    }

    stage('Docker: Building image'){
      steps{
        container('devops'){
          script{
            sh 'echo "Buildingggggg" '
            dockerImage = docker.build registry + ":$BUILD_NUMBER"
          }
        }
      }
    }

    stage('Docker: Deploy image') {
      steps {
        container('devops'){
          script {
            sh 'echo "deployyyyyyyy" '
            docker.withRegistry( '', registryCredential ) { 
                dockerImage.push() 
            }
          }
        }
      }
    }

  }
}