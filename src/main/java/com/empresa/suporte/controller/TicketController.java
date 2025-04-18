package com.empresa.suporte.controller;

import com.empresa.suporte.model.Ticket;
import com.empresa.suporte.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Ticket> criarTicket(@RequestBody @Valid Ticket ticket) {
        Ticket ticketCriado = ticketService.criarTicket(ticket);
        return new ResponseEntity<>(ticketCriado, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Ticket> listarTickets() {
        return ticketService.listarTickets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> buscarTicket(@PathVariable Long id) {
        Ticket ticket = ticketService.buscarTicket(id);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> atualizarTicket(@PathVariable Long id, @RequestBody @Valid Ticket ticket) {
        Ticket ticketAtualizado = ticketService.atualizarTicket(id, ticket);
        return new ResponseEntity<>(ticketAtualizado, HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Ticket> atualizarStatusTicket(@PathVariable Long id, @RequestBody String novoStatus) {
        Ticket ticketAtualizado = ticketService.atualizarStatusTicket(id, novoStatus);
        return new ResponseEntity<>(ticketAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTicket(@PathVariable Long id) {
        ticketService.deletarTicket(id);
        return ResponseEntity.noContent().build();
    }
}
