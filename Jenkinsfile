pipeline {
    agent any
    tools {
        maven 'MAVEN_PATH'
        jdk 'jdk8'
    }
    stages {
        stage("Tools initialization") {
            steps {
                sh "mvn --version"
                sh "java -version"
            }
        }
        stage("Checkout Code") {
            steps {
                checkout scm
            }
        }
        stage("Feature Build & Deploy") {
            when {
                branch 'Feature*'
            }
            steps {
                sh """
                echo "Building Artifact from Feature branch"
                sh "mvn package -DskipTests=true"
                """
            } 
            steps {
                script {
                def pom = readMavenPom file: 'pom.xml'
                sshPublisher(publishers: [sshPublisherDesc(configName: 'server name',
                        transfers: [sshTransfer(cleanRemote:true,
                                sourceFiles: "target/${pom.artifactId}-${pom.version}.jar",
                                removePrefix: "target",
                                remoteDirectory: "${pom.artifactId}",
                                execCommand: "mv ${pom.artifactId}/${pom.artifactId}-${pom.version}.jar ${pom.artifactId}/${pom.version}.jar",
                                    sshTransfer(
                                            execCommand: "/etc/init.d/${pom.artifactId} restart -Dspring.profiles.active=dev}"
                                    )
                        ])
                ])
            }
          }
        } 
        stage("Develop branch Build and upload") {
            when {
                branch 'develop'
            }
            steps {
                sh """
                echo "Building Artifact from develop branch"
                sh "mvn package -DskipTests=true"
                """
                }
            steps {
            script {
                 def pom = readMavenPom file: 'pom.xml'
                 nexusArtifactUploader artifacts: [
                     [
                        artifactId: '${pom.artifactId}', 
                        classifier: '',
                        file: "target/${pom.artifactId}-${pom.version}.jar", 
                        type: 'jar'
                     ]
                 ],
                  credentialsId: 'Nexus3',
                  groupId: "${pom.groupId}",
                  nexusUrl: 'localhost:8081',
                  nexusVersion: 'nexus3',
                  protocol: 'http',
                  repository: 'maven-snapshots',
                  version: "${pom.version}"
               }
            }
         }
         stage("Deploy to qa") {
            steps {
                script {
                def pom = readMavenPom file: 'pom.xml'
                sshPublisher(publishers: [sshPublisherDesc(configName: 'server name',
                        transfers: [sshTransfer(cleanRemote:true,
                                sourceFiles: "target/${pom.artifactId}-${pom.version}.jar",
                                removePrefix: "target",
                                remoteDirectory: "${pom.artifactId}",
                                execCommand: "mv ${pom.artifactId}/${pom.artifactId}-${pom.version}.jar ${pom.artifactId}/${pom.version}.jar",
                                    sshTransfer(
                                            execCommand: "/etc/init.d/${pom.artifactId} restart -Dspring.profiles.active=qa"
                                    )
                        ])
                ])
            }
          }
        }     
      }        
    }