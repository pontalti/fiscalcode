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
        container('devops') {
        	sh 'mvn clean package -DskipTests'
        	sh 'pwd'
        }
      }
    }
    stage('Docker'){
      steps {
      	container('docker'){
  			script{
  				sh 'docker version'
	  			//docker.build("pontalti/fiscalcode:latest")
  			}
      	}
      }
    }
  }
}