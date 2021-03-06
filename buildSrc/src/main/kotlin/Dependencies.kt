object Dependencies {
    object Java {
        const val logback = "ch.qos.logback:logback-classic:${Versions.logback}"

        object Jackson {
            const val kotlin = "com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.jackson}"
            const val params = "com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${Versions.jackson}"

        }
    }

    object Kotlin {
        private const val prefix = "org.jetbrains.kotlin:kotlin"
        private const val version = Versions.kotlin

        const val stdlib = "$prefix-stdlib-jdk8:$version"
        const val reflect = "$prefix-reflect:$version"
        const val hikariCp = "com.zaxxer:HikariCP:3.4.3"
        const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.8"

        const val logging = "io.github.microutils:kotlin-logging:${Versions.kotlinLogging}"

        object Database{
            const val postGresSQL = "org.postgresql:postgresql:42.2.19"
            const val exposedCore = "org.jetbrains.exposed:exposed-core:${Versions.exposed}"
            const val exposedJdbc = "org.jetbrains.exposed:exposed-jdbc:${Versions.exposed}"
            const val exposedDao = "org.jetbrains.exposed:exposed-dao:${Versions.exposed}"
            const val exposedJodaTime = "org.jetbrains.exposed:exposed-jodatime:${Versions.exposed}"
        }

        object Test {
            const val truth = "com.google.truth:truth:1.0.1"
            const val mockk = "io.mockk:mockk:${Versions.kotlinMockito}"

            object Spek {
                private const val prefix = "org.jetbrains.spek:spek"
                const val api = "$prefix-api:${Versions.spek}"
                const val junit = "$prefix-junit-platform-engine:${Versions.spek}"
                const val subject = "$prefix-subject-extension:${Versions.spek}"
            }

            object Junit5 {
                private const val junit5 = "org.junit.jupiter:junit-jupiter-"
                const val junit5Api = "${junit5}api:${Versions.testJunit5}"
                const val junit5Engine = "${junit5}engine:${Versions.testJunit5}"
            }
        }
    }

    object Ktor {
        private const val prefix = "io.ktor:ktor"

        object Server {
            private const val sprefix = "$prefix-server"

            const val core = "$sprefix-core:${Versions.ktorServer}"
            const val netty = "$sprefix-netty:${Versions.ktorServer}"
        }

        object Client {
            const val websockets = "$prefix-websockets:${Versions.ktorServer}"
            const val networkTls = "$prefix-network-tls:${Versions.ktorServer}"
            const val locations = "$prefix-locations:${Versions.ktorServer}"
            const val auth = "$prefix-auth:${Versions.ktorServer}"
            const val jwt = "$prefix-auth-jwt:${Versions.ktorServer}"
            const val jackson = "$prefix-jackson:${Versions.ktorServer}"



            private const val cprefix = "$prefix-client"

            const val cio = "$cprefix-cio:${Versions.ktor}"
            const val core = "$cprefix-core:${Versions.ktor}"
            const val coreJvm = "$cprefix-core-jvm:${Versions.ktor}"
            const val json = "$cprefix-json:${Versions.ktor}"
            const val jsonJvm = "$cprefix-json-jvm:${Versions.ktor}"
            const val clientJackson = "$cprefix-jackson:${Versions.ktor}"
        }
    }

    object Kodein {
        private const val prefix = "org.kodein.di:kodein-di"
        private const val version = "7.0.0"

       /* implementation("org.kodein.di:kodein-di-framework-ktor-server-jvm:7.0.0")
        implementation("org.kodein.di:kodein-di-framework-ktor-server-controller-jvm:7.0.0")*/

        object Ktor {
            private const val kprefix = "$prefix-framework-ktor-server"

            const val controller = "$kprefix-controller-jvm:$version"
            const val server = "$kprefix-jvm:$version"
        }
    }

    object Firebase{
        const val firebaseAdmin = "com.google.firebase:firebase-admin:6.15.0"
    }
}