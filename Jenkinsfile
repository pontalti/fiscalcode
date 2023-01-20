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
    stage('Maven compile'){
      steps{
        container('dind'){
            sh 'docker version'
        }
      }
    }
  }
}