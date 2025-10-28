applyCommonProjectSetup()

plugins {
    applyCommonProjectPlugins()
}

dependencies {
    // project
    // core
    api(project(":core:domain"))
    api(project(":core:application"))

    // api adapters
    api(project(":api:http"))
    api(project(":api:kafka"))

    // infrastructure adapters
    api(project(":infrastructure:orm"))
    api(project(":infrastructure:gps-tracker"))
    api(project(":infrastructure:persist:postgres"))

    // frameworks
    // ktorm
    implementation(Libs.Ktorm.core)
    implementation(Libs.Ktorm.support_postgresql)

    // spring
    implementation(Libs.Spring.jdbc)
    implementation(platform(Libs.SpringBoot.bom))
    implementation(Libs.SpringBoot.starter_web)
    implementation(Libs.SpringBoot.starter_jdbc)
    implementation(Libs.SpringBoot.starter_data_jpa)
    implementation(Libs.Spring.kafka)

    // grpc
    implementation(platform(Libs.Grpc.bom))
    implementation(Libs.Grpc.kotlin_stub)
}