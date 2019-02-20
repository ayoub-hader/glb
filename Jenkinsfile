pipeline {
    agent any
    tools {
        maven 'Maven 3.6.0'
        jdk 'Java 11'
    }
    stages {
	stage('Cheackout project') {
	    steps {
		checkout([$class: 'GitSCM', branches: [[name: '*/deploy']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'git.neoxia', url: 'https://git.neoxia-maroc.net/h.ayoub/groupe-ldap-back']]])
	    }
	}
    
	stage('Build project') {
	    steps {
		sh "mvn -s settings.xml clean install -DskipTests"
	    }
	}
    }
}

