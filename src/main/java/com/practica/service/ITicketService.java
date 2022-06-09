package com.practica.service;

import com.practica.persistence.entity.Ticket;

import java.util.Optional;

public interface ITicketService {

    public Ticket save(Ticket t);
    public Ticket update(Integer id, Ticket t);
    public Optional<Ticket> getTicketById(Integer id);
}
