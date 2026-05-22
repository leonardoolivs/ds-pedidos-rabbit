package com.rabbit.controllers;

import com.rabbit.entities.Pedido;
import com.rabbit.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pedidos")
public class PedidoController {

    private final Logger logger = LoggerFactory.getLogger(PedidoController.class);

    private final PedidoService service;

    @PostMapping
    public ResponseEntity<Pedido> cadastrar(@RequestBody Pedido pedido){
        logger.info("Pedido recebido {}", pedido.toString());
        pedido = service.enfileirarFila(pedido);

        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }
}
