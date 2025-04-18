package com.empresa.suporte.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Valid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Descrição não pode ser nula")
    @Size(min = 5, message = "Descrição deve ter no mínimo 5 caracteres")
    private String descricao;

    @NotNull(message = "Status não pode ser nulo")
    private String status;

    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", status='" + status + '\'' +
                ", dataAbertura=" + dataAbertura +
                ", dataFechamento=" + dataFechamento +
                '}';
    }
}
