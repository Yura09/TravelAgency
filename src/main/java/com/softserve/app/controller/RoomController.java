package com.softserve.app.controller;

import com.softserve.app.entity.Room;
import com.softserve.app.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    private RoomService roomService;

    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    //  @DateTimeFormat(pattern="yyyy-MM-dd")
    //end=2020-07-20
    @GetMapping
    public String listRooms(Model model, @RequestParam(name = "hotel-id", required = false) Long hotelId,
                            @RequestParam(name = "start", required = false)
                                    Date start,
                            @RequestParam(name = "end", required = false)
                                    Date end
    ) {

        if (hotelId == null) {
            model.addAttribute("rooms", roomService.listRooms());
        } else {
            List<Room> rooms = roomService.availableRoomsForPeriodInHotel(hotelId, start, end);
            if (rooms == null) {
                model.addAttribute("rooms", roomService.listRooms());
            } else {
                model.addAttribute("rooms", rooms);
            }
        }
        return "room/rooms";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("room", new Room());
        return "room/create";
    }

    @PostMapping("/create")
    public String addRoom(@Valid Room room, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "room/create";
        }
        if (room.getId() == null) {
            roomService.addRoom(room);
        } else {
            roomService.updateRoom(room);

        }
        model.addAttribute("rooms", roomService.listRooms());
        return "redirect:/rooms";
    }

    @GetMapping("/remove/{id}")
    public String removeRoom(@PathVariable("id") Long id) {
        roomService.removeRoom(id);
        return "redirect:/rooms";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        Room room = roomService.getRoomById(id);

        if (room == null) {
            return "redirect:/rooms";
        }
        model.addAttribute("room", room);
        return "room/edit";
    }

    @PostMapping("/update/{id}")
    public String updateRoom(@PathVariable("id") Long id, @Valid Room room, BindingResult result) {
        if (result.hasErrors()) {
            return "room/edit";
        }
        roomService.updateRoom(room);
        return "redirect:/rooms";
    }

    @GetMapping("/get/{id}")
    public String getRoom(@PathVariable("id") Long id, Model model) {
        model.addAttribute("room", roomService.getRoomById(id));
        return "room/room-data";
    }
}
