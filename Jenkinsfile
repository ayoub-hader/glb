pipeline {
    agent any
    tools {
        maven 'Maven 3.6.0'
        jdk 'Java 11'
        nodejs 'nodejs-10.15.1'
    }
    stages {
        stage('Build Backend Project') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/deploy']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'git.neoxia', url: 'https://git.neoxia-maroc.net/h.ayoub/groupe-ldap-back']]])
                sh "mvn -s settings.xml clean install -DskipTests"
            }
        }

        stage('Build Frontend Project') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'git.neoxia', url: 'https://git.neoxia-maroc.net/abdelmoughit.rabia/groupe-ldap-front.git']]])
                sh 'npm install && node_modules/.bin/ng build'
            }
        }

        stage('Deploy Projects') {
            steps {
                sshPublisher(publishers: [sshPublisherDesc(configName: 'debianVM9', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: 'cd ldap_release/docker && bash docker.sh', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: 'ldap_release', remoteDirectorySDF: false, sourceFiles: 'target/*.jar,docker/**')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
            }
        }
    }
}

