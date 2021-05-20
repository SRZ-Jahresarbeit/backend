package com.example.demo.controller;

import com.example.demo.dto.Sensor;
import com.example.demo.service.SensorService;
import java.util.Set;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class SensorController implements ISensorController {

  private final SensorService sensorService;

  @Override
  public Set<Sensor> findAll() {
    return this.sensorService.findAll();
  }

  @Override
  public Sensor create(@Valid final Sensor sensor) {
    return this.sensorService.create(sensor);
  }

  @Override
  public Sensor update(final UUID sensorId, @Valid final Sensor sensor) {
    return this.sensorService.update(sensorId, sensor);
  }

  @Override
  public void delete(final UUID sensorId) {
    this.sensorService.delete(sensorId);
  }
}
