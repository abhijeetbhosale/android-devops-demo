try {
	echo "EXECUTING jenkinsfile"
	notifyMessengers()
	
	node{
			def workspace = env.WORKSPACE
			def sparseDir = 'android-devops-demo/'
	  
		stage('clean-workspace') {
			cleanWs(workspace,sparseDir)
		}
		
		stage('scm') {        
			getGitLatest(workspace)   
		}
		  


	}//of node
}
catch (e){
	echo 'EXCEPTION OCCURED'
    echo "${e}"
	error " Error during execution of pipeline"
}

def cleanWs(workspace,sparseDir) {

		echo "gradle clean started"
	echo "current working directory is :  echo pwd" 
	echo pwd
	
	echo "current working directory is :  sh pwd" 
	sh "pwd"
	

	sh ' chmod a+x gradlew '
    sh ' ./gradlew clean '
	echo "gradle clean finished"
}

def getGitLatest(workspace){

    echo "Git fetching new changes started"
	echo "workspace directory is ${workspace}"  

	
	def config = [:]
	
	config.serviceRepo = 'https://github.com/abhijeetbhosale/android-devops-demo.git'
	config.sparseCheckout= 'android-devops-demo'
	config.gitBranch = 'master' 
	
	echo "check out branch ${config.gitBranch} from ${config.serviceRepo}"                       
	git branch: config.gitBranch, url: config.serviceRepo 
}

def mvnBuild(workspace,sparseDir){
      echo "mvn-build"
      echo "workspace directory is ${workspace}"
      echo "${sparseDir}"
      echo '${workspace}/${sparseDir}'
      sh "cd ${workspace}/${sparseDir}"      
      echo "current working directory is : " 
      sh "pwd"
      sh "mvn -f  ${workspace}/${sparseDir}/pom.xml clean compile package"
}
      
def mvnSonar(workspace,sparseDir){
	echo "executing mvn sonar:sonar"
	withSonarQubeEnv("awsSonarQube") {
		sh "mvn -f  ${workspace}/${sparseDir}/pom.xml sonar:sonar"// running locally
    }
}
      
def sonarQualityGate1(){
	echo "executing Quality-Gate1"
	
	timeout(time: 1, unit: 'HOURS') { // Just in case something goes wrong, pipeline will be killed after a timeout
    def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
    if (qg.status != 'OK') {
		error "Pipeline aborted due to quality gate failure: ${qg.status}"
     }
	}
 }
   
def pcfPUSH1(workspace,sparseDir){
     echo 'pcf-push' 
     sh "cd ${workspace}/${sparseDir}/"
     sh "cf login -a https://api.run.pivotal.io -o  anusheel -s development -u bhupesh.rathod@altimetrik.com -p Alti@123 "
     sh "cf push -f ${workspace}/${sparseDir}/manifest.yml -t 179" 
 }
 
def executeJmeter(workspace,sparseDir){
	echo 'executeJmeter' 
	sh "cd ${workspace}/${sparseDir}/src/test/jmeter"
	sh "pwd"
	sh "ls -l"
	
	sh "/var/lib/apache-jmeter-4.0/bin/jmeter  -n -t ${workspace}/${sparseDir}/src/test/jmeter/Jmeter101_AssertionPass.jmx > 101-failed.csv"
}

def jmeterQualityGate2(){
	sh "ls -l"
	//perfReport percentiles: '0,50,90,100', sourceDataFiles: ''
	//build job: 'JMeter - Freestyle'
	//def errorCode = ''
	
	errorCode = sh  """ awk ' { if (\$14 == "Err:" && \$15 !=0 ) {print "ERROR"; exit 1;} }' 101-failed.csv"""
	
	//sh "$errorCode"
 }
 
 def executefunctionlTest(workspace,sparseDir){
	echo 'executefunctionlTest' 
	sh "mvn -f  ${workspace}/${sparseDir}/pom.xml  test -Dtest=**/FT*Tests"// running locally
	
 }
 
def notifyMessengers(String buildStatus = 'STARTED') {
    // Build status of null means successful.
    buildStatus = buildStatus ?: 'SUCCESS'
    
	// Replace encoded slashes.
    def decodedJobName = env.JOB_NAME.replaceAll("%2F", "/")
    def colorSlack

    if (buildStatus == 'STARTED') {
        colorSlack = '#D4DADF'
    } else if (buildStatus == 'SUCCESS') {
        colorSlack = '#BDFFC3'
    } else if (buildStatus == 'UNSTABLE') {
        colorSlack = '#FFFE89'
    } else {
        colorSlack = '#FF9FA1'
    }

    def msgSlack = "${buildStatus}: `${decodedJobName}` #${env.BUILD_NUMBER}: ${env.BUILD_URL}"
    slackSend(color: colorSlack, message: msgSlack)  
}

   