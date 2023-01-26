package io.openlibrary.entity.domain;

import io.openlibrary.entity.domain.enums.ReservationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

import static io.openlibrary.entity.domain.enums.ReservationStatus.OCCUPIED;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "RESERVATION")
public class Reservation {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "RESERVATION_ID", columnDefinition = "BINARY(16)")
    private UUID reservationId;
    @Column(name = "RESERVATION_TIMESTAMP", updatable = false)
    private Long reservationTimestamp;//예약일
    @NotNull
    @Column(name = "BOOK_STOCK_ID")
    private Long bookStockId;
    @NotNull
    @Column(name = "ACCOUNT_ID")
    private Long accountId;
    @Column(name = "RESERVATION_STATUS")
    private ReservationStatus reservationStatus;

    public Reservation(Accounts accounts, BookStock bookStock) {

    }

    public Reservation(Long bookStockId, Long accountId) {
        this.bookStockId = bookStockId;
        this.accountId = accountId;
        this.reservationTimestamp = Instant.now().getEpochSecond();
        this.reservationStatus = OCCUPIED;
    }
}
