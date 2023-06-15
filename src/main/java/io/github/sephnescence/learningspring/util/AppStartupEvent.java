package io.github.sephnescence.learningspring.util;

import io.github.sephnescence.learningspring.data.Room;
import io.github.sephnescence.learningspring.data.RoomRepository;
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

    /*
     * because RoomRepository is final, we need to use Inversion of Control to set it in the constructor
     */

    public AppStartupEvent(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // findAll comes from CrudRepository, so we never had to define it
        Iterable<Room> rooms = this.roomRepository.findAll();
        rooms.forEach(System.out::println); // On cool. We don't need to do System.out.println(room)

        /*
         * Now when we run `mvn clean package` we can see rows like
         *  Room{id=1, name='Piccadilly', roomNumber='P1', bedInfo='1Q'}
         */
    }
}
