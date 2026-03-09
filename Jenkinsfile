pipeline {
    agent any

    environment {
        // Paramètres de l'image Docker
        DOCKER_IMAGE = "yvezzz/ecomove-backend"
        DOCKER_TAG = "${env.BUILD_NUMBER}"
        DOCKER_CREDENTIALS = 'dockerhub-credentials'
        
        // Configuration SonarQube
        SONAR_PROJECT_KEY = "ecomove-backend"
        SONAR_HOST_URL = "http://localhost:9000"
    }

    tools {
        maven 'Maven3'
        jdk 'JDK17'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Récupération du code source...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Compilation du projet Ecomove...'
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Tests unitaires & Couverture') {
            steps {
                echo 'Exécution des tests avec JaCoCo...'
                bat 'mvn clean verify'
            }
            post {
                always {
                    // Collecte des résultats de tests
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Analyse SonarQube') {
            steps {
                echo 'Analyse de la qualité du code sur SonarQube...'
                script {
                    withSonarQubeEnv('SonarQube') {
                        bat "mvn sonar:sonar " +
                            "-Dsonar.projectKey=${SONAR_PROJECT_KEY} " +
                            "-Dsonar.projectName=Ecomove-Backend " +
                            "-Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml"
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                echo 'Vérification du Quality Gate...'
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                echo ' Construction de l\'image Docker Ecomove...'
                script {
                    echo "Building image: ${DOCKER_IMAGE}:${DOCKER_TAG}"

                    // Vérifier que le Dockerfile existe
                    bat 'if not exist Dockerfile (echo ERROR: Dockerfile not found && exit 1)'
                    
                    // Construction
                    bat "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
                    bat "docker tag ${DOCKER_IMAGE}:${DOCKER_TAG} ${DOCKER_IMAGE}:latest"

                    echo " Image Docker créée : ${DOCKER_IMAGE}:${DOCKER_TAG}"
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                echo ' Envoi de l\'image vers Docker Hub...'
                script {
                    // Les identifiants doivent être configurés dans Jenkins sous l'ID 'dockerhub-credentials'
                    withCredentials([usernamePassword(credentialsId: DOCKER_CREDENTIALS,
                            usernameVariable: 'DOCKER_USER',
                            passwordVariable: 'DOCKER_PASS')]) {
                        bat 'docker login -u %DOCKER_USER% -p %DOCKER_PASS%'
                        bat "docker push ${DOCKER_IMAGE}:${DOCKER_TAG}"
                        bat "docker push ${DOCKER_IMAGE}:latest"

                        echo ' Images poussées avec succès !'
                            }
                }
            }
        }

        stage('Clean up') {
            steps {
                echo 'Nettoyage de l\'espace de travail...'
                script {
                    // Supprimer l'image locale pour gagner de la place
                    bat "docker rmi ${DOCKER_IMAGE}:${DOCKER_TAG} || exit 0"
                }
            }
        }
    }

    post {
        success {
            echo ' Pipeline Ecomove terminé avec succès !'
        }
        failure {
            echo ' Le pipeline a échoué.'
        }
        always {
            // Nettoyage final du workspace Jenkins
            cleanWs()
        }
    }
}
