package com.practica.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practica.controller.TicketController;
import com.practica.controller.dto.CtrlTicketRsp;
import com.practica.exception.RequestException;
import com.practica.persistence.entity.Ticket;
import com.practica.service.TicketServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


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

        CtrlTicketRsp ticket = new CtrlTicketRsp();
        ticket.setId(1);
        ticket.setTitle("Titulo de prueba");
        ticket.setDetail("Detalle de prueba");
        ticket.setEstimate(3.1f);

        when(ticketService.getTicketById(1)).thenReturn(ticket);

        mockMvc.perform(MockMvcRequestBuilders.get("/practico/ticket/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Titulo de prueba"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.detail").value("Detalle de prueba"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.estimate").value(3.1))
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
        ticketReq.setTitle("Titulo de prueba");
        ticketReq.setDetail("Detalle de prueba");
        ticketReq.setEstimate(3.1f);

        CtrlTicketRsp ticketRes = new CtrlTicketRsp();
        ticketRes.setId(1);
        ticketRes.setTitle("Titulo de prueba");
        ticketRes.setDetail("Detalle de prueba");
        ticketRes.setEstimate(3.1f);

        when(ticketService.save(ticketReq)).thenReturn(ticketRes);


        ObjectMapper objectMapper = new ObjectMapper();
        String JSON = objectMapper.writeValueAsString(ticketReq);

        mockMvc.perform(MockMvcRequestBuilders.post("/practico/ticket/").content(JSON).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void saveErrorEstimate() throws Exception{

        Ticket ticketReq = new Ticket();
        ticketReq.setTitle("Titulo de prueba");
        ticketReq.setDetail("Detalle de prueba");
        ticketReq.setEstimate(3.1999999f);  //debería fallar por la cantidad de decimales

        ObjectMapper objectMapper = new ObjectMapper();
        String JSON = objectMapper.writeValueAsString(ticketReq);

        mockMvc.perform(MockMvcRequestBuilders.post("/practico/ticket/").content(JSON).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    public void saveErrorTitle() throws Exception{

        Ticket ticketReq = new Ticket();
        // ticketReq.setTitle("Titulo de prueba"); debería fallar porque el titulo es requerido
        ticketReq.setDetail("Detalle de prueba");
        ticketReq.setEstimate(3.1f);

        ObjectMapper objectMapper = new ObjectMapper();
        String JSON = objectMapper.writeValueAsString(ticketReq);

        mockMvc.perform(MockMvcRequestBuilders.post("/practico/ticket/").content(JSON).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    public void saveErrorDetail() throws Exception{

        Ticket ticketReq = new Ticket();
        ticketReq.setTitle("Titulo de prueba");
        // ticketReq.setDetail("Detalle de prueba"); debería fallar porque el detalle es requerido
        ticketReq.setEstimate(3.1f);

        ObjectMapper objectMapper = new ObjectMapper();
        String JSON = objectMapper.writeValueAsString(ticketReq);

        mockMvc.perform(MockMvcRequestBuilders.post("/practico/ticket/").content(JSON).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    public void updateOk() throws Exception{

        Ticket ticketReq = new Ticket();
        ticketReq.setTitle("Nuevo titulo");
        ticketReq.setDetail("Nuevo detalle");
        ticketReq.setEstimate(3.1f);

        CtrlTicketRsp ticketRes = new CtrlTicketRsp();
        ticketRes.setId(1);
        ticketRes.setTitle("Nuevo titulo");
        ticketRes.setDetail("Nuevo detalle");
        ticketRes.setEstimate(3.1f);

        when(ticketService.update(1, ticketReq)).thenReturn(ticketRes);

        ObjectMapper objectMapper = new ObjectMapper();
        String JSON = objectMapper.writeValueAsString(ticketReq);

        mockMvc.perform(MockMvcRequestBuilders.put("/practico/ticket/1").content(JSON).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

}
