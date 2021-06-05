package com.example.demo.jpa.repository;

import com.example.demo.jpa.model.DashboardModel;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardRepository extends JpaRepository<DashboardModel, UUID> {

  @Query("select object(d) from DashboardModel as d inner join d.sensors as s where s.id = ?1")
  Set<DashboardModel> findBySensor(UUID sensorId);
}
