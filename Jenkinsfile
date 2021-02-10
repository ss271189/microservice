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

        stage ('Testing Stage') {

            steps {
	            echo '****************************** Testing ***************************************'
               
                    bat 'mvn test'
                
            }
        }


        stage('Docker Image'){
            steps{
             echo '****************************** Building Docker Image ***************************************'
             bat 'docker build -t i_football_league --no-cache -f Dockerfile .'
            }
        }
	    stage('Pre container check') {
                    steps {
                         echo '****************************** Pre Container check ***************************************'
                         script {
                         
                         containerID = powershell(returnStdout: true, script:'docker ps -af name=c_football_league --format "{{.ID}}"')
                         if(containerID)
				         {
                        bat "docker stop ${containerID}"
					    bat "docker rm -f ${containerID}"
				         }

                        }  


                        
                    }
                }
		
	    stage('Docker Deployment'){
             steps{
			 echo '****************************** Deploying  Docker Image ***************************************'
             bat 'docker container run --name c_football_league -d -p 8085:8085 i_football_league'
            }
        }	
    }
}
