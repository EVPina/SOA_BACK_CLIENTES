package com.soa.soaclientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soa.soaclientes.entity.Cumpleanos;

import java.util.UUID;

public interface CumpleanosRepository extends JpaRepository<Cumpleanos, UUID> {
}
