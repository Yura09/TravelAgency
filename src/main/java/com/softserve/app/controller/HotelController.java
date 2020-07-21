package com.softserve.app.controller;

import com.softserve.app.entity.Hotel;
import com.softserve.app.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/hotels")
public class HotelController {
    private HotelService hotelService;

    @Autowired
    public void setHotelService(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public String listHotels(Model model, @RequestParam(value = "city-name", required = false) String city
            , @RequestParam(value = "country-name", required = false) String country) {
        if (city != null) {

            model.addAttribute("hotels", hotelService.getHotelsByCityName(city));
        } else if (country != null) {
            model.addAttribute("hotels", hotelService.getHotelsByCountryName(country));

        } else {
            model.addAttribute("hotels", hotelService.listHotels());
        }
        return "hotel/hotels";
    }


    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "hotel/create";
    }

    @PostMapping("/create")
    public String addHotel(@Valid Hotel hotel, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "hotel/create";
        }
        if (hotel.getId() == null) {
            hotelService.addHotel(hotel);
        } else {
            hotelService.updateHotel(hotel);

        }
        model.addAttribute("hotels", hotelService.listHotels());
        return "redirect:/hotels";
    }

    @GetMapping("/remove/{id}")
    public String removeHotel(@PathVariable("id") Long id) {
        hotelService.removeHotel(id);
        return "redirect:/hotels";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        Hotel hotel = hotelService.getHotelById(id);

        if (hotel == null) {
            return "redirect:/hotels";
        }
        model.addAttribute("hotel", hotel);
        return "hotel/edit";
    }

    @PostMapping("/update/{id}")
    public String updateHotel(@PathVariable("id") Long id, @Valid Hotel hotel, BindingResult result) {
        if (result.hasErrors()) {
            return "hotel/edit";
        }
        hotelService.updateHotel(hotel);
        return "redirect:/hotels";
    }

    @GetMapping("/get/{id}")
    public String showHotel(@PathVariable("id") Long id, Model model) {
     //   hotelService.getHotelById(id);
        //lazy initialization exception
        //List<Hotel> hotels = hotelService.listHotels();
       // Hotel hotel = hotels.stream().filter(i -> i.getId().compareTo(id) == 0).findAny().orElse(null);
        model.addAttribute("hotel", hotelService.getHotelById(id));

        return "hotel/hotel-data";
    }
}
