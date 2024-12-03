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
             sh "docker build -t=kshitijk001/seleniumk  ."
         }

  }

  stage('Push Image')
        {
           steps
           {
               sh "docker push kshitijk001/seleniumk"
           }

    }
 }
 }
