pipeline {
	agent any
	stages {
		stage("build") {
			steps {
				script {
					echo "-----------------------------------------------------------------------------------------------"
					echo "Build"
					sh "./gradlew --version"
					sh "./gradlew clean build -x check"
				}
			}
		}
        stage("SCA") {
            steps {
                script {
                    echo "-----------------------------------------------------------------------------------------------"
                    sh "bash ./gradlew checkstyleMain checkstyleTest pmdMain pmdTest spotbugsMain spotbugsTest"
                    }

            }
        }
        stage("Unit tests") {
            steps {
                script {
                    echo "-----------------------------------------------------------------------------------------------"
                    sh "./gradlew test"
                }
            }
        }
	}
	post {
		failure {
			echo "-----------------------------------------------------------------------------------------------"
			echo "-                                       FAILED                                                -"
			echo "-----------------------------------------------------------------------------------------------"
		}
		success {
			echo "-----------------------------------------------------------------------------------------------"
            echo "-                                      SUCCESS                                                -"
            echo "-----------------------------------------------------------------------------------------------"
		}
		always {
			junit 'build/test-results/test/*.xml'
		}
	}
}
