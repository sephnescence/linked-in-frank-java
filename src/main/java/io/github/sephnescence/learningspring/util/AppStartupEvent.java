package io.github.sephnescence.learningspring.util;

import io.github.sephnescence.learningspring.data.*;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/*
 * We can specify ApplicationListener<ApplicationReadyEvent> to stipulate that we must wait for Spring to
 * have the database ready before we query the database
 */

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    /*
     * because RoomRepository is final, we need to use Inversion of Control to set it in the constructor
     */

    public AppStartupEvent(
        RoomRepository roomRepository,
        GuestRepository guestRepository,
        ReservationRepository reservationRepository
    ) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // findAll comes from CrudRepository, so we never had to define it
        Iterable<Room> rooms = this.roomRepository.findAll();
        rooms.forEach(System.out::println); // On cool. We don't need to do System.out.println(room)

        Iterable<Guest> guests = this.guestRepository.findAll();
        guests.forEach(System.out::println);

        Iterable<Reservation> reservations = this.reservationRepository.findAll();
        reservations.forEach(System.out::println);

        /*
         * Now when we run `mvn clean package` we can see rows like
         *  Room{id=1, name='Piccadilly', roomNumber='P1', bedInfo='1Q'}
         */
    }
}
