import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

buildscript {
    repositories { jcenter() }
}

plugins {
    application
    kotlin("jvm") version "1.4.0"

    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group   = "reprator.khatabook"
version = "0.0.1"

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenLocal()
    jcenter()
}

dependencies {

    implementation(project(":lib:error"))
    implementation(project(":account:account-service"))
    implementation(kotlin("stdlib-jdk8"))

    //ktor logging
    implementation(Dependencies.Java.logback)
    implementation(Dependencies.Kotlin.logging)

    //ktor server
    implementation(Dependencies.Ktor.Server.core)
    implementation(Dependencies.Ktor.Server.netty)

    //KTor features
    implementation(Dependencies.Ktor.Client.websockets)
    implementation(Dependencies.Ktor.Client.locations)
    implementation(Dependencies.Ktor.Client.networkTls)
    implementation(Dependencies.Ktor.Client.auth)
    implementation(Dependencies.Ktor.Client.jwt)
    implementation(Dependencies.Ktor.Client.jackson)

    implementation(Dependencies.Java.Jackson.params)

    //DI
    implementation(Dependencies.Kodein.Ktor.controller)
    implementation(Dependencies.Kodein.Ktor.server)

    //Firebase Phone Verification
    implementation(Dependencies.Firebase.firebaseAdmin)

    //Threading
    implementation(Dependencies.Kotlin.coroutineCore)

    //Db
    implementation(Dependencies.Kotlin.Database.postGresSQL)
    implementation(Dependencies.Kotlin.Database.exposedCore)
    implementation(Dependencies.Kotlin.Database.exposedJdbc)
    implementation(Dependencies.Kotlin.Database.exposedDao)

    implementation(Dependencies.Kotlin.hikariCp)
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")

sourceSets["main"].resources.srcDirs("resources")

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}


tasks {

    val shadowJarTask = named<ShadowJar>("shadowJar") {
        archiveFileName.set("khatabook-0-0-1-with-dependencies.jar")
        mergeServiceFiles()

        manifest {
            attributes(mapOf("Main-Class" to application.mainClassName))
        }
    }

    named("jar") {
        enabled = false
    }

    named("assemble") {
        dependsOn(shadowJarTask)
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}