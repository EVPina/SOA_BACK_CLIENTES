package com.soa.soaclientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.soa.soaclientes.entity.HistorialPuntos;

import java.util.List;
import java.util.UUID;

public interface HistorialPuntosRepository extends JpaRepository<HistorialPuntos, Long> {
    List<HistorialPuntos> findByClienteIdOrderByFechaDesc(UUID clienteId);

    @Query("SELECT COALESCE(SUM(h.puntos), 0L) FROM HistorialPuntos h WHERE h.cliente.id = :clienteId")
    Long calcularSaldo(@Param("clienteId") UUID clienteId);
}
