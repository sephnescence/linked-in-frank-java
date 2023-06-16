package io.github.sephnescence.learningspring.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    // These are also possible
    // PagingAndSortingRepository
    // JpaRepository

    // When defining a repo method, you can just do something like this
    Iterable<Reservation> findReservationByReservationDate(Date date); // java.sql.Date was suggested by Frank, but we can also use java.util.Date
}
