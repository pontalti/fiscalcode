pipeline {
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
    stage('Docker stage'){
      steps{
        container('dind'){
          script{
            withCredentials([usernamePassword(credentialsId: 'Docker-user', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
              //sh "docker login -u ${dockerHubUser} -p ${dockerHubPassword}"
              sh 'docker version'
            }
          }
        }
      }
    }
  }
}