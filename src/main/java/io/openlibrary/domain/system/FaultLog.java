package io.openlibrary.domain.system;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class FaultLog {
    @Id
    private Long faultLogId;
    @NotEmpty
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;
    @Column
    private Long timestamp;
    @Column(length = 500)
    private String data;

    public FaultLog(Long transactionId, String data) {
        this.transactionId = transactionId;
        this.timestamp = Instant.now().getEpochSecond();
        this.data = data;
    }
}
