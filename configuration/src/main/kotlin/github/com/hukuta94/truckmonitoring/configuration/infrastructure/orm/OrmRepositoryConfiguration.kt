package github.com.hukuta94.truckmonitoring.configuration.infrastructure.orm

import github.com.hukuta94.truckmonitoring.core.application.port.repository.domain.TruckRepositoryPort
import github.com.hukuta94.truckmonitoring.infrastructure.orm.query.KtormGetTrucksQuery
import github.com.hukuta94.truckmonitoring.infrastructure.orm.repository.KtormTruckRepository
import github.com.hukuta94.truckmonitoring.infrastructure.orm.repository.OrmUnitOfWorkAdapter
import org.ktorm.database.Database
import org.ktorm.support.postgresql.PostgreSqlDialect
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Clock
import javax.sql.DataSource

@Configuration
open class OrmRepositoryConfiguration {

    @Bean
    open fun database(dataSource: DataSource) = Database.connectWithSpringSupport(
        dataSource = dataSource,
        dialect = PostgreSqlDialect()
    )

    @Bean
    open fun ormUnitOfWork(database: Database) = OrmUnitOfWorkAdapter(database)

    @Bean
    open fun getTrucksQuery(database: Database) = KtormGetTrucksQuery(database)

    @Bean
    open fun truckRepository(
        database: Database,
        clock: Clock,
    ): TruckRepositoryPort = KtormTruckRepository(
        database = database,
        clock = clock,
    )
}