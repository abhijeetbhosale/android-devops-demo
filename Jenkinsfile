try {
	echo "EXECUTING jenkinsfile"

	
	node{
			def workspace = env.WORKSPACE
			def sparseDir = 'android-devops-demo/'
		stage('scm') {        
			getGitLatest(workspace)   
		}
	    stage('build-android-sdk-check') {
			androidSDKCheck()
		}
		stage('build-clean-workspace') {
          	   cleanWs(workspace,sparseDir)
   		}
   		stage('build-android-lint') {
               androidLint()
         }
        stage('build-publish-android-lint-result'){
             publishHTML([allowMissing: false,
              alwaysLinkToLastBuild: false,
              keepAll: false,
              reportDir: 'app/build/reports/',
              reportFiles: 'lint-results*.html',
              reportName: 'Lint Report',
              reportTitles: ''
              ])
        }
        stage('build-android-unit-test') {
               androidUnitTest()
        }
        stage('build-publish-android-junit-result'){
            publishHTML([allowMissing: false,
                  alwaysLinkToLastBuild: false,
                  keepAll: false,
                  reportDir: 'app/build/reports/',
                  reportFiles: 'index*.html',
                  reportName: 'JUnit Test Report',
                  reportTitles: ''
                  ])

        }
        stage('build-assemble-debug-apk'){
        assembleApk()
        }
        stage('build-upload-build-on-hockeyapp'){
        uploadBuildOnHockeyApp()
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


def gradleSonar(){
	echo "Executing sonar quality"
	withSonarQubeEnv("awsSonarQube") {
		sh ' chmod a+x gradlew '
        sh ' ./gradlew clean sonarqube '
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


def assembleApk(){
	echo "Executing assemble apk"
		sh ' chmod a+x gradlew '
        sh ' ./gradlew assembleDebug '

}


def uploadBuildOnHockeyApp(){
echo "executing upload build on HockeyApp"
step([$class: 'HockeyappRecorder',
      applications: [[apiToken: 'ca5dde39e9e84b0c9a44c4c4e1ad529e', downloadAllowed: true,
                      filePath: 'app/build/outputs/apk/*/*.apk', mandatory: false, notifyTeam: true,
                      releaseNotesMethod: [$class: 'ChangelogReleaseNotes'],
                      uploadMethod: [$class: 'AppCreation', publicPage: false]]],
      debugMode: false, failGracefully: false])
}

   