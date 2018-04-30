try {
	echo "EXECUTING jenkinsfile"

	
	node{
			def workspace = env.WORKSPACE
			def sparseDir = 'android-devops-demo/'
		stage('scm') {        
			getGitLatest(workspace)   
		}
	    stage('android-sdk-check') {
			androidSDKCheck()
		}
		stage('clean-workspace') {
          	   cleanWs(workspace,sparseDir)
   		}
   		stage('android-lint') {
               androidLint()
         }
        stage('publish-android-lint-result'){
               step([$class: 'HtmlPublisher', pattern: 'app/build/reports/lint-results*.html'])
        }
        stage('android-unit-test') {
               androidUnitTest()
        }
        stage('publish-android-junit-result'){
        step([$class: 'JUnitResultArchiver', testResults: 'app/build/test-results/*/TEST-*.xml'])
        }



	}
}
catch (e){
	echo 'EXCEPTION OCCURED'
    echo "${e}"
	error " Error during execution of pipeline"
}

def androidSDKCheck()
  {
    def currentPath = pwd()
    echo currentPath
    env.ANDROID_HOME = env.JENKINS_HOME + '/AndroidSDK'
    echo env.ANDROID_HOME
    echo 'Checking AndroidSDK...'

    if(fileExists(env.ANDROID_HOME)) {
            echo 'AndroidSDK Found'

    } else {
            echo 'AndroidSDK NOT Found'
            sh ' curl --fail --output androidsdk.zip https://dl.google.com/android/repository/sdk-tools-linux-3859397.zip '
            sh ' unzip androidsdk.zip -d AndroidSDK'
            sh ' mv AndroidSDK $ANDROID_HOME '
            sh ' mkdir -p $ANDROID_HOME/licenses '
            sh 'echo "\\nd56f5187479451eabf01fb78af6dfcb131a6481e" > "$ANDROID_HOME/licenses/android-sdk-license"'
        }

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


def androidLint(){
	echo "executing Android lint"
	sh ' chmod a+x gradlew '
    sh ' ./gradlew lint '
    echo "Android lint finished"
}


def androidUnitTest(){
	echo "executing Android unit test"
	sh ' chmod a+x gradlew '
    sh ' ./gradlew test '
    echo "Android unit test task finished"
}




   