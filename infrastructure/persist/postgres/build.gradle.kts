applyCommonProjectSetup()

plugins {
    applyCommonProjectPlugins()
}

dependencies {
    // data base
    implementation(Libs.Liquibase.core)
    runtimeOnly(Libs.Postgresql.postgresql)

    // test containers
    testImplementation(Libs.TestContainers.junit)
    testImplementation(Libs.TestContainers.postgresql)
}

tasks.register<JavaExec>("liquibaseUpdate") {
    group = "database"
    description = "Run Liquibase migrations"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("liquibase.integration.commandline.Main")
    args = listOf(
        "--changeLogFile=src/main/resources/db/changelog/db.changelog-master.xml",
        "--url=jdbc:postgresql://localhost:5432/truck_monitoring",
        "--username=postgres",
        "--password=postgres",
        "update"
    )
}