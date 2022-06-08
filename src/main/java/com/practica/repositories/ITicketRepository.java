package com.practica.repositories;

import com.practica.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket, Integer> {
}
