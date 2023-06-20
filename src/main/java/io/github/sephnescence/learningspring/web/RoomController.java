package io.github.sephnescence.learningspring.web;

import io.github.sephnescence.learningspring.data.Room;
import io.github.sephnescence.learningspring.data.RoomRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getRooms(
        Model model
    ) {
        List<Room> rooms = (List<Room>) roomRepository.findAll();
        model.addAttribute("rooms", rooms);

        return "rooms";
    }
}
