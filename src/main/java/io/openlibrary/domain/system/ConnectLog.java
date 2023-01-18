package io.openlibrary.domain.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ConnectLog {
    @Id
    private Long connectLogId;
    @Id
    private String uri;
    @Column
    private Long requestTimestamp;
    @Column
    private Long responseTimestamp;
    @Column(length = 500)
    private String requestMessage;
    @Column(length = 500)
    private String responseMessage;
}
