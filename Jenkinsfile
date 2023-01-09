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
            securityContext:
              privilege: true
          - name: docker
            image: docker:20.10.22-dind-alpine3.17
            command:
            - cat
            tty: true
            securityContext:
              privilege: true
          - name: dind
            image: docker:18.05-dind
            securityContext:
              privileged: true
            volumeMounts:
              - name: dind-storage
                mountPath: /var/lib/docker
          volumes:
            - name: dind-storage
              emptyDir: {}
        '''
    }
/**

containers:
  - name: my-main-container
    # ...
    # other container config here
    # ...
    env:
    - name: DOCKER_HOST
      value: tcp://localhost:2375
  - name: dind
    image: docker:18.05-dind
    securityContext:
      privileged: true
    volumeMounts:
      - name: dind-storage
        mountPath: /var/lib/docker
volumes:
  - name: dind-storage
    emptyDir: {}

*/

  }
  stages {
    /*
    stage('Maven build and package') {
      steps {
        container('devops') {
          script{
            sh 'mvn clean package -DskipTests'
          }
        }
      }
    }
    */
    stage('Docker'){
      steps{
        container('docker'){
          script{
            sh 'pwd'
          }
        }
      }
    }
  }
}