package com.practica.controllers;

import com.practica.entities.Ticket;
import com.practica.services.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/practico/ticket")
public class TicketController {

    @Autowired
    ITicketService ticketService;

    @PostMapping("")
    public ResponseEntity<?>save(@RequestBody Ticket ticket){

        Ticket ticketResponse = ticketService.save(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?>update(@PathVariable Integer id, @RequestBody Ticket ticket){

        Ticket ticketResponse = ticketService.update(id,ticket);
        return ResponseEntity.status(HttpStatus.OK).body(ticketResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable Integer id){

        Optional<Ticket> ticketResponse = ticketService.getTicketById(id);
        return ResponseEntity.status(HttpStatus.OK).body(ticketResponse);
    }


}
