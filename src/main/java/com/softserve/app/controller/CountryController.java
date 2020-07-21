package com.softserve.app.controller;

import com.softserve.app.entity.Country;
import com.softserve.app.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/countries")
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class CountryController {
    private CountryService countryService;

    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public String listCountries(Model model) {

        model.addAttribute("countries", countryService.listCountries());
        return "country/countries";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("country", new Country());
        return "country/create";
    }

    @PostMapping("/create")
    public String addCountry(@Valid Country country, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "country/create";
        }
        if (country.getId() == null) {
            countryService.addCountry(country);
        } else {
            countryService.updateCountry(country);

        }
        model.addAttribute("countries", countryService.listCountries());
        return "redirect:/countries";
    }

    @GetMapping("/remove/{id}")
    public String removeCountry(@PathVariable("id") Long id) {
        countryService.removeCountry(id);
        return "redirect:/countries";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        Country country = countryService.getCountryById(id);

        if (country == null) {
            return "redirect:/countries";
        }
        model.addAttribute("country", country);
        return "country/edit";
    }

    @PostMapping("/update/{id}")
    public String updateCountry(@PathVariable("id") Long id, @Valid Country country, BindingResult result) {
        if (result.hasErrors()) {
            return "country/edit";
        }
        countryService.updateCountry(country);
        return "redirect:/countries";
    }

    @GetMapping("/get/{id}")
    public String getCountry(@PathVariable("id") Long id, Model model) {
        model.addAttribute("country", countryService.getCountryById(id));
        return "country/country-data";
    }

}
