package com.empresa.suporte.controller;

import com.empresa.suporte.model.Ticket;
import com.empresa.suporte.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public Ticket criarTicket(@RequestBody Ticket ticket) {
        return ticketService.criarTicket(ticket);
    }

    @GetMapping
    public List<Ticket> listarTickets() {
        return ticketService.listarTickets();
    }

    @GetMapping("/{id}")
    public Ticket buscarTicket(@PathVariable Long id) {
        return ticketService.buscarTicket(id);
    }

    @PutMapping("/{id}")
    public Ticket atualizarTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
        return ticketService.atualizarTicket(id, ticket);
    }

    @DeleteMapping("/{id}")
    public void deletarTicket(@PathVariable Long id) {
        ticketService.deletarTicket(id);
    }
}
