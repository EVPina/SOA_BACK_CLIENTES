package com.soa.soaclientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.soa.soaclientes.entity.Promocion;

import java.time.LocalDate;
import java.util.List;

public interface PromocionRepository extends JpaRepository<Promocion, Long> {
    @Query("SELECT p FROM Promocion p WHERE p.activa = true AND (p.fechaFin IS NULL OR p.fechaFin >= :today)")
    List<Promocion> findActivas(@Param("today") LocalDate today);
}
