package com.practica.service;

import com.practica.controller.dto.CtrlTicketRsp;
import com.practica.exception.RequestException;
import com.practica.persistence.entity.Ticket;
import com.practica.repositories.ITicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketServiceImpl implements ITicketService{

    @Autowired
    ITicketRepository ticketRepository;

    @Autowired
    ConversionService convertService;

    @Override
    public CtrlTicketRsp save(Ticket t) {

        Ticket ticket = ticketRepository.save(t);
        return this.convertService.convert(ticket, CtrlTicketRsp.class);

    }

    @Override
    public CtrlTicketRsp update(Integer id, Ticket t) {

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

        return this.convertService.convert(ticketUpdate, CtrlTicketRsp.class);
    }

    @Override
    public CtrlTicketRsp getTicketById(Integer id) {

        Optional<Ticket> ticket = ticketRepository.findById(id);
        if(!ticket.isPresent()) throw new RequestException("No existe un ticket con el id ingresado", HttpStatus.NOT_FOUND);


        return this.convertService.convert(ticket.get(), CtrlTicketRsp.class);
    }
}
