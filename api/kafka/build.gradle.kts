applyCommonProjectSetup()

plugins {
    applyCommonProjectPlugins()
}

dependencies {
    // project
    implementation(project(":core:domain"))
    implementation(project(":core:application"))

    // spring & logging
    implementation(platform(Libs.SpringBoot.bom))
    implementation(Libs.SpringBoot.starter_logging)

    // kafka
    implementation(Libs.Spring.kafka)
    implementation(Libs.Kafka.clients)

    // json
    implementation(Libs.Jackson.module_kotlin)
    implementation(Libs.Jackson.data_type_jsr310)
}
