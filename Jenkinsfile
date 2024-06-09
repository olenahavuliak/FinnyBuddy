pipeline {

  environment {
    dockerimagename = "olenahavuliak/finny-buddy-service"
    dockerImage = ""
  }

  agent {
      docker {
        image 'docker:19.03.12-dind'
        args '--privileged' // Required for DinD to work
      }
    }

  stages {

    stage('Checkout Source') {
      steps {
        git 'https://github.com/ohavuliak/FinnyBuddy.git'
      }
    }

    stage('Build image') {
      steps{
        script {
          dockerImage = docker.build dockerimagename
        }
      }
    }

    stage('Pushing Image') {
      environment {
               registryCredential = 'dockerhub-credentials'
           }
      steps{
        script {
          docker.withRegistry( 'https://registry.hub.docker.com', registryCredential ) {
            dockerImage.push("1.0")
          }
        }
      }
    }

    stage('Deploying App container to Kubernetes') {
      steps {
        script {
          kubernetesDeploy(configs: "deployment.yaml")
        }
      }
    }

  }

}