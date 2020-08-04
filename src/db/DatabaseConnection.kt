package reprator.khatabookAccount.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

/**
 * Meta object regrouping setup and utility functions for PostgreSQL.
 */
object DatabaseConnection {

    private val hikariConfig = HikariConfig().apply {
        jdbcUrl = System.getenv("JDBC_DATABASE_URL")
    }

    private val dataSource = if (hikariConfig.jdbcUrl != null)
        HikariDataSource(hikariConfig)
    else
        configureHikariCP()

    private fun configureHikariCP(): HikariDataSource {
        val config = HikariConfig("/hikari.properties")
        config.maximumPoolSize = 64
        config.minimumIdle = 6
        config.validationTimeout = 10 * 1000
        config.connectionTimeout = 10 * 1000
        config.maxLifetime = 30 * 60 * 1000
        config.leakDetectionThreshold = 60 * 1000
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }

    fun connect() = Database.connect(dataSource)
}
