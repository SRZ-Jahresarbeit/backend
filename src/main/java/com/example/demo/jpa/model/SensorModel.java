package com.example.demo.jpa.model;

import com.example.demo.dto.Sensor;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sensor")
public class SensorModel {

  @Id
  @GeneratedValue
  @Column(name = "id", updatable = false, nullable = false, unique = true)
  private UUID id;

  @Column(name = "name", nullable = false, updatable = true, length = 255)
  private String name;

  @Column(name = "unit", nullable = false, updatable = true, length = 8)
  private String unit;

  public SensorModel(final Sensor sensor) {
    this.id = sensor.getId();
    this.name = sensor.getName();
    this.unit = sensor.getUnit();
  }
}
