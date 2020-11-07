import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

repositories {
    mavenLocal()
    jcenter()
}

buildscript {
    repositories { jcenter() }
}

plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":account:account-api"))
    implementation(project(":lib:error"))
    implementation(project(":lib:service"))

    implementation(kotlin("stdlib-jdk8"))

    //ktor logging
    implementation(Dependencies.Java.logback)
    implementation(Dependencies.Kotlin.logging)

    //ktor server
    implementation(Dependencies.Ktor.Server.core)
    implementation(Dependencies.Ktor.Server.netty)

    //KTor features
    implementation(Dependencies.Ktor.Client.jsonJvm)
    implementation(Dependencies.Ktor.Client.jackson)
    implementation(Dependencies.Java.Jackson.params)

    //DI
    implementation(Dependencies.Kodein.Ktor.controller)
    implementation(Dependencies.Kodein.Ktor.server)

    implementation(Dependencies.Kotlin.coroutineCore)

    implementation(Dependencies.Kotlin.Database.postGresSQL)
    implementation(Dependencies.Kotlin.Database.exposedCore)
    implementation(Dependencies.Kotlin.Database.exposedJdbc)
    implementation(Dependencies.Kotlin.Database.exposedDao)
    implementation(Dependencies.Kotlin.Database.exposedJodaTime)

    implementation(Dependencies.Ktor.Client.auth)
    implementation(Dependencies.Ktor.Client.jwt)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    freeCompilerArgs = listOf("-Xinline-classes")
}