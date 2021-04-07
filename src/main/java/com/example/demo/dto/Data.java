package com.example.demo.dto;

import com.example.demo.influx.DataModel;
import java.time.Instant;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@lombok.Data
@AllArgsConstructor
public class Data {

  @NotNull
  private final Instant timestamp;

  @NotNull
  private final double value;

  public Data(final DataModel model) {
    this.timestamp = model.getTimestamp();
    this.value = model.getValue();
  }
}
