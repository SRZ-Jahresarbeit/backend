package com.example.demo.controller;

import com.example.demo.dto.Data;
import com.example.demo.dto.Resolution;
import com.example.demo.service.DataService;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
public class DataController implements IDataController {

  private final DataService dataService;

  @Override
  public Flux<Data> find(
    final UUID sensorId,
    final Instant from,
    final Optional<Instant> to,
    final Resolution resolution
  ) {
    return this.dataService.find(sensorId, from, to, resolution);
  }
}
