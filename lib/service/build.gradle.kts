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
    implementation(kotlin("stdlib-jdk8"))

    //DI
    implementation(Dependencies.Kodein.Ktor.controller)
    implementation(Dependencies.Kodein.Ktor.server)

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

    implementation(Dependencies.Ktor.Client.auth)
    implementation(Dependencies.Ktor.Client.jwt)

    api(project(":lib:error"))
}
