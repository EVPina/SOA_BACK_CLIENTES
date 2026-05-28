package com.soa.soaclientes.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "promociones", schema = "public")
public class Promocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(length = 30)
    private String tipo;

    @Column(precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "puntos_requeridos")
    private Integer puntosRequeridos;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    private Boolean activa = true;

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getTipo() { return tipo; }
    public BigDecimal getValor() { return valor; }
    public Integer getPuntosRequeridos() { return puntosRequeridos; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public Boolean getActiva() { return activa; }
}
