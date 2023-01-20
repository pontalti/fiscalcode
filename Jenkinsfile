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
'''
    }
  }
  stages {
    stage('Maven compile'){
      steps{
        container('devops'){
            sh 'mvn compile'
        }
      }
    }
  }
}