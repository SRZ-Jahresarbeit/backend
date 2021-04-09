package com.example.demo.service;

import com.example.demo.InfluxDB2Properties;
import com.example.demo.NotFoundException;
import com.example.demo.dto.Data;
import com.example.demo.dto.Resolution;
import com.example.demo.influx.DataModel;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.reactive.QueryReactiveApi;
import com.influxdb.client.reactive.WriteReactiveApi;
import io.reactivex.Maybe;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public final class DataService {

  private final InfluxDB2Properties influxProperties;
  private final SensorService sensorService;
  private final QueryReactiveApi queryApi;
  private final WriteReactiveApi writeApi;

  public Flux<Data> find(
    final UUID sensorId,
    final Instant from,
    final Optional<Instant> to,
    final Resolution resolution
  ) {
    if (!this.sensorService.exist(sensorId)) {
      throw new NotFoundException(sensorId, "SENSOR_NOT_FOUND");
    }

    final String query = """
      from(bucket: "%s")
        |> range(start: %s, stop: %s)
        |> filter(fn: (r) => r.sensor_id == "%s")
        |> aggregateWindow(every: 1%s, fn: mean)
      """
      .formatted(
        this.influxProperties.getBucket(),
        from.getEpochSecond(),
        to.isEmpty() ? "now()" : to.get().getEpochSecond(),
        sensorId,
        resolution.getInfluxUnit()
      );

    return Flux.from(this.queryApi.query(query, DataModel.class))
      .map(Data::new);
  }

  public void publish(final UUID sensorId, final Data data) {
    if (!this.sensorService.exist(sensorId)) {
      throw new NotFoundException(sensorId, "SENSOR_NOT_FOUND");
    }

    final DataModel model = new DataModel(sensorId.toString(), data);

    this.writeApi.writeMeasurement(WritePrecision.S, Maybe.just(model));
  }
}
