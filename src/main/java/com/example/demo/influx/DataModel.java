package com.example.demo.influx;

import com.example.demo.dto.Data;
import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@Measurement(name = "data")
public class DataModel {

  @Column(name = "sensor_id", tag = true)
  private String sensorId;

  @Column(name = "time", timestamp = true)
  private Instant timestamp;

  @Column(name = "value")
  private double value;

  public DataModel(final String sensorId, final Data data) {
    this.sensorId = sensorId;
    this.timestamp = data.getTimestamp();
    this.value = data.getValue();
  }
}
