plugins {
    java
}

repositories { mavenCentral() }

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.codeborne:selenide:7.4.2")
    // при желании: логирование
    testRuntimeOnly("org.slf4j:slf4j-simple:2.0.13")
}

tasks.test {
    useJUnitPlatform()

    val includeTagsProp  = (project.findProperty("tags") as String?)?.trim()
    val excludeTagsProp  = (project.findProperty("excludeTags") as String?)?.trim()
    val browserProp      = (project.findProperty("browser") as String?) ?: "chrome"
    val headlessProp     = (project.findProperty("headless") as String?) ?: "true"

    if (!includeTagsProp.isNullOrBlank() || !excludeTagsProp.isNullOrBlank()) {
        useJUnitPlatform {
            if (!includeTagsProp.isNullOrBlank())
                includeTags(*includeTagsProp.split(',').map { it.trim() }.filter { it.isNotEmpty() }.toTypedArray())
            if (!excludeTagsProp.isNullOrBlank())
                excludeTags(*excludeTagsProp.split(',').map { it.trim() }.filter { it.isNotEmpty() }.toTypedArray())
        }
    }

    systemProperty("selenide.browser",  browserProp)
    systemProperty("selenide.headless", headlessProp)

    testLogging {
        events("passed","skipped","failed")
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}
