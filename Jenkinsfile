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
            image: pontalti/docker:latest
            command:
            - cat
            tty: true
        '''
    }
  }
  environment {
  	DOCKERHUB_CREDENTIALS=credentials('Docker-user')
  }
  stages {
    stage('Maven build and package') {
      steps {
        container('docker') {
        	sh 'mvn clean package -DskipTests'
        	sh 'pwd'
        	sh 'docker version'
        }
      }
    }
  }
}