pipeline {
  agent any
  options { timestamps() }  // ansiColor убрали

  parameters {
    string(name: 'TAGS', defaultValue: '', description: 'JUnit @Tag через запятую: smoke | ui,regression')
    string(name: 'EXCLUDE_TAGS', defaultValue: '', description: 'Исключить теги: slow')
    choice(name: 'BROWSER', choices: ['chrome','firefox','edge'], description: 'Selenide браузер')
    booleanParam(name: 'HEADLESS', defaultValue: true, description: 'Запуск в headless')
  }

  environment {
    GRADLE_USER_HOME = "${WORKSPACE}/.gradle"
  }

  stages {
    stage('Checkout') {
      steps {
        git branch: 'zhanarkhan', url: 'https://github.com/ZHANARKHANN/GUCCI.git'
      }
    }

    stage('Build (no tests)') {
      steps { sh './gradlew clean classes -x test --no-daemon' }
    }

    stage('Test') {
      steps {
        script {
          def cmd = ["./gradlew", "test", "--no-daemon"]
          if (params.TAGS?.trim())         cmd << "-Ptags=${params.TAGS.trim()}"
          if (params.EXCLUDE_TAGS?.trim()) cmd << "-PexcludeTags=${params.EXCLUDE_TAGS.trim()}"
          if (params.BROWSER?.trim())      cmd << "-Pbrowser=${params.BROWSER.trim()}"
          cmd << "-Pheadless=${params.HEADLESS ? 'true' : 'false'}"
          sh cmd.join(' ')
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
