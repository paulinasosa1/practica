package com.practica.service;

import com.practica.exception.RequestException;
import com.practica.persistence.entity.Ticket;
import com.practica.repositories.ITicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        if(!ticketBD.isPresent()) throw new RequestException("El ticket que se quiere modificar no existe", HttpStatus.NOT_FOUND);

        Ticket ticketUpdate = new Ticket();
        if(ticketBD.isPresent()){
            ticketUpdate = ticketBD.get();
            ticketUpdate.setTitle(t.getTitle());
            ticketUpdate.setDetail(t.getDetail());
            ticketUpdate.setEstimate(t.getEstimate());
            ticketRepository.save(ticketUpdate);
        }

        return ticketUpdate;
    }

    @Override
    public Optional<Ticket> getTicketById(Integer id) {

        Optional<Ticket> ticket = ticketRepository.findById(id);
        if(!ticket.isPresent()) throw new RequestException("No existe un ticket con el id ingresado", HttpStatus.NOT_FOUND);

        return ticket;
    }
}
