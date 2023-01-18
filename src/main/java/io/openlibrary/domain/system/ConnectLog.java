package io.openlibrary.domain.system;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
public class ConnectLog {
    @Id
    private Long connectLogId;
    @Id
    private String uri;
    @Column
    private Long requestTimestamp;
    @Column
    private Long responseTimestamp;
    @Column
    private Long durationMills;
    @Column(length = 500)
    private String requestMessage;
    @Column(length = 500)
    private String responseMessage;

    public ConnectLog(String uri, Long requestTimestamp, Long responseTimestamp, String requestMessage, String responseMessage) {
        this.uri = uri;
        this.requestTimestamp = requestTimestamp;
        this.responseTimestamp = responseTimestamp;
        this.durationMills = responseTimestamp - requestTimestamp;
        this.requestMessage = requestMessage;
        this.responseMessage = responseMessage;
    }
}
