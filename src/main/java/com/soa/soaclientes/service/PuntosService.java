package com.soa.soaclientes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soa.soaclientes.dto.CanjeRequest;
import com.soa.soaclientes.dto.CanjeResponse;
import com.soa.soaclientes.dto.ClienteResponse;
import com.soa.soaclientes.dto.HistorialPuntosResponse;
import com.soa.soaclientes.dto.PuntosRequest;
import com.soa.soaclientes.entity.Canje;
import com.soa.soaclientes.entity.Cliente;
import com.soa.soaclientes.entity.HistorialPuntos;
import com.soa.soaclientes.entity.Promocion;
import com.soa.soaclientes.exception.BusinessException;
import com.soa.soaclientes.exception.ResourceNotFoundException;
import com.soa.soaclientes.repository.CanjeRepository;
import com.soa.soaclientes.repository.ClienteRepository;
import com.soa.soaclientes.repository.HistorialPuntosRepository;
import com.soa.soaclientes.repository.PromocionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PuntosService {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private HistorialPuntosRepository historialPuntosRepository;

    @Autowired
    private PromocionRepository promocionRepository;

    @Autowired
    private CanjeRepository canjeRepository;

    @Transactional
    public ClienteResponse ganarPuntos(UUID clienteId, PuntosRequest dto) {
        Cliente cliente = clienteService.findEntityById(clienteId);

        HistorialPuntos mov = new HistorialPuntos();
        mov.setCliente(cliente);
        mov.setTipo("ganado");
        mov.setPuntos(dto.puntos());
        mov.setConcepto(dto.concepto());
        mov.setPedidoId(dto.pedidoId());
        historialPuntosRepository.save(mov);

        cliente.setUltimaVisita(LocalDate.now());
        cliente.setVecesVisitado(cliente.getVecesVisitado() + 1);
        clienteRepository.save(cliente);

        return clienteService.toResponse(cliente);
    }

    @Transactional
    public CanjeResponse canjearPromocion(UUID clienteId, CanjeRequest dto) {
        Cliente cliente = clienteService.findEntityById(clienteId);

        Promocion promocion = promocionRepository.findById(dto.promocionId())
            .orElseThrow(() -> new ResourceNotFoundException("Promoción no encontrada con id " + dto.promocionId()));

        if (!Boolean.TRUE.equals(promocion.getActiva())) {
            throw new BusinessException("La promoción no está activa");
        }
        if (promocion.getFechaFin() != null && promocion.getFechaFin().isBefore(LocalDate.now())) {
            throw new BusinessException("La promoción ha expirado");
        }

        int puntosRequeridos = promocion.getPuntosRequeridos() != null ? promocion.getPuntosRequeridos() : 0;
        long saldoActual = historialPuntosRepository.calcularSaldo(clienteId);
        if (saldoActual < puntosRequeridos) {
            throw new BusinessException("Puntos insuficientes. Tiene " + saldoActual
                + ", requiere " + puntosRequeridos);
        }

        Canje canje = new Canje();
        canje.setCliente(cliente);
        canje.setPromocion(promocion);
        canje.setPuntosUsados(puntosRequeridos);
        canje.setCanjeado(true);
        canje = canjeRepository.save(canje);

        HistorialPuntos mov = new HistorialPuntos();
        mov.setCliente(cliente);
        mov.setTipo("canjeado");
        mov.setPuntos(-puntosRequeridos);
        mov.setConcepto("canje_" + promocion.getNombre().toLowerCase().replace(" ", "_"));
        historialPuntosRepository.save(mov);

        return new CanjeResponse(canje.getId(), clienteId, promocion.getId(),
            promocion.getNombre(), puntosRequeridos, canje.getFechaCanje(), true);
    }

    @Transactional(readOnly = true)
    public List<HistorialPuntosResponse> obtenerHistorial(UUID clienteId) {
        clienteService.findEntityById(clienteId);
        return historialPuntosRepository.findByClienteIdOrderByFechaDesc(clienteId)
            .stream()
            .map(h -> new HistorialPuntosResponse(h.getId(), h.getTipo(), h.getPuntos(),
                h.getConcepto(), h.getPedidoId(), h.getFecha()))
            .toList();
    }

    @Transactional(readOnly = true)
    public List<CanjeResponse> obtenerCanjes(UUID clienteId) {
        clienteService.findEntityById(clienteId);
        return canjeRepository.findByClienteIdOrderByFechaCanjeDesc(clienteId)
            .stream()
            .map(c -> new CanjeResponse(c.getId(), clienteId, c.getPromocion().getId(),
                c.getPromocion().getNombre(), c.getPuntosUsados(), c.getFechaCanje(), c.getCanjeado()))
            .toList();
    }
}
