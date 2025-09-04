plugins { java }

java { toolchain { languageVersion.set(JavaLanguageVersion.of(21)) } }

repositories { mavenCentral() }

dependencies {
    // main
    implementation("com.codeborne:selenide:7.4.2")
    implementation("org.aeonbits.owner:owner:1.0.12")
    implementation("net.datafaker:datafaker:2.3.1")
    implementation("io.qameta.allure:allure-java-commons:2.29.0")
    implementation("io.qameta.allure:allure-selenide:2.29.0")

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

    // tests
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("io.qameta.allure:allure-junit5:2.29.0")

    testCompileOnly("org.projectlombok:lombok:1.18.34")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.34")
}

tasks.test {
    useJUnitPlatform()

    val includeTagsProp  = (project.findProperty("tags") as String?).orEmpty().trim()
    val excludeTagsProp  = (project.findProperty("excludeTags") as String?).orEmpty().trim()
    val browserProp      = (project.findProperty("browser") as String?) ?: "chrome"
    val headlessProp     = (project.findProperty("headless") as String?) ?: "true"

    useJUnitPlatform {
        if (includeTagsProp.isNotBlank())
            includeTags(*includeTagsProp.split(',').map { it.trim() }.filter { it.isNotEmpty() }.toTypedArray())
        if (excludeTagsProp.isNotBlank())
            excludeTags(*excludeTagsProp.split(',').map { it.trim() }.filter { it.isNotEmpty() }.toTypedArray())
    }

    systemProperty("selenide.browser",  browserProp)
    systemProperty("selenide.headless", headlessProp)

    testLogging { events("passed","skipped","failed") }
}
