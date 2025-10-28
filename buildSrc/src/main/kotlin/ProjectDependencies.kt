import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.*
import org.gradle.plugin.use.PluginDependenciesSpec

/**
 * Apply common project repository and common dependencies setup to the module
 * reducing boilerplate code in each build.gradle.kts file
 */
fun Project.applyCommonProjectSetup() {
    repositories {
        mavenCentral()
    }

    dependencies {
        // kotlin
        add("implementation", PrivateLibs.Kotlin.jdk8)
        add("implementation", PrivateLibs.Kotlin.stdlib)
        add("implementation", PrivateLibs.Kotlin.reflect)
        add("implementation", PrivateLibs.KotlinX.coroutines)
        add("testImplementation", PrivateLibs.Kotlin.test)

        // Kotest
        add("testImplementation", PrivateLibs.Kotest.junit5)
        add("testImplementation", PrivateLibs.Kotest.property)
        add("testImplementation", PrivateLibs.Kotest.engine)
    }
}

/**
 * Apply common project plugins setup to the module
 * reducing boilerplate code in each build.gradle.kts file
 */
fun PluginDependenciesSpec.applyCommonProjectPlugins() {
    id("io.kotest")                version PrivateLibs.Kotest.version
    id("org.jetbrains.kotlin.jvm") version PrivateLibs.Kotlin.version
    `java-test-fixtures`
}

/**
 * Apply common protobuf plugins to the module that are required to generate code from .proto files
 * reducing boilerplate code in each build.gradle.kts file
 */
fun PluginDependenciesSpec.applyCommonProtobufPlugins() {
    id("com.google.protobuf") version "0.9.5"
}

/**
 * Apply common protobuf dependencies to the module that are required to generate code from .proto files
 * reducing boilerplate code in each build.gradle.kts file
 */
fun DependencyHandler.applyCommonProtobufDependencies() {
    add("implementation", Libs.Protobuf.kotlin)
    add("implementation", Libs.Protobuf.java_util)
}

/**
 * Private libs are used in whole project (each module) and must be applied only in applyCommonProjectSetup function
 */
object PrivateLibs {
    object Kotlin {
        const val version = "2.2.10"
        const val jdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect"
        const val test = "org.jetbrains.kotlin:kotlin-test"
    }
    object KotlinX {
        private const val version = "1.10.2"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
    }
    object Kotest {
        const val version = "6.0.3"
        const val junit5 = "io.kotest:kotest-runner-junit5:$version"
        const val property = "io.kotest:kotest-property:$version"
        const val engine = "io.kotest:kotest-framework-engine:$version"

        private const val arrow_version = "1.1.1"
        const val arrow = "io.kotest.extensions:kotest-assertions-arrow-jvm:$arrow_version"
    }
    object ArrowKt {
        private const val version = "1.1.5"
        const val core = "io.arrow-kt:arrow-core:$version"
    }
}

/**
 * Defines all additional libraries and their versions that are used in whole project in adapters
 */
object Libs {
    object TestContainers {
        private const val version = "1.18.0"
        const val postgresql = "org.testcontainers:postgresql:$version"
        const val junit = "org.testcontainers:junit-jupiter:$version"
    }
    object Kotest {
        const val kotest_extensions_spring = "io.kotest:kotest-extensions-spring-jvm:${PrivateLibs.Kotest.version}"
    }
    object Mockito {
        private const val version = "5.4.0"
        const val mockito_kotlin = "org.mockito.kotlin:mockito-kotlin:$version"
    }
    object ArchUnit {
        private const val version = "1.4.1"
        const val junit5 = "com.tngtech.archunit:archunit-junit5:$version"
    }
    object Kafka {
        private const val version = "3.9.1"
        const val clients = "org.apache.kafka:kafka-clients:$version"
    }
    object Spring {
        private const val version = "3.3.9"
        const val kafka = "org.springframework.kafka:spring-kafka:$version"
        const val jdbc = "org.springframework:spring-jdbc:$version"
        const val context = "org.springframework:spring-context:6.1.2"
    }
    object SpringBoot {
        private const val version = "3.5.5"
        const val bom = "org.springframework.boot:spring-boot-dependencies:$version"
        const val starter = "org.springframework.boot:spring-boot-starter"
        const val starter_web = "org.springframework.boot:spring-boot-starter-web"
        const val starter_test = "org.springframework.boot:spring-boot-starter-test"
        const val starter_logging = "org.springframework.boot:spring-boot-starter-logging"
        const val starter_jdbc = "org.springframework.boot:spring-boot-starter-jdbc"
        const val starter_data_jpa = "org.springframework.boot:spring-boot-starter-data-jpa"
    }
    object Liquibase {
        private const val version = "4.6.1"
        const val core = "org.liquibase:liquibase-core:$version"
    }
    object Postgresql {
        private const val version = "42.3.1"
        const val postgresql = "org.postgresql:postgresql:$version"
    }
    object Protobuf {
        private const val version = "4.32.0"
        const val kotlin = "com.google.protobuf:protobuf-kotlin:$version"
        const val java_util = "com.google.protobuf:protobuf-java-util:$version"
        const val protoc = "com.google.protobuf:protoc:$version"
    }
    object Grpc {
        private const val version = "1.75.0"
        private const val kotlin_version = "1.4.3"
        const val bom = "io.grpc:grpc-bom:$version"
        const val kotlin_stub = "io.grpc:grpc-kotlin-stub:$kotlin_version"
        const val netty_shadded = "io.grpc:grpc-netty-shaded"
        const val protobuf = "io.grpc:grpc-protobuf"
        const val proto_java = "io.grpc:protoc-gen-grpc-java:$version"
        const val proto_kotlin = "io.grpc:protoc-gen-grpc-kotlin:$kotlin_version:jdk8@jar"
    }
    object Jackson {
        private const val version = "2.13.4"
        const val module_kotlin = "com.fasterxml.jackson.module:jackson-module-kotlin:$version"
        const val data_type_jsr310 = "com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$version"
    }
    object Ktorm {
        private const val version = "4.1.1"
        const val core = "org.ktorm:ktorm-core:$version"
        const val support_postgresql = "org.ktorm:ktorm-support-postgresql:$version"
    }
}