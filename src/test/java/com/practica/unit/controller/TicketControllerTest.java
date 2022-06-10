package com.practica.unit.controller;

import com.practica.controller.TicketController;
import com.practica.exception.RequestException;
import com.practica.persistence.entity.Ticket;
import com.practica.service.TicketServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TicketController.class)
public class TicketControllerTest {

    @Autowired
    TicketController ticketController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketServiceImpl ticketService;

    @Test
    public void getTicketOk() throws Exception{

        Ticket ticket = new Ticket();
        ticket.setId(1);
        ticket.setTitle("Titulo de prueba");
        ticket.setDetail("Detalle de prueba");
        ticket.setEstimate(3.14f);

        when(ticketService.getTicketById(1)).thenReturn(Optional.of(ticket));

        mockMvc.perform(MockMvcRequestBuilders.get("/practico/ticket/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Titulo de prueba"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.detail").value("Detalle de prueba"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.estimate").value(3.14))
                .andExpect(status().isOk());
    }

    @Test
    public void getTicketNotFound() throws Exception{

        when(ticketService.getTicketById(1)).thenThrow(new RequestException("No existe un ticket con el id ingresado", HttpStatus.NOT_FOUND));

        mockMvc.perform(MockMvcRequestBuilders.get("/practico/ticket/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("No existe un ticket con el id ingresado"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(404))
                .andExpect(status().isNotFound());
    }

    @Test
    public void saveOk() throws Exception{

        Ticket ticketReq = new Ticket();
        ticketReq.setId(1);
        ticketReq.setTitle("Titulo de prueba");
        ticketReq.setDetail("Detalle de prueba");
        ticketReq.setEstimate(3.14f);

        Ticket ticketRes = new Ticket();

    }

}
