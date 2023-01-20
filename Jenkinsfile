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
  - name: dind
    image: docker:dind
    securityContext:
      privileged: true
    volumeMounts:
      - mountPath: /var/run/docker.sock
        name: docker-socket-volume
  volumes:
    - name: docker-socket-volume
      hostPath:
        path: /var/run/docker.sock
        type: File
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