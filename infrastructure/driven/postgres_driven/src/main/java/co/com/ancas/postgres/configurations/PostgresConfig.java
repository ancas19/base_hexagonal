package co.com.ancas.postgres.configurations;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "co.com.ancas.postgres.repositories",
        entityManagerFactoryRef = "postgresEntityManager",
        transactionManagerRef = "postgresTransactionManager"
)
public class PostgresConfig extends DatabaseConfig{
    @Value("${database.postgres.hibernate.show_sql}")
    private String showSql;
    @Value("${database.postgres.hibernate.hbm2ddl.auto}")
    private String hbm2ddl;
    @Value("${database.postgres.dialect}")
    private String dialect;
    @Value("${database.postgres.jpa.properties.hibernate.proc.param_null_passing}")
    private String paramNull;
    @Value("${database.postgres.driverClassName}")
    private String driverClassName;
    @Value("${database.postgres.url}")
    private String jdbcUrl;
    @Value("${database.postgres.user}")
    private String jdbcUser;
    @Value("${database.postgres.pass}")
    private String jdbcPass;
    @Value( "${database.postgres.hikari.maximum-pool-size}")
    private Integer maximumPoolSize;
    @Value( "${database.postgres.hikari.minimum-idle}")
    private Integer minimumIdle;
    @Value( "${database.postgres.hikari.idle-timeout}")
    private Long idleTimeout;
    @Value( "${database.postgres.hikari.max-lifetime}")
    private Long maxLifetime;
    @Value( "${database.postgres.hikari.connection-timeout}")
    private Long connectionTimeout;
    @Value("${database.postgres.hikari.keepaliveTime}")
    private Long keepaliveTime;
    @Value("${database.postgres.hikari.validationTimeout}")
    private Long validationTimeout;
    @Value("${database.postgres.hikari.leakDetectionThreshold}")
    private Long leakDetectionThreshold;


    @Bean
    public LocalContainerEntityManagerFactoryBean postgresEntityManager(DataSource dataSource) {
        return super.getEntityManager(showSql, hbm2ddl, dialect, paramNull, new String[]{"co.com.ancas.postgres"}, dataSource);
    }

    @Bean
    public DataSource postgresDatasource() {
        return super.getDataSource(driverClassName, jdbcUrl, jdbcUser, jdbcPass, maximumPoolSize, minimumIdle, idleTimeout, maxLifetime, connectionTimeout, keepaliveTime, validationTimeout, leakDetectionThreshold);
    }

    @Bean
    public PlatformTransactionManager postgresTransactionManager(LocalContainerEntityManagerFactoryBean postgresEntityManager) {
        return super.getTransactionManager(postgresEntityManager);
    }
}
