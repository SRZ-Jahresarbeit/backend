package com.example.demo.controller;

import com.example.demo.dto.Dashboard;
import java.util.Set;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dashboard")
public interface IDashboardController {

  @GetMapping
  Set<Dashboard> findAll();

  @PostMapping
  Dashboard create(@RequestBody @Valid Dashboard dashboard);

  @PutMapping("{dashboardId}")
  Dashboard update(@PathVariable UUID dashboardId, @RequestBody @Valid Dashboard dashboard);

  @DeleteMapping("{dashboardId}")
  void delete(@PathVariable UUID dashboardId);
}
