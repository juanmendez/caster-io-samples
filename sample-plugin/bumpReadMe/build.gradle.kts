import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// TODO: make buildSrc
val kotlinVersion = "1.3.50"

plugins {
    id("maven")
    id("java")
    id("kotlin")
}

repositories {
    jcenter()
}

dependencies {
    implementation((gradleApi()))
    implementation(localGroovy())
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.50")
    testImplementation("org.junit.jupiter:junit-jupiter:5.4.2")
}


tasks.getByName<Test>("test") {
    testLogging {
        events("passed", "skipped", "failed")
    }
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

version = "1.0"
group = "info.adavis"

tasks.getByName<Upload>("uploadArchives") {
    repositories.withGroovyBuilder {
        "mavenDeployer" {
            "repository"("url" to uri("repo"))
        }
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}