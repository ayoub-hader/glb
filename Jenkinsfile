pipeline {
    agent any
    stages {
        stage('Build Backend') {
            tools {
                maven 'Maven 3.6.0'
                jdk 'Java 11'
            }
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/deploy']],
                    userRemoteConfigs: [[credentialsId: 'git.neoxia', url: 'https://git.neoxia-maroc.net/h.ayoub/groupe-ldap-back']]
                ])
                sh "mvn -s settings.xml clean install -DskipTests"
                sshPublisher(publishers: [
                    sshPublisherDesc(configName: 'debianVM9', transfers: [sshTransfer(
                        execTimeout: 120000,
                        remoteDirectory: 'ldap_release',
                        sourceFiles: 'target/*.jar,docker/**'
                    )])
                ])
            }
        }
        stage('Build Frontend') {
            tools {
                nodejs 'nodejs-10.15.1'
            }
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/deploy']],
                    userRemoteConfigs: [[credentialsId: 'git.neoxia', url: 'https://git.neoxia-maroc.net/abdelmoughit.rabia/groupe-ldap-front']]
                ])
                sh "npm install --no-audit && node_modules/.bin/ng build --prod"
                sshPublisher(publishers: [
                    sshPublisherDesc(configName: 'debianVM9', transfers: [sshTransfer(
                        execTimeout: 120000,
                        remoteDirectory: 'ldap_release/groupe-ldap-front',
                        removePrefix: 'dist/groupe-ldap-ng7',
                        sourceFiles: 'dist/groupe-ldap-ng7/**'
                    )])
                ])
            }
        }
        stage('Deploy containers') {
            steps {
                sshPublisher(publishers: [
                    sshPublisherDesc(configName: 'debianVM9', transfers: [sshTransfer(
                        execCommand: 'cd ldap_release/docker && bash docker.sh',
                        execTimeout: 300000,
                        remoteDirectory: 'ldap_release'
                    )])
                ])
            }
        }
    }
}
