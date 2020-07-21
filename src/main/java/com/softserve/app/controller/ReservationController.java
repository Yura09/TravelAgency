package com.softserve.app.controller;

import com.softserve.app.entity.Reservation;
import com.softserve.app.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    private ReservationService reservationService;
    @Autowired
    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    @GetMapping
    public String listReservations(Model model, @RequestParam(value = "hotel-name", required = false) String hotel) {
        if (hotel == null) {

            model.addAttribute("reservations", reservationService.listReservations());
        }
         else {
            model.addAttribute("reservations", reservationService.getReservationsByHotelName(hotel));
        }
        return "reservation/reservations";
    }


    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "reservation/create";
    }

    @PostMapping("/create")
    public String addReservation(@Valid Reservation reservation, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "reservation/create";
        }
        if (reservation.getId() == null) {
            reservationService.addReservation(reservation);
        } else {
            reservationService.updateReservation(reservation);

        }
        model.addAttribute("reservations", reservationService.listReservations());
        return "redirect:/reservations";
    }

    @GetMapping("/remove/{id}")
    public String removeReservation(@PathVariable("id") Long id) {
        reservationService.removeReservation(id);
        return "redirect:/reservations";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        Reservation reservation = reservationService.getReservationById(id);

        if (reservation == null) {
            return "redirect:/reservations";
        }
        model.addAttribute("reservation", reservation);
        return "reservation/edit";
    }

    @PostMapping("/update/{id}")
    public String updateHotel(@PathVariable("id") Long id, @Valid Reservation reservation, BindingResult result) {
        if (result.hasErrors()) {
            return "reservation/edit";
        }
        reservationService.updateReservation(reservation);
        return "redirect:/reservations";
    }

    @GetMapping("/get/{id}")
    public String showReservation(@PathVariable("id") Long id, Model model) {

        model.addAttribute("reservation", reservationService.getReservationById(id));

        return "reservation/reservation-data";
    }
}
