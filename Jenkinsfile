pipeline {
    agent any

    environment {
        // Initialize the version variable
        POM_VERSION = ''
    }

    stages {
        stage('Verify Tools') {
            steps {
                script {
                    // Verify Maven installation
                    sh 'mvn --version'
                }
            }
        }

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Extract Version') {
            steps {
                script {
                    // Read the version from pom.xml
                    POM_VERSION = sh(script: "mvn help:evaluate -Dexpression=project.version -q -DforceStdout", returnStdout: true).trim()
                    echo "Building version: ${POM_VERSION}"
                }
            }
        }

        stage('Build All Versions') {
            parallel {
                stage('Build 1.21.5') {
                    steps {
                        sh 'mvn clean install -P mc1.21.5'
                    }
                    post {
                        success {
                            archiveArtifacts artifacts: '**/minecraft-1.21.5/EmpireWandPlus-*-mc1.21.5.jar', fingerprint: true
                        }
                    }
                }
                stage('Build 1.21.4') {
                    steps {
                        sh 'mvn clean install -P mc1.21.4'
                    }
                    post {
                        success {
                            archiveArtifacts artifacts: '**/minecraft-1.21.4/EmpireWandPlus-*-mc1.21.4.jar', fingerprint: true
                        }
                    }
                }
                stage('Build 1.21.3') {
                    steps {
                        sh 'mvn clean install -P mc1.21.3'
                    }
                    post {
                        success {
                            archiveArtifacts artifacts: '**/minecraft-1.21.3/EmpireWandPlus-*-mc1.21.3.jar', fingerprint: true
                        }
                    }
                }
                stage('Build 1.21.2') {
                    steps {
                        sh 'mvn clean install -P mc1.21.2'
                    }
                    post {
                        success {
                            archiveArtifacts artifacts: '**/minecraft-1.21.2/EmpireWandPlus-*-mc1.21.2.jar', fingerprint: true
                        }
                    }
                }
            }
        }
    }

    post {
        success {
            script {
                echo "Build successful for version ${POM_VERSION}"
            }
        }
        always {
            cleanWs()
        }
    }
}