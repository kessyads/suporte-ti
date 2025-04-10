package com.empresa.suporte.service;

import com.empresa.suporte.exception.TicketNotFoundException;
import com.empresa.suporte.exception.InvalidTicketException;
import com.empresa.suporte.model.Ticket;
import com.empresa.suporte.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    private static final Logger logger = LoggerFactory.getLogger(TicketService.class);

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket criarTicket(Ticket ticket) {
        if (ticket.getDescricao() == null || ticket.getDescricao().isEmpty()) {
            throw new InvalidTicketException("Descrição do ticket é obrigatória.");
        }

        if (ticket.getStatus() == null
                || (!ticket.getStatus().equals("Aberto") && !ticket.getStatus().equals("Fechado"))) {
            throw new InvalidTicketException("Status inválido.");
        }

        ticket.setDataAbertura(LocalDateTime.now());
        ticket.setStatus("Aberto");

        logger.info("Criando ticket com descrição: {}", ticket.getDescricao());
        return ticketRepository.save(ticket);
    }

    public List<Ticket> listarTickets() {
        logger.info("Listando todos os tickets.");
        return ticketRepository.findAll();
    }

    public Ticket buscarTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket não encontrado para o ID: " + id));

        logger.info("Buscando ticket com ID: {}", id);
        return ticket;
    }

    public Ticket atualizarTicket(Long id, Ticket ticket) {
        Ticket ticketExistente = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket não encontrado para o ID: " + id));

        if ("Fechado".equals(ticketExistente.getStatus())) {
            throw new RuntimeException("Ticket já está fechado.");
        }

        ticketExistente.setDescricao(ticket.getDescricao());
        ticketExistente.setStatus(ticket.getStatus());

        if ("Fechado".equals(ticket.getStatus())) {
            ticketExistente.setDataFechamento(LocalDateTime.now());
        }

        logger.info("Atualizando ticket com ID: {}", id);
        return ticketRepository.save(ticketExistente);
    }

    public Ticket atualizarStatusTicket(Long id, String novoStatus) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket não encontrado para o ID: " + id));

        if ("Fechado".equals(ticket.getStatus())) {
            throw new RuntimeException("Ticket já está fechado");
        }

        if ("Fechado".equals(novoStatus)) {
            ticket.setDataFechamento(LocalDateTime.now());
        }

        ticket.setStatus(novoStatus);
        logger.info("Atualizando status do ticket com ID: {}", id);
        return ticketRepository.save(ticket);
    }

    public void deletarTicket(Long id) {
        Ticket ticketExistente = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket não encontrado para o ID: " + id));

        ticketRepository.delete(ticketExistente);
        logger.info("Ticket com ID: {} deletado", id);
    }
}