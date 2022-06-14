package com.practica.unit.service;

import com.practica.controller.dto.CtrlTicketRsp;
import com.practica.persistence.entity.Ticket;
import com.practica.repositories.ITicketRepository;
import com.practica.service.TicketServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import static org.mockito.Mockito.when;

@WebMvcTest(TicketServiceImpl.class)
public class TicketServiceTest {

    @Autowired
    TicketServiceImpl ticketService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ITicketRepository ticketRepository;

    @Test
    public void getTicketOk(){

        Ticket ticket = new Ticket();
        ticket.setId(1);
        ticket.setTitle("Titulo de prueba");
        ticket.setDetail("Detalle de prueba");
        ticket.setEstimate(3.1f);

        when(ticketRepository.findById(1)).thenReturn(Optional.of(ticket));

        CtrlTicketRsp response = ticketService.getTicketById(1);

        assertEquals(ticket.getTitle(), response.getTitle());
        assertEquals(ticket.getDetail(), response.getDetail());
        assertEquals(ticket.getEstimate(), response.getEstimate());

    }

    @Test
    public void saveOk(){

        Ticket ticket = new Ticket();
        ticket.setTitle("Titulo de prueba");
        ticket.setDetail("Detalle de prueba");
        ticket.setEstimate(3.1f);

        Ticket ticketRes = new Ticket();
        ticketRes.setId(1);
        ticketRes.setTitle("Titulo de prueba");
        ticketRes.setDetail("Detalle de prueba");
        ticketRes.setEstimate(3.1f);

        when(ticketRepository.save(ticket)).thenReturn(ticketRes);

        CtrlTicketRsp response = ticketService.save(ticket);

        assertEquals(1, response.getId());
        assertEquals(ticket.getTitle(), response.getTitle());
        assertEquals(ticket.getDetail(), response.getDetail());
        assertEquals(ticket.getEstimate(), response.getEstimate());


    }

    @Test
    public void updateOk(){

        Ticket ticket = new Ticket();
        ticket.setTitle("Titulo de prueba");
        ticket.setDetail("Detalle de prueba");
        ticket.setEstimate(3.1f);

        Ticket ticketRes = new Ticket();
        ticketRes.setId(1);
        ticketRes.setTitle("Titulo de prueba");
        ticketRes.setDetail("Detalle de prueba");
        ticketRes.setEstimate(3.1f);

        when(ticketRepository.findById(1)).thenReturn(Optional.of(ticketRes));
        when(ticketRepository.save(ticket)).thenReturn(ticketRes);


        CtrlTicketRsp response = ticketService.update(1, ticket);

        assertEquals(1, response.getId());
        assertEquals(ticket.getTitle(), response.getTitle());
        assertEquals(ticket.getDetail(), response.getDetail());
        assertEquals(ticket.getEstimate(), response.getEstimate());
    }
}
