package io.github.sephnescence.learningspring.web;

import io.github.sephnescence.learningspring.business.ReservationService;
import io.github.sephnescence.learningspring.business.RoomReservation;
import io.github.sephnescence.learningspring.util.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/reservations")
public class RoomReservationController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    public RoomReservationController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getReservations(
        @RequestParam(value = "date", required = false)
        String dateString,
        Model model
    ) {
        /*
        I've noted that you can pass in 2022-02-30 for example, and dateString doesn't fail validation of any kind,
            and date resolves to Mar 2

        Additionally, if you provide something like "invalid", it returns the current date
         */
        Date date = dateUtils.createDateFromDateString(dateString);
        List<RoomReservation> reservations = reservationService.getRoomReservationsForDate(date);
        model.addAttribute("roomReservations", reservations);

        return "roomres";
    }
}
