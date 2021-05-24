package com.example.demo.controller;

import com.example.demo.dto.Data;
import com.example.demo.dto.Resolution;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("sensor")
public interface IDataController {

  @GetMapping(value = "{sensorId}/data")
  Flux<Data> find(
    @PathVariable UUID sensorId,
    @RequestParam Instant from,
    @RequestParam Optional<Instant> to,
    @RequestParam Resolution resolution
  );
}
