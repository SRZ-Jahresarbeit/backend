package com.example.demo.jpa.repository;

import com.example.demo.jpa.model.SensorModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<SensorModel, UUID> {

}
