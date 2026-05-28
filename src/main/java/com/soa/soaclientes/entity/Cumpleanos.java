package com.soa.soaclientes.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "cumpleanos", schema = "public")
public class Cumpleanos {

    @Id
    @Column(name = "cliente_id")
    private UUID clienteId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "enviar_promocion")
    private Boolean enviarPromocion = true;

    public UUID getClienteId() { return clienteId; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public Boolean getEnviarPromocion() { return enviarPromocion; }
    public void setEnviarPromocion(Boolean enviarPromocion) { this.enviarPromocion = enviarPromocion; }
}
