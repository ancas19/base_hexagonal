package co.com.ancas.postgres.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

public abstract class DatabaseConfig {
    public DatabaseConfig() {
    }

    protected LocalContainerEntityManagerFactoryBean getEntityManager(String showSql, String hbm2ddl, String dialect, String paramNull, String[] packagesToScan, DataSource datasource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(datasource);
        em.setPackagesToScan(packagesToScan);
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap();
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.hbm2ddl.auto", hbm2ddl);
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.proc.param_null_passing", paramNull);
        em.setJpaPropertyMap(properties);
        return em;
    }

    protected DataSource getDataSource(String className, String jdbcUrl, String jdbcUser, String jdbcPass, Integer maximumPoolSize, Integer minimumIdle, Long idleTimeout, Long maxLifetime, Long connectionTimeout, Long keepaliveTime, Long validationTimeout, Long leakDetectionThreshold) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(className);
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(jdbcUser);
        config.setPassword(jdbcPass);

        // Pool sizing
        config.setMaximumPoolSize(maximumPoolSize);
        config.setMinimumIdle(minimumIdle);

        // Timeouts
        config.setIdleTimeout(idleTimeout);
        config.setMaxLifetime(maxLifetime);
        config.setConnectionTimeout(connectionTimeout);
        config.setValidationTimeout(validationTimeout);

        // Health checks
        config.setKeepaliveTime(keepaliveTime);
        config.setLeakDetectionThreshold(leakDetectionThreshold);

        return new HikariDataSource(config);

    }

    protected PlatformTransactionManager getTransactionManager(LocalContainerEntityManagerFactoryBean factory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(factory.getObject());
        return transactionManager;
    }
}
