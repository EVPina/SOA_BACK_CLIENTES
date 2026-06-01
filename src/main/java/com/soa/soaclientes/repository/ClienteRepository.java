package com.soa.soaclientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.soa.soaclientes.entity.Cliente;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    Optional<Cliente> findByTelefono(String telefono);
    boolean existsByTelefono(String telefono);
    boolean existsByEmail(String email);
    Optional<Cliente> findByEmail(String email);
    // Para búsqueda por email O teléfono
    @Query("SELECT c FROM Cliente c WHERE c.telefono = :valor OR c.email = :valor")
    Optional<Cliente> buscarPorTelefonoOEmail(@Param("valor") String valor);
}
