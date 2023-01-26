package io.openlibrary.policy;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;

@Getter
public class LibraryPolicy {
    private static final LibraryPolicy libraryPolicy = new LibraryPolicy();
    private LibraryPolicy() {
        this.allowedBookLentExtensionCount = 3;
        this.reservationMarginTimestamp = daysToTimestamp(3);
    }

    @NotBlank
    private final Integer allowedBookLentExtensionCount;
    @NotBlank
    private final Long reservationMarginTimestamp;

    @Bean
    LibraryPolicy libraryPolicy() {
        return libraryPolicy;
    }

    private Long daysToTimestamp(int day) {
        return (long)day * (1000 * 60 * 24);
    }

}
