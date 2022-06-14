package com.practica.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CtrlTicketRsp {

    private Integer id;
    private String title;
    private String detail;
    private float estimate;

}
