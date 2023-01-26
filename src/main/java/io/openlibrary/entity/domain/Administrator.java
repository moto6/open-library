package io.openlibrary.entity.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@ToString
@Table(name = "ADMINISTRATOR", indexes = {
        @Index(name = "IDX_ADMINISTRATOR_CODE", columnList = "ADMINISTRATOR_CODE", unique = true)
})
@NoArgsConstructor
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADMINISTRATOR_ID")
    private Long administratorId;
    @NotBlank
    @Column(name = "ADMINISTRATOR_CODE", nullable = false, unique = true)
    private String administratorCode;

    public Administrator(String administratorCode) {
        this.administratorCode = administratorCode;
    }
}
