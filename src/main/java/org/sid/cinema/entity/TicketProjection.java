package org.sid.cinema.entity;

import org.springframework.data.rest.core.config.Projection;


@Projection(name = "ticketProj", types = Ticket.class)
public interface TicketProjection {
    Long getId();
    String getNameClient();
    double getPrice();
    String getPaymentCode();
    boolean getReserve();
    Seat getSeat();
}
