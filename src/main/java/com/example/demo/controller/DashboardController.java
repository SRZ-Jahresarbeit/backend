package com.example.demo.controller;

import com.example.demo.dto.Dashboard;
import com.example.demo.service.DashboardService;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DashboardController implements IDashboardController {

  private final DashboardService dashboardService;

  @Override
  public Set<Dashboard> findAll() {
    return this.dashboardService.findAll();
  }

  @Override
  public Dashboard create(final Dashboard dashboard) {
    return this.dashboardService.create(dashboard);
  }

  @Override
  public Dashboard update(final UUID dashboardId, final Dashboard dashboard) {
    return this.dashboardService.update(dashboardId, dashboard);
  }

  @Override
  public void delete(final UUID dashboardId) {
    this.dashboardService.delete(dashboardId);
  }
}
