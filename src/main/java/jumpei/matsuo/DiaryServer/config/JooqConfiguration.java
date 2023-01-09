package jumpei.matsuo.DiaryServer.config;

import javax.sql.DataSource;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
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

  @Bean
  public DataSourceConnectionProvider connectionProvider() {
    return new DataSourceConnectionProvider
        (new TransactionAwareDataSourceProxy(dataSource()));
  }

  // 使用していないかも...(cofigurationは利用している。)
  @Bean
  public DefaultDSLContext dsl() {
    return new DefaultDSLContext(configuration());
  }

  @Bean
  public DefaultConfiguration configuration() {
    DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
    jooqConfiguration.set(connectionProvider());
    jooqConfiguration.set(SQLDialect.MYSQL);
    jooqConfiguration.set(dataSource());
    return jooqConfiguration;
  }

}
