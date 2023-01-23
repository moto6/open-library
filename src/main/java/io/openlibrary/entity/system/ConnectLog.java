package io.openlibrary.entity.system;

import io.openlibrary.common.util.CommonUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "CONNECT_LOG")
@EqualsAndHashCode
public class ConnectLog {
    @Id
    @GeneratedValue
    @Column(name = "CONNECT_LOG_ID")
    private Long connectLogId;
    @Column(name = "REQUEST_ID")
    private Long requestId;
    @Column
    private Long requestTimestamp;
    @Column
    private Long responseTimestamp;
    @Column
    private Long durationMills;
    @Column(length = 500)
    private String requestHeader;
    @Column(length = 500)
    private String requestBody;
    @Column(length = 500)
    private String responseBody;

    public ConnectLog(Long requestId, Long requestTimestamp, Long responseTimestamp, String requestHeader, String requestBody, String responseBody) {
        this.requestId = requestId;
        this.requestTimestamp = requestTimestamp;
        this.responseTimestamp = responseTimestamp;
        this.durationMills = responseTimestamp - requestTimestamp;
        this.requestHeader = requestHeader;
        this.requestBody = requestBody;
        this.responseBody = responseBody;
    }

    public ConnectLog(Long requestTimestamp, Long responseTimestamp, CommonUtils commonUtils, ProceedingJoinPoint joinPoint) {
        HttpServletRequest request = commonUtils.getHttpServletRequest();
        HttpServletResponse response = (HttpServletResponse)joinPoint.getArgs()[1];
        this.requestId = (long) request.getAttribute("X-Request-ID");
        this.requestHeader = commonUtils.requestHeader(request);
        this.requestBody = commonUtils.requestBody(request);
        this.responseBody = commonUtils.responseBody(response);

        this.requestTimestamp = requestTimestamp;
        this.responseTimestamp = responseTimestamp;
        this.durationMills = responseTimestamp - requestTimestamp;
    }
}
