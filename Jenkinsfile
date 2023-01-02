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
  stages {
    stage('Run maven') {
      steps {
        container('maven') {
        	/**
        	sh 'apt-get update'
        	sh 'apt-get install git -y'
        	sh 'mvn clean package'
        	*/
        	sh 'pwd'
        }
      }
    }
    stage('Docker registry'){
      steps {
      	container('docker'){
      		sh 'apt-get update'
      		sh 'apt-get install service -y'
      		sh 'service docker start'
			sh 'docker version'
		}
      }
    }
  }
}