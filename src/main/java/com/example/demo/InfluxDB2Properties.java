/*
 * The MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.example.demo;

import com.influxdb.LogLevel;
import java.time.Duration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties for InfluxDB 2.
 *
 * @see <a href="https://github.com/influxdata/influxdb-client-java/blob/master/spring/src/main/java/com/influxdb/spring/influx/InfluxDB2Properties.java">github.com/influxdata/influxdb-client-java/../InfluxDB2Properties.java</a>
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.influx2")
public class InfluxDB2Properties {

  private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

  /**
   * URL to connect to InfluxDB.
   */
  private String url;

  /**
   * Username to use in the basic auth.
   */
  private String username;

  /**
   * Password to use in the basic auth.
   */
  private String password;

  /**
   * Token to use for the authorization.
   */
  private String token;

  /**
   * Default destination organization for writes and queries.
   */
  private String org;

  /**
   * Default destination bucket for writes.
   */
  private String bucket;

  /**
   * The log level for logging the HTTP request and HTTP response.
   */
  private LogLevel logLevel = LogLevel.NONE;

  /**
   * Read timeout for {@code OkHttpClient}.
   */
  private Duration readTimeout = DEFAULT_TIMEOUT;

  /**
   * Write timeout for {@code OkHttpClient}.
   */
  private Duration writeTimeout = DEFAULT_TIMEOUT;

  /**
   * Connection timeout for {@code OkHttpClient}.
   */
  private Duration connectTimeout = DEFAULT_TIMEOUT;
}
