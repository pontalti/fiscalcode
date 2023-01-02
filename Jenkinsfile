pipeline {
  agent {
    kubernetes {
      yaml '''
        apiVersion: v1
        kind: Pod
        spec:
          containers:
          - name: maven
            image: maven:3.8.4-openjdk-17-slim
            command:
            - cat
            tty: true
          - name: docker
            image: docker:rc-git
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
    stage('Run maven') {
      steps {
        container('maven') {
        	sh 'apt-get update'
        	sh 'apt-get install git -y'
        	sh 'mvn clean package'
        	sh 'pwd'
        }
      }
    }
    stage('Docker registry'){
      steps {    		
	  		cmd 'docker build -t pontalti/fiscalcode:latest .'
	  		//sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
	  		//sh 'docker push pontalti/fiscalcode:latest'
      }
    }
  }
}