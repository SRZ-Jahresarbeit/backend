package com.example.demo.dto;

import com.example.demo.jpa.model.SensorModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class Sensor {

  private final UUID id;
  @NotBlank
  @Length(max = 255)
  private final String name;
  @NotBlank
  @Length(max = 8)
  private final String unit;

  public Sensor(
    @JsonProperty("name") final String name,
    @JsonProperty("unit") final String unit
  ) {
    this.id = null;
    this.name = name;
    this.unit = unit;
  }

  public Sensor(final SensorModel model) {
    this.id = model.getId();
    this.name = model.getName();
    this.unit = model.getUnit();
  }
}
