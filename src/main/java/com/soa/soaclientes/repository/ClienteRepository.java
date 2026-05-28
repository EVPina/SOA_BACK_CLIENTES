package com.soa.soaclientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soa.soaclientes.entity.Cliente;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    Optional<Cliente> findByTelefono(String telefono);
    boolean existsByTelefono(String telefono);
    boolean existsByEmail(String email);
}
