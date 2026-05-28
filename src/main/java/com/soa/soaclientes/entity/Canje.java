package com.soa.soaclientes.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "canjes", schema = "public")
public class Canje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "promocion_id", nullable = false)
    private Promocion promocion;

    @Column(name = "puntos_usados")
    private Integer puntosUsados;

    @Column(name = "fecha_canje")
    private LocalDateTime fechaCanje;

    private Boolean canjeado = false;

    @PrePersist
    void onCreate() {
        if (fechaCanje == null) fechaCanje = LocalDateTime.now();
        if (canjeado == null) canjeado = false;
    }

    public Long getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Promocion getPromocion() { return promocion; }
    public void setPromocion(Promocion promocion) { this.promocion = promocion; }
    public Integer getPuntosUsados() { return puntosUsados; }
    public void setPuntosUsados(Integer puntosUsados) { this.puntosUsados = puntosUsados; }
    public LocalDateTime getFechaCanje() { return fechaCanje; }
    public Boolean getCanjeado() { return canjeado; }
    public void setCanjeado(Boolean canjeado) { this.canjeado = canjeado; }
}
