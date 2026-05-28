package com.soa.soaclientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soa.soaclientes.entity.Canje;

import java.util.List;
import java.util.UUID;

public interface CanjeRepository extends JpaRepository<Canje, Long> {
    List<Canje> findByClienteIdOrderByFechaCanjeDesc(UUID clienteId);
}
