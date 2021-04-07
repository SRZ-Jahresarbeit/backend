package com.example.demo.controller;

import com.example.demo.dto.Sensor;
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
@RequestMapping("sensor")
public interface ISensorController {

  @GetMapping
  Set<Sensor> findAll();

  @PostMapping
  Sensor create(@RequestBody @Valid Sensor sensor);

  @PutMapping("{sensorId}")
  Sensor update(@PathVariable UUID sensorId, @RequestBody @Valid Sensor sensor);

  @DeleteMapping("{sensorId}")
  void delete(@PathVariable UUID sensorId);
}
