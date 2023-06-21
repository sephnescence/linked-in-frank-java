package io.github.sephnescence.learningspring.webservice;

import io.github.sephnescence.learningspring.business.ReservationService;
import io.github.sephnescence.learningspring.business.RoomReservation;
import io.github.sephnescence.learningspring.util.DateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebserviceController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    public WebserviceController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    @RequestMapping(path = "/reservations")
    public List<RoomReservation> reservations(
        @RequestParam(name = "date", required = false)
        String dateString
    ) {
        Date date = dateUtils.createDateFromDateString(dateString);
        return reservationService.getRoomReservationsForDate(date);
    }
}
