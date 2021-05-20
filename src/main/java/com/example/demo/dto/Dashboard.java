package com.example.demo.dto;

import com.example.demo.jpa.model.DashboardModel;
import com.example.demo.jpa.model.SensorModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class Dashboard {

  private final UUID id;
  @NotBlank
  @Length(max = 255)
  private final String name;
  private final List<UUID> sensors;

  public Dashboard(
    @JsonProperty("name") final String name,
    @JsonProperty("sensors") final List<UUID> sensors
  ) {
    this.id = null;
    this.name = name;
    this.sensors = sensors;
  }

  public Dashboard(final DashboardModel model) {
    this.id = model.getId();
    this.name = model.getName();
    this.sensors = model.getSensors().stream().map(SensorModel::getId).collect(Collectors.toList());
  }
}
