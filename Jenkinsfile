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
  environment {
  	DOCKERHUB_CREDENTIALS=credentials('Docker-user')
  	imageName = 'pontalti/fiscalcode'
  	registryCredential = 'Docker-user'
    dockerImage = ''
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
      	container('devops') {
			script{
				sh 'docker version'
				/**
	  			dockerImage = docker.build imageName
				docker.withRegistry( '', DOCKERHUB_CREDENTIALS ) {
	        		dockerImage.push("$BUILD_NUMBER")
	         		dockerImage.push('latest')
	      		}
	      		*/
      		}
		}
      }
    }
  }
}