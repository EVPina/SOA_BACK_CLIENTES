package com.soa.soaclientes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soa.soaclientes.dto.TarjetaResponse;
import com.soa.soaclientes.entity.Cliente;
import com.soa.soaclientes.entity.TarjetaFidelidad;
import com.soa.soaclientes.repository.TarjetaFidelidadRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TarjetaService {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TarjetaFidelidadRepository tarjetaRepository;

    @Transactional
    public TarjetaResponse emitirTarjeta(UUID clienteId) {
        Cliente cliente = clienteService.findEntityById(clienteId);

        TarjetaFidelidad tarjeta = new TarjetaFidelidad();
        tarjeta.setCliente(cliente);
        tarjeta.setCodigoTarjeta(UUID.randomUUID().toString().replace("-", "").substring(0, 20).toUpperCase());
        tarjeta.setFechaVencimiento(LocalDate.now().plusYears(2));

        return toResponse(tarjetaRepository.save(tarjeta));
    }

    @Transactional(readOnly = true)
    public List<TarjetaResponse> listarPorCliente(UUID clienteId) {
        clienteService.findEntityById(clienteId);
        return tarjetaRepository.findByClienteId(clienteId).stream().map(this::toResponse).toList();
    }

    private TarjetaResponse toResponse(TarjetaFidelidad t) {
        return new TarjetaResponse(t.getId(), t.getCodigoTarjeta(), t.getFechaEmision(),
            t.getFechaVencimiento(), t.getActiva());
    }
}
