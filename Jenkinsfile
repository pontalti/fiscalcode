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
        container('dind'){
            sh 'docker version'
        }
      }
    }
  }
}