package io.openlibrary.entity.repositroy;

import io.openlibrary.entity.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
