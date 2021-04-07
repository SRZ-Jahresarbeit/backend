package com.example.demo;

import com.example.demo.dto.Data;
import com.example.demo.service.DataService;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Slf4j
@Configuration
public class MqttConfiguration {

  private static final String CLIENT_ID = "demo-client";
  private static final Charset CHARSET = StandardCharsets.UTF_8;

  @Bean
  MqttClientPersistence mqttPersistence() {
    return new MemoryPersistence();
  }

  @Bean
  MqttClient client(
    final MqttProperties properties,
    final MqttClientPersistence persistence,
    final DataService dataService
  ) throws MqttException {
    final MqttConnectOptions options = new MqttConnectOptions();
    options.setCleanSession(true);
    options.setAutomaticReconnect(true);
    options.setConnectionTimeout(Math.toIntExact(properties.getConnectTimeout().toSeconds()));

    if (StringUtils.hasLength(properties.getUsername()) && StringUtils.hasLength(properties.getPassword())) {
      options.setUserName(properties.getUsername());
      options.setPassword(properties.getPassword().toCharArray());
    }

    final MqttClient client = new MqttClient(properties.getUrl(), CLIENT_ID, persistence);
    client.connect(options);

    client.subscribe("sensor/#", (topic, message) -> {
      try {
        final UUID sensorId = UUID.fromString(topic.substring(7));
        final double value = Double.parseDouble(new String(message.getPayload(), CHARSET));
        dataService.publish(sensorId, new Data(Instant.now(), value));
      }
      catch (NumberFormatException e) {
        log.warn(e.getMessage());
      }
      catch (IllegalArgumentException e) {
        if (e.getMessage().startsWith("Invalid UUID string:")) {
          log.warn(e.getMessage());
        }
        else {
          log.error("Unable to consume data", e);
        }
      }
      catch (NotFoundException e) {
        log.warn("Unable to consume data for sensor: {}", e.toString());
      }
      catch (Exception e) {
        log.error("Unable to consume data", e);
      }
    });

    return client;
  }
}
