#Microservice Demo APP

Develop, Test and Deploy a microservice to find standings of a team playing league football match using country name, league name and team name. The service should be accessible via web browser on internet and end user should be able to view results by changing previously listed parameters. Output of this service should be presented in web browser using any one of Javascript framework, HTML or JSON. And the service should be ready to be released to production or live environment. In output, display following:
Country ID & Name: (<ID>) - <name>
League ID & Name: (<ID>) - <name>
Team ID & Name: (<ID>) - <name>
Overall League Position: <position>



Test URL :- 
http://localhost:8100/api/v1/teams/standing?teamName=Bournemouth&countryName=England&leagueName=Championship


Resposne Format(JSON) :- 
{
Country ID & Name: "(41) - England",
League ID & Name: "(149) - Championship",
Team ID & Name: "(2615) - Bournemouth",
overallPosition: 6
}


CI/CD

1) Create a Jenkins pipeline job. 
2) Pipeline to be selected as JenkinsFile and Link of the gihub repo
3) Assumption is made that Jenkins and Docker host resides on same VM.
4) Pipeline with automatically spawn a container running on port 8100. In case of any conflict change the port in Jenkins file.
