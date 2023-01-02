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
              apt-get update
              apt-get install git -y
            tty: true
        '''
    }
  }
  stages {
    stage('Run maven') {
      steps {
        container('maven') {
        	sh 'mvn clean package'
        }
      }
    }
  }
}