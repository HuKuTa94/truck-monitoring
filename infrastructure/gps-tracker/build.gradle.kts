applyCommonProjectSetup()

plugins {
    applyCommonProjectPlugins()
}

dependencies {
    // project
    implementation(project(":core:domain"))
    implementation(project(":core:application"))

    testImplementation(testFixtures(project(":core:domain")))
    testFixturesImplementation(testFixtures(project(":core:domain")))

    testImplementation(testFixtures(project(":core:application")))
    testFixturesImplementation(testFixtures(project(":core:application")))

    // jobs
    implementation(Libs.Spring.context)

    // kafka
    implementation(Libs.Spring.kafka)
    implementation(Libs.Kafka.clients)

    // json
    implementation(Libs.Jackson.module_kotlin)
    implementation(Libs.Jackson.data_type_jsr310)
}