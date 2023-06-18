package io.github.sephnescence.learningspring.util;

import io.github.sephnescence.learningspring.business.ReservationService;
import io.github.sephnescence.learningspring.business.RoomReservation;
import io.github.sephnescence.learningspring.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/*
 * We can specify ApplicationListener<ApplicationReadyEvent> to stipulate that we must wait for Spring to
 * have the database ready before we query the database
 */

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    private final ReservationService reservationService;
    private final DateUtils dateUtils;

    /*
     * because RoomRepository is final, we need to use Inversion of Control to set it in the constructor
     */

    public AppStartupEvent(
        RoomRepository roomRepository,
        GuestRepository guestRepository,
        ReservationRepository reservationRepository,
        ReservationService reservationService,
        DateUtils dateUtils
    ) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
        this.dateUtils = dateUtils;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // findAll comes from CrudRepository, so we never had to define it
        Iterable<Room> rooms = this.roomRepository.findAll();
        rooms.forEach(System.out::println); // On cool. We don't need to do System.out.println(room)

        Iterable<Guest> guests = this.guestRepository.findAll();
        guests.forEach(System.out::println);

        System.out.println("All reservations");
        Iterable<Reservation> reservations = this.reservationRepository.findAll();
        reservations.forEach(System.out::println);

        String reservationDate = "2022-01-01";
        System.out.println("Reservations for date - " + reservationDate);
        Date date = this.dateUtils.createDateFromDateString(reservationDate);
        List<RoomReservation> reservationsForDate = this.reservationService.getRoomReservationsForDate(date);
        reservationsForDate.forEach(System.out::println);

        /*
         * Now when we run `mvn clean package` we can see rows like
         *  Room{id=1, name='Piccadilly', roomNumber='P1', bedInfo='1Q'}
         *  Guest...
         *  All reservations...
         *  Reservation...
         *  Reservations for date...
         *  Reservation...
         */
    }
}
