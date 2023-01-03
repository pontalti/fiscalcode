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
            image: docker:23.0.0-rc.1-alpine3.17
            command:
            - cat
            tty: true
        '''
    }
  }
/*
	environment {
		DOCKERHUB_CREDENTIALS=credentials('Docker-user')
	}
*/
  stages {
    stage('Run maven') {
      steps {
        container('devops') {
        	sh 'mvn clean package -DskipTests'
        	sh 'pwd'
        }
      }
    }
    /*
    stage('Docker registry'){
      steps {
      	container('docker'){
  			script{
	  			docker.build("pontalti/fiscalcode:latest")
  			}
      	}
      }
    }
    */
  }
}