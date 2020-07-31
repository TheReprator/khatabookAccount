import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

buildscript {
    repositories { jcenter() }
}

plugins {
    application
    kotlin("jvm") version "1.3.72"

    // Shadow plugin - enable support for building our UberJar
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
    implementation(Dependencies.Kodein.core)
    implementation(Dependencies.Kodein.generic)
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


    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    testImplementation(Dependencies.Kotlin.Test.truth)
    testImplementation(Dependencies.Kotlin.Test.mockk)
    testImplementation(Dependencies.Kotlin.Test.Junit5.junit5Api)
    testRuntimeOnly(Dependencies.Kotlin.Test.Junit5.junit5Engine)

    testImplementation("io.ktor:ktor-server-tests:1.3.2")
    testImplementation("org.mock-server:mockserver-netty:5.3.0")
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

// Configure the "shadowJar" task to properly build our UberJar
// we effectively want a jar with zero dependencies we can run and will "just work"
tasks {

    val shadowJarTask = named<ShadowJar>("shadowJar") {
        // explicitly configure the filename of the resulting UberJar
        archiveFileName.set("khatabook-0-0-1-with-dependencies.jar")

        // Appends entries in META-INF/services resources into a single resource. For example, if there are several
        // META-INF/services/org.apache.maven.project.ProjectBuilder resources spread across many JARs the individual
        // entries will all be concatenated into a single META-INF/services/org.apache.maven.project.ProjectBuilder
        // resource packaged into the resultant JAR produced by the shading process -
        // Effectively ensures we bring along all the necessary bits from Jetty
        mergeServiceFiles()

        // As per the App Engine java11 standard environment requirements listed here:
        // https://cloud.google.com/appengine/docs/standard/java11/runtime
        // Your Jar must contain a Main-Class entry in its META-INF/MANIFEST.MF metadata file
        manifest {
            attributes(mapOf("Main-Class" to application.mainClassName))
        }
    }

    // because we're using shadowJar, this task has limited value
    named("jar") {
        enabled = false
    }

    // update the `assemble` task to ensure the creation of a brand new UberJar using the shadowJar task
    named("assemble") {
        dependsOn(shadowJarTask)
    }
}
