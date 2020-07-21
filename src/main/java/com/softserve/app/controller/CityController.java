package com.softserve.app.controller;

import com.softserve.app.entity.City;
import com.softserve.app.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cities")
public class CityController {
    private CityService cityService;

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public String listCities(Model model, @RequestParam(value = "country-name") String country) {
        if (country == null) {
            model.addAttribute("cities", cityService.listCities());
        } else {
            model.addAttribute("cities", cityService.getCitiesByCountryName(country));
        }
        return "city/cities";
    }

   /* @GetMapping
    public String listCitiesOfCountry(Model model, @RequestParam("country-name") String country) {

        model.addAttribute("cities", cityService.getCitiesByCountryName(country));
        return "city/cities"//////;
    }*/

    @GetMapping("/create")
    public String createForm(Model model) {

       /* for (String countryName : cityService.listCities().stream().map(x -> x.getCountry().getName()).distinct().collect(Collectors.toList())) {
            model.addAttribute(countryName, filterByCountry(cityService.listCities(), countryName));
            System.out.println(countryName + " " + filterByCountry(cityService.listCities(), countryName));
        }*/

        model.addAttribute("allCountries", cityService.listCities().stream().map(City::getCountry).distinct().collect(Collectors.toList()));
        model.addAttribute("city", new City());
        return "city/create";
    }

    /*private List<City> filterByCountry(
            List<City> cities, String countryName) {
        return cities
                .stream()
                .filter(x -> x.getCountry().getName().equals(countryName))
                .collect(Collectors.toList());
    }*/

    @PostMapping("/create")
    public String addCity(@Valid @ModelAttribute("city") City city, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "city/create";
        }
        cityService.addCity(city);


        model.addAttribute("cities", cityService.listCities());
        return "redirect:/cities";
    }

    @GetMapping("/remove/{id}")
    public String removeCity(@PathVariable("id") Long id) {
        cityService.removeCity(id);
        return "redirect:/cities";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        City city = cityService.getCityById(id);

        if (city == null) {
            return "redirect:/cities";
        }
        model.addAttribute("city", city);
        return "city/edit";
    }

    @PostMapping("/update/{id}")
    public String updateCity(@PathVariable("id") Long id, @Valid City city, BindingResult result) {
        if (result.hasErrors()) {
            return "city/edit";
        }
        cityService.updateCity(city);
        return "redirect:/cities";
    }

    @GetMapping("/get/{id}")
    public String getCity(@RequestParam(value = "country-name") String country, @PathVariable("id") Long id, Model model) {
        City city = cityService.getCityById(id);
        if (city.getCountry().getName().equals(country)) {
            model.addAttribute("city", city);
        } else {
            throw new NoSuchElementException("not found");
        }
        return "city/city-data";
    }

}


