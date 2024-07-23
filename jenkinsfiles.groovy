pipeline {
    agent any

    stages {

    stage('Construir imagen') {
        steps {
            // Clonar el repositorio de GitHub
          
            git branch: 'main', changelog: false, poll: false, url: 'https://github.com/YkzaGrg/BootCamp.git'
            
            
            // Construir la imagen de Docker
            
            sh 'docker image build --tag webserver2 .'
           
         
        }
    }

        stage('Ejecutar contenedor') {
            steps {
                // Ejecutar un contenedor de Docker usando la imagen construida
            
            sh 'docker run -d --name servidor_web -v webvol:/var/www/html/ webserver2'
            }
        }

        stage('Publicación de la imagen') {
            steps {
                
               // Etiquetar la imagen con el último tag
            sh 'docker tag webserver2:latest ykzagorgonita/desafio13:v3'
            
            // Publicar la imagen en Docker Hub
            sh 'docker push ykzagorgonita/desafio13:v3'
             
            }
        }

        stage('Test') {
            steps {
                
                    sh 'curl http://localhost:8080'
                
            }
        }
    }

    triggers {
        cron('0 * * * *') // Ejecutar el pipeline todos los días a la medianoche
    }

    
}