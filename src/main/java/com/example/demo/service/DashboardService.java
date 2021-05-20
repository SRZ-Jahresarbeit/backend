package com.example.demo.service;

import com.example.demo.NotFoundException;
import com.example.demo.dto.Dashboard;
import com.example.demo.jpa.model.DashboardModel;
import com.example.demo.jpa.model.SensorModel;
import com.example.demo.jpa.repository.DashboardRepository;
import com.example.demo.jpa.repository.SensorRepository;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class DashboardService {

  private final SensorRepository sensorRepository;
  private final DashboardRepository dashboardRepository;

  public Set<Dashboard> findAll() {
    return this.dashboardRepository.findAll()
      .stream()
      .map(Dashboard::new)
      .collect(Collectors.toSet());
  }

  public boolean exist(final UUID id) {
    return this.dashboardRepository.existsById(id);
  }

  public Dashboard create(final Dashboard dashboard) {
    assert dashboard.getId() == null;

    final List<SensorModel> sensors = this.sensorRepository.findAllById(dashboard.getSensors());

    if (sensors.size() != dashboard.getSensors().size()) {
      throw new NotFoundException("", "Some/all sensors were not found.");
    }

    return new Dashboard(this.dashboardRepository.save(new DashboardModel(dashboard, sensors)));
  }

  public Dashboard update(final UUID id, final Dashboard dashboard) {
    final DashboardModel model = this.dashboardRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(dashboard.getId(), "DASHBOARD_NOT_FOUND"));

    final List<SensorModel> sensors = this.sensorRepository.findAllById(dashboard.getSensors());

    if (sensors.size() != dashboard.getSensors().size()) {
      throw new NotFoundException("", "Some/all sensors were not found.");
    }

    model.setName(dashboard.getName());
    model.setSensors(sensors);

    return new Dashboard(this.dashboardRepository.save(model));
  }

  public void delete(final UUID dashboardId) {
    if (this.dashboardRepository.existsById(dashboardId)) {
      throw new NotFoundException(dashboardId, "DASHBOARD_NOT_FOUND");
    }

    this.dashboardRepository.deleteById(dashboardId);
  }
}
