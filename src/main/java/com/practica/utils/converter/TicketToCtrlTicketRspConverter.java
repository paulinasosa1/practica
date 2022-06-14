package com.practica.utils.converter;

import com.practica.controller.dto.CtrlTicketRsp;
import com.practica.persistence.entity.Ticket;
import org.springframework.core.convert.converter.Converter;


public class TicketToCtrlTicketRspConverter implements Converter<Ticket, CtrlTicketRsp> {

    @Override
    public CtrlTicketRsp convert(Ticket ticket) {

        CtrlTicketRsp ctrlTicketRsp = new CtrlTicketRsp();
        ctrlTicketRsp.setId(ticket.getId());
        ctrlTicketRsp.setTitle(ticket.getTitle());
        ctrlTicketRsp.setDetail(ticket.getDetail());
        ctrlTicketRsp.setEstimate(ticket.getEstimate());

        return ctrlTicketRsp;
    }
}
