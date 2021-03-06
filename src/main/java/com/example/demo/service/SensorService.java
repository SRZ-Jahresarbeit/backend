package com.example.demo.service;

import com.example.demo.NotFoundException;
import com.example.demo.dto.Sensor;
import com.example.demo.jpa.model.DashboardModel;
import com.example.demo.jpa.model.SensorModel;
import com.example.demo.jpa.repository.DashboardRepository;
import com.example.demo.jpa.repository.SensorRepository;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SensorService {

  private final SensorRepository sensorRepository;
  private final DashboardRepository dashboardRepository;

  public Set<Sensor> findAll() {
    return this.sensorRepository.findAll()
      .stream()
      .map(Sensor::new)
      .collect(Collectors.toSet());
  }

  public boolean exist(final UUID id) {
    return this.sensorRepository.existsById(id);
  }

  public Sensor create(final Sensor sensor) {
    assert sensor.getId() == null;

    return new Sensor(this.sensorRepository.save(new SensorModel(sensor)));
  }

  public Sensor update(final UUID id, final Sensor sensor) {
    final SensorModel model = this.sensorRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(sensor.getId(), "SENSOR_NOT_FOUND"));

    model.setName(sensor.getName());
    model.setUnit(sensor.getUnit());

    return new Sensor(this.sensorRepository.save(model));
  }

  @Transactional
  public void delete(final UUID sensorId) {
    if (!this.sensorRepository.existsById(sensorId)) {
      throw new NotFoundException(sensorId, "SENSOR_NOT_FOUND");
    }

    final Set<DashboardModel> dashboards = this.dashboardRepository.findBySensor(sensorId);
    dashboards.forEach(dashboard -> dashboard.getSensors().removeIf(sensor -> sensor.getId() == sensorId));
    this.dashboardRepository.saveAll(dashboards);

    this.sensorRepository.deleteById(sensorId);
  }
}
