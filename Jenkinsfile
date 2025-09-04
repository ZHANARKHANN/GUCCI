pipeline {
  agent any
  options { timestamps() }

  parameters {
    string(name: 'TAGS',         defaultValue: '', description: 'JUnit @Tag: smoke | ui,regression')
    string(name: 'EXCLUDE_TAGS', defaultValue: '', description: 'Исключить теги, напр. slow')
    choice(name: 'BROWSER', choices: ['chrome','firefox','edge'], description: 'Selenide браузер')
    booleanParam(name: 'HEADLESS', defaultValue: true, description: 'Запуск в headless')
  }

  environment {
    GRADLE_USER_HOME = "${WORKSPACE}/.gradle"
  }

  stages {
    stage('Prep Gradle') {
      steps {
        sh 'chmod +x ./gradlew || true'
        sh './gradlew --version --no-daemon'
      }
    }

    stage('Build (no tests)') {
      steps {
        sh './gradlew clean classes -x test --no-daemon'
      }
    }

    stage('Test') {
      steps {
        script {
          def args = []
          if (params.TAGS?.trim())         args << "-Ptags=${params.TAGS.trim()}"
          if (params.EXCLUDE_TAGS?.trim()) args << "-PexcludeTags=${params.EXCLUDE_TAGS.trim()}"
          args << "-Pbrowser=${params.BROWSER}"
          args << "-Pheadless=${params.HEADLESS ? 'true' : 'false'}"
          sh "./gradlew test --no-daemon ${args.join(' ')}"
        }
      }
      post {
        always {
          junit allowEmptyResults: true, testResults: 'build/test-results/test/*.xml'
          archiveArtifacts allowEmptyArchive: true, artifacts: 'build/reports/tests/test/**', fingerprint: true
          archiveArtifacts allowEmptyArchive: true, artifacts: 'allure-results/**', fingerprint: true
        }
      }
    }
  }
}
