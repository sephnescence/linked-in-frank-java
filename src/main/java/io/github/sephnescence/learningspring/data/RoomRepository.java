package io.github.sephnescence.learningspring.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
 * We're only putting the @Repository annotation here to be absolutely clear that this is a repository
 * It is not required to have
 */
@Repository
public interface RoomRepository extends CrudRepository<Room, Long> { // You pass in the Entity class and the ID class
}
