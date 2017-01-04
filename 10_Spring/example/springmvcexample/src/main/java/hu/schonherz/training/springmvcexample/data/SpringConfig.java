package hu.schonherz.training.springmvcexample.data;

import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    /**
     * This method creates a {@link DataSource} instance, specifically
     * a {@link PGPoolingDataSource} with the necessary information to establish
     * connection to a local PostgreSQL server's database.
     *
     * Once this method returns with the created instance, it will be added to Spring's
     * application context and will be managed by the framework (because of the @Bean annotation).
     *
     * (!)
     * In a typical Java project, the connection information are supplied from a property file
     * or the datasource itself is registered inside an Application Server.
     *
     * NEVER include hard-coded credentials (like user names and passwords) in your Java code!
     */
    @Bean
    public DataSource createDataSource() {
        final PGPoolingDataSource dataSource = new PGPoolingDataSource();

        dataSource.setServerName("localhost");
        dataSource.setPortNumber(5432);
        dataSource.setUser("postgres");
        dataSource.setPassword("pass");
        dataSource.setDatabaseName("postgres");

        dataSource.setInitialConnections(10);
        dataSource.setMaxConnections(30);

        return dataSource;
    }
}
