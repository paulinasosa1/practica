package com.practica.services;

import com.practica.entities.Ticket;
import com.practica.repositories.ITicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketServiceImpl implements ITicketService{

    @Autowired
    ITicketRepository ticketRepository;

    @Override
    public Ticket save(Ticket t) {
        return ticketRepository.save(t);
    }

    @Override
    public Ticket update(Integer id, Ticket t) {

        Optional<Ticket> ticketBD = ticketRepository.findById(id);
        Ticket ticketUpdate = ticketBD.get();
        ticketUpdate = ticketRepository.save(t);

        return ticketUpdate;

    }

    @Override
    public Optional<Ticket> getTicketById(Integer id) {

        return ticketRepository.findById(id);
    }
}
