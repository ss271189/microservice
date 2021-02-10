pipeline {
    agent any
	
	tools {
        maven 'maven3'
    }

    stages {
        stage ('Compile Stage') {

            steps {
			    echo '****************************** Compiling ***************************************'
                bat 'mvn clean package install -DskipTests'
                }
            }
        }

        stage ('Testing Stage') {

            steps {
			    echo '****************************** Testing ***************************************'
                withMaven(maven : 'maven3') {
                    bat 'mvn test'
                }
            }
        }


        stage('Docker Image'){
            steps{
			 echo '****************************** Building Docker Image ***************************************'
             bat 'docker build -t i_football_league --no-cache -f Dockerfile .'
            }
        }
		
	    stage('Docker Deployment'){
             steps{
			 echo '****************************** Deploying  Docker Image ***************************************'
             bat 'docker container run --name c_football_league -d -p 8088:8088 i_football_league'
            }
        }	
    }
}
