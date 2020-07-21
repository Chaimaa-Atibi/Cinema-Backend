package org.sid.cinema.controller;

import org.sid.cinema.dao.*;
import org.sid.cinema.entity.Movie;
import org.sid.cinema.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class CinemaRestController {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private CityRepository cityRepository;

    @GetMapping(value = "moviePhoto/{idMovie}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPhotoOfMovie(@PathVariable("idMovie") Long id) throws IOException {
        Movie movie = movieRepository.findById(id).get();
        if (movie != null) {
            String photoName = movie.getPhoto();

            String photoPath = "/cinema/images/"+photoName+".JPG";

            File file =
                    new File(System.getProperty("user.home") + photoPath);

            Path path = Paths.get(file.toURI());

            return Files.readAllBytes(path);
        }
        return null;
    }

    @PostMapping("buyTickets")
    @Transactional
    public List<Ticket> buyTicket(@RequestBody TicketForm ticketForm) {
        List<Ticket> tickets = new ArrayList<>();

        ticketForm.getTickets().forEach( idTicket -> {
            Ticket ticket = ticketRepository.findById(idTicket).get();
            ticket.setNameClient(ticketForm.getNameClient());
            ticket.setReserve(true);
            ticket.setPaymentCode(ticketForm.getCodePayment());
            Ticket savedTicket = ticketRepository.save(ticket);
            tickets.add(savedTicket);
        });
        return tickets;
    }

    @DeleteMapping("deleteCinemas/{id}/{idCity}")
    @Transactional
    public void deleteCinema(@PathVariable("id") Long id,
                             @PathVariable("idCity") Long idCity) {
        cinemaRepository.deleteCinemaByIdAndCityId(id, idCity);
    }
}
class TicketForm {
    private List<Long> tickets = new ArrayList<>();
    private String nameClient;
    private String codePayment;

    public TicketForm() {
    }

    public TicketForm(List<Long> tickets, String nameClient, String codePayment) {
        this.tickets = tickets;
        this.nameClient = nameClient;
        this.codePayment = codePayment;
    }

    public List<Long> getTickets() {
        return tickets;
    }

    public void setTickets(List<Long> tickets) {
        this.tickets = tickets;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getCodePayment() {
        return codePayment;
    }

    public void setCodePayment(String codePayment) {
        this.codePayment = codePayment;
    }
}
