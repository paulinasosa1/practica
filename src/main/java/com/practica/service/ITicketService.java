package com.practica.service;

import com.practica.controller.dto.CtrlTicketRsp;
import com.practica.persistence.entity.Ticket;

public interface ITicketService {

    public CtrlTicketRsp save(Ticket t);
    public CtrlTicketRsp update(Integer id, Ticket t);
    public CtrlTicketRsp getTicketById(Integer id);
}
