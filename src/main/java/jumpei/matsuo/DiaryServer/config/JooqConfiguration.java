package jumpei.matsuo.DiaryServer.config;

import com.zaxxer.hikari.HikariConfig;
import javax.sql.DataSource;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

@Configuration
public class JooqConfiguration {

  @ConfigurationProperties(prefix = "spring.datasource")
  @Bean
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }

  public DataSourceConnectionProvider connectionProvider() {
    return new DataSourceConnectionProvider
        (new TransactionAwareDataSourceProxy(dataSource()));
  }

  @Bean
  public DefaultDSLContext dsl() {
    return new DefaultDSLContext(configuration());
  }

  public DefaultConfiguration configuration() {
    DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
    jooqConfiguration.set(connectionProvider());
    jooqConfiguration.set(SQLDialect.MYSQL);
    jooqConfiguration.set(dataSource());
    return jooqConfiguration;
  }
}
