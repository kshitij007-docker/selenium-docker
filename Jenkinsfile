pipeline
{

 agent any


 stages
 {
     stage('Build-jar')
     {
        steps
        {
            sh "mvn clean package -DskipTests"
        }

 }
 stage('Build Image')
      {
         steps
         {
             sh "docker build -t=kshitijk001/seleniumk:latest  ."
         }

  }

  stage('Push Image')
        {
//         environment{
//         DOCKER_HUB=credentials('dockerhub-creds')}
           steps
           {
               sh 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
               sh "docker push kshitijk001/seleniumk"
               sh "docker push kshitijk001/seleniumk:latest kshitijk001/seleniumk:${env.BUILD_NUMBER}"
               sh 'docker push kshitijk001/seleniumk:${env.BUILD_NUMBER}'
           }

    }
 }
 post
 {
 always {
 sh "docker logout"
 }
 }
 }
