package com.example.demo;

import com.influxdb.client.InfluxDBClientOptions;
import com.influxdb.client.reactive.InfluxDBClientReactive;
import com.influxdb.client.reactive.InfluxDBClientReactiveFactory;
import com.influxdb.client.reactive.QueryReactiveApi;
import com.influxdb.client.reactive.WriteReactiveApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
public class InfluxDBConfiguration {

  /**
   * @see <a href="https://github.com/influxdata/influxdb-client-java/blob/master/spring/src/main/java/com/influxdb/spring/influx/InfluxDB2AutoConfiguration.java#L62">github.com/influxdata/influxdb-client-java/../InfluxDB2AutoConfiguration.java#L62</a>
   */
  @Bean
  @SuppressWarnings("checkstyle:JavadocMethod")
  InfluxDBClientReactive influxDBClient(final InfluxDB2Properties properties) {
    final InfluxDBClientOptions.Builder influxBuilder = InfluxDBClientOptions.builder()
      .url(properties.getUrl())
      .bucket(properties.getBucket())
      .org(properties.getOrg());

    if (StringUtils.hasLength(properties.getToken())) {
      influxBuilder.authenticateToken(properties.getToken().toCharArray());
    }
    else if (StringUtils.hasLength(properties.getUsername()) && StringUtils.hasLength(properties.getPassword())) {
      influxBuilder.authenticate(properties.getUsername(), properties.getPassword().toCharArray());
    }

    return InfluxDBClientReactiveFactory.create(influxBuilder.build())
      .setLogLevel(properties.getLogLevel());
  }

  @Bean
  QueryReactiveApi queryReactiveApi(final InfluxDBClientReactive client) {
    return client.getQueryReactiveApi();
  }

  @Bean
  WriteReactiveApi writeReactiveApi(final InfluxDBClientReactive client) {
    return client.getWriteReactiveApi();
  }
}
