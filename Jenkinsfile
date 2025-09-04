pipeline {
  agent any
  options { timestamps(); ansiColor('xterm') }

  parameters {
    string(name: 'TAGS',         defaultValue: '', description: 'Список JUnit @Tag через запятую. Примеры: smoke | ui,regression')
    string(name: 'EXCLUDE_TAGS', defaultValue: '', description: 'Исключить теги (через запятую). Пример: slow')
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
      steps {
        sh './gradlew clean classes -x test --no-daemon'
      }
    }

    stage('Test') {
      steps {
        script {
          // собираем команду gradle с нужными пропертями
          def cmd = ["./gradlew", "test", "--no-daemon"]
          if (params.TAGS?.trim())         cmd << "-Ptags=${params.TAGS.trim()}"
          if (params.EXCLUDE_TAGS?.trim()) cmd << "-PexcludeTags=${params.EXCLUDE_TAGS.trim()}"
          if (params.BROWSER?.trim())      cmd << "-Pbrowser=${params.BROWSER.trim()}"
          if (params.HEADLESS)             cmd << "-Pheadless=true"
          sh cmd.join(' ')
        }
      }
      post {
        always {
          // JUnit отчёты Gradle
          junit allowEmptyResults: true, testResults: 'build/test-results/test/*.xml'
          // HTML-отчёт Gradle
          archiveArtifacts allowEmptyArchive: true, artifacts: 'build/reports/tests/test/**', fingerprint: true
          // Если используете Allure — архивнём результаты; в Jenkins можно подключить Allure плагин к этой папке
          archiveArtifacts allowEmptyArchive: true, artifacts: 'allure-results/**', fingerprint: true
        }
      }
    }
  }
}
