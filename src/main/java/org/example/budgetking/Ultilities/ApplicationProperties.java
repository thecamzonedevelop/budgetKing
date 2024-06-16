package org.example.budgetking.Ultilities;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {

    private final Environment env;

    public ApplicationProperties(Environment env) {
        this.env = env;
    }

    public String getApplicationName() {
        return env.getProperty("APPLICATION_NAME");
    }

    public String getDatasourceUrl() {
        return env.getProperty("DATASOURCE_URL");
    }

    public String getDatasourceUsername() {
        return env.getProperty("DATASOURCE_USERNAME");
    }

    public String getDatasourcePassword() {
        return env.getProperty("DATASOURCE_PASSWORD");
    }

    public String getHibernateDdlAuto() {
        return env.getProperty("HIBERNATE_DDL_AUTO");
    }

    public String getHibernateDialect() {
        return env.getProperty("HIBERNATE_DIALECT");
    }

    public boolean getShowSql() {
        return Boolean.parseBoolean(env.getProperty("SHOW_SQL"));
    }

    public int getServerPort() {
        return Integer.parseInt(env.getProperty("SERVER_PORT"));
    }
}
