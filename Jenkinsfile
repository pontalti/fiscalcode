
	node('jenkins-slave') {
	    
	     stage('unit-tests') {
	        sh(script: """
	            docker run --rm alpine /bin/sh -c "echo hello world"
	        """)
	    }
	}
