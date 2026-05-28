package com.soa.soaclientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soa.soaclientes.entity.TarjetaFidelidad;

import java.util.List;
import java.util.UUID;

public interface TarjetaFidelidadRepository extends JpaRepository<TarjetaFidelidad, Long> {
    List<TarjetaFidelidad> findByClienteId(UUID clienteId);
}
