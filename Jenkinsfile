pipeline {
  agent {
    kubernetes {
      yaml '''
        apiVersion: v1
        kind: Pod
        spec:
          containers:
          - name: devops
            image: maven:3.8.4-openjdk-17-slim
            command:
            - cat
            tty: true
        '''
    }
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
  }
}