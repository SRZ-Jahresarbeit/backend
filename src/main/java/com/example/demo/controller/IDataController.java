package com.example.demo.controller;

import com.example.demo.dto.Data;
import com.example.demo.dto.Resolution;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("sensor")
public interface IDataController {

  @GetMapping(value = "{sensorId}/data")
  Flux<Data> find(
    @PathVariable UUID sensorId,
    @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) Instant from,
    @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) Optional<Instant> to,
    @RequestParam Resolution resolution
  );

  @DeleteMapping(value = "{sensorId}/data")
  Mono<Void> delete(
    @PathVariable UUID sensorId,
    @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) Instant from,
    @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) Instant to
  );
}
