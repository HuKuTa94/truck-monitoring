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

    // spring jpa
    implementation(platform(Libs.SpringBoot.bom))
    implementation(Libs.SpringBoot.starter_jdbc)
    implementation(Libs.SpringBoot.starter_data_jpa)
    implementation(Libs.SpringBoot.starter_test)

    // data base
    implementation(Libs.Liquibase.core)
    runtimeOnly(Libs.Postgresql.postgresql)

    // mockito
    testImplementation(Libs.Mockito.mockito_kotlin)

    // kotest
    testImplementation(Libs.Kotest.kotest_extensions_spring)

    // test containers
    testImplementation(Libs.TestContainers.junit)
    testImplementation(Libs.TestContainers.postgresql)
}