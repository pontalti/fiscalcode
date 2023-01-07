pipeline {
  environment {
  	DOCKERHUB_CREDENTIALS=credentials('Docker-user')
  }
  stages {
	node('jenkins-slave') {
    stage('Maven build and package') {
      steps {
        
        	sh 'mvn clean package -DskipTests'
        	sh 'pwd'
      }
    }
    stage('Docker'){
      steps {
  			script{
  				sh 'docker version'
	  			//docker.build("pontalti/fiscalcode:latest")
  			}
      }
    }
    }
  }
}