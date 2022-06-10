package com.practica.controller;

import com.practica.exception.RequestException;
import com.practica.persistence.entity.Ticket;
import com.practica.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/practico/ticket")
public class TicketController {

    @Autowired
    ITicketService ticketService;

    @PostMapping("")
    public ResponseEntity<?>save(@Valid @RequestBody Ticket ticket) throws BindException {

        Ticket ticketResponse = ticketService.save(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?>update(@PathVariable Integer id, @Valid @RequestBody Ticket ticket){

        Ticket ticketResponse = ticketService.update(id,ticket);
        return ResponseEntity.status(HttpStatus.OK).body(ticketResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable Integer id){

       Optional<Ticket> ticketResponse = ticketService.getTicketById(id);
        return ResponseEntity.status(HttpStatus.OK).body(ticketResponse);
    }

}
