package com.example.demo.jpa.model;

import com.example.demo.dto.Dashboard;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dashboard")
public class DashboardModel {

  @Id
  @GeneratedValue
  @Column(name = "id", updatable = false, nullable = false, unique = true)
  private UUID id;

  @Column(name = "name", nullable = false, updatable = true, length = 255)
  private String name;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<SensorModel> sensors;

  public DashboardModel(final Dashboard dashboard, final List<SensorModel> sensors) {
    this.id = dashboard.getId();
    this.name = dashboard.getName();
    this.sensors = sensors;
  }
}
