package com.soa.soaclientes.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tarjetas_fidelidad", schema = "public")
public class TarjetaFidelidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "codigo_tarjeta", unique = true, nullable = false, length = 50)
    private String codigoTarjeta;

    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;

    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;

    private Boolean activa = true;

    @PrePersist
    void onCreate() {
        if (fechaEmision == null) fechaEmision = LocalDate.now();
        if (activa == null) activa = true;
    }

    public Long getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public String getCodigoTarjeta() { return codigoTarjeta; }
    public void setCodigoTarjeta(String codigoTarjeta) { this.codigoTarjeta = codigoTarjeta; }
    public LocalDate getFechaEmision() { return fechaEmision; }
    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
    public Boolean getActiva() { return activa; }
}
