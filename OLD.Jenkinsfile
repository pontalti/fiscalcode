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
  stages {

    stage('Checkout'){
      steps{
        container('devops'){
          git branch: 'main', poll: false, url: 'https://github.com/pontalti/fiscalcode.git'
        }
      }
    }

    stage('Maven compile'){
      steps{
        container('devops'){
          script{
            sh 'mvn compile'
          }
        }
      }
    }
    stage('Maven Junit'){
      steps{
        container('devops'){
          script{
            sh 'mvn test'
          }
        }
      }
    }
    stage('Maven build and package') {
      steps {
        container('devops') {
          script{
            sh 'mvn clean package -DskipTests'
          }
        }
      }
    }       
    /*
    stage('Docker'){
      steps{
        container('dind'){
          withCredentials([usernamePassword(credentialsId: 'docker_id', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
            sh "docker login -u ${dockerHubUser} -p ${dockerHubPassword}"
            sh 'docker build -f Dockerfile . -t pontalti/fiscalcode:latest'
            sh 'docker push pontalti/fiscalcode:latest'
          }
        }
      }
    }
    */
  }
}