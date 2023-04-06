import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.8.0"
    kotlin("plugin.serialization") version "1.5.0"
    id("com.github.johnrengelman.shadow") version "7.1.0"
    application
}
plugins.apply("com.github.johnrengelman.shadow")

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.14.2")
}


kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}
tasks {
    named<ShadowJar>("shadowJar") {
        archiveFileName.set("generateXml.jar")
        destinationDirectory.set(file("${rootProject.projectDir}/"))
        mergeServiceFiles()
        manifest {
            attributes(mapOf("Main-Class" to "MainKt"))
        }
    }
}
tasks {
    build {
        dependsOn(shadowJar)
    }
}
