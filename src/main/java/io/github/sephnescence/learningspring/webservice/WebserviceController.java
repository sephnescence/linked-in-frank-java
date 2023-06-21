package io.github.sephnescence.learningspring.webservice;

import io.github.sephnescence.learningspring.business.ReservationService;
import io.github.sephnescence.learningspring.business.RoomReservation;
import io.github.sephnescence.learningspring.data.Guest;
import io.github.sephnescence.learningspring.data.GuestRepository;
import io.github.sephnescence.learningspring.data.Room;
import io.github.sephnescence.learningspring.data.RoomRepository;
import io.github.sephnescence.learningspring.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebserviceController {
    private final DateUtils dateUtils;
    private final GuestRepository guestRepository;
    private final ReservationService reservationService;
    private final RoomRepository roomRepository;

    public WebserviceController(DateUtils dateUtils, GuestRepository guestRepository, ReservationService reservationService, RoomRepository roomRepository) {
        this.dateUtils = dateUtils;
        this.guestRepository = guestRepository;
        this.reservationService = reservationService;
        this.roomRepository = roomRepository;
    }

    /*
    Note here that you can annotate with @GetMapping or add `method = RequestMethod.GET`
     */
    @GetMapping(path = "/reservations")
    @RequestMapping(path = "/reservations", method = RequestMethod.GET)
    public List<RoomReservation> reservations(
        @RequestParam(name = "date", required = false)
        String dateString
    ) {
        Date date = dateUtils.createDateFromDateString(dateString);
        return reservationService.getRoomReservationsForDate(date);
    }

    @GetMapping
    @RequestMapping(path = "/guests")
    public List<Guest> guests() {
        return (List<Guest>) guestRepository.findAll();
    }

    /*
    I've committed an insomnia file as well to help show this working
     */
    @PostMapping(path="/guests")
    @ResponseStatus(HttpStatus.CREATED)
    public void addGuest(
        @RequestBody Guest guest
    ) {
        guestRepository.save(guest);
    }

    @GetMapping(path = "/rooms")
    public List<Room> rooms() {
        return (List<Room>) roomRepository.findAll();
    }
}
