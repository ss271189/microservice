pipeline {
  agent any
 
  tools {
        maven 'Maven3'
    }
	
   options
        {   
		// append time stamp to the console
            timestamps()

            timeout(time: 1, unit: 'HOURS')

            // Done not checkout automatically the scm on every stage we stash what
            // we need to save time
            skipDefaultCheckout()

            //dicard old build after 10 days or 30 build count
            buildDiscarder(logRotator(daysToKeepStr: '10', numToKeepStr: '10'))

            // to avoid concurrent build to avoid multiple checkouts
            disableConcurrentBuilds()
        }
	

  stages {
    stage ('Start') {
      steps {
        echo '@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@hello! I am in '+env.BRANCH_NAME+' environment@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@'
        echo '************************Checking out the project from the SCM.**************************************'
        checkout scm
       
      }
    }
    
    stage('Build') {
        agent any
        steps {
        echo "****************************** Building the project using mvn ************************************"
		bat 'mvn clean package install -DskipTests'
            }
        }
        
    stage('Test') {
           
            steps {
                echo "*********************** Executing testcases *********************************************"
				bat 'mvn test'
            }
        } 
        
     stage ('Sonar Analysis') {
        	agent any
			steps {
				echo "*************** Executing Sonar analysis ***************"
				withSonarQubeEnv('Test_Sonar'){
				    bat "mvn sonar:sonar"
				}
			}
		}

    
    stage ('Upload to Artifactory') {
      steps {
        echo '****************************** Uploading  jar/war/ear Artifactory ***************************************'
			rtMavenDeployer (
                    id: 'deployer',
                    serverId: '123456789@artifactory',
                    releaseRepo: 'CI-Automation-JAVA',
                    snapshotRepo: 'CI-Automation-JAVA'
                )
                rtMavenRun (
                    pom: 'pom.xml',
                    goals: 'clean install -DskipTests',
                    deployerId: 'deployer',
                )
                rtPublishBuildInfo (
                    serverId: '123456789@artifactory',
                )

      }
    }
    
    stage('Docker Image'){
            steps{
			 echo '****************************** Building Docker Image ***************************************'
             bat 'docker build -t dtr.nagarro.com:443/i_shashanksaurabh_%BRANCH_NAME%:%BUILD_NUMBER% --no-cache -f Dockerfile .'
            }
        }
    stage('Containers') {
            parallel {
                stage('PUSH to DTR') {
                    steps {
                         echo '****************************** Pushing Docker Image to DTR ***************************************'
                        bat 'docker push dtr.nagarro.com:443/i_shashanksaurabh_%BRANCH_NAME%:%BUILD_NUMBER%'
                    }
                }
                 stage('Pre container check') {
                    steps {
                         echo '****************************** Pre Container check ***************************************'
                         script {
                         
                         containerID = powershell(returnStdout: true, script:'docker ps -af name=c_shashank_container_develop --format "{{.ID}}"')
                         if(containerID)
				         {
                        bat "docker stop ${containerID}"
					    bat "docker rm -f ${containerID}"
				         }

                        }  


                        
                    }
                }
    
            }
    }
     stage('Docker Deployment'){
             steps{
			 echo '****************************** Deploying  Docker Image ***************************************'
             bat 'docker container run --name c_shashank_container_%BRANCH_NAME% -d -p 6200:8080 dtr.nagarro.com:443/i_shashanksaurabh_%BRANCH_NAME%:%BUILD_NUMBER%'
            }
        }
		
	stage ('Helm Chart Deployment') {
      steps {
        
             echo '****************************** Deploying  Helm Charts ***************************************'              
			 bat 'helm install shashanksaurabh-nagp-develop shashanksaurabh -n default --set repository=dtr.nagarro.com:443/i_shashanksaurabh_%BRANCH_NAME% --set tag=%BUILD_NUMBER%'
      }
    }
	
        
        
  }
}