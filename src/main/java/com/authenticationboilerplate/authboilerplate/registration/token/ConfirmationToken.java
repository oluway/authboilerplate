package com.authenticationboilerplate.authboilerplate.registration.token;

import com.authenticationboilerplate.authboilerplate.appUser.AppUser;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {

    public ConfirmationToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiresAt,
                             AppUser appUser) {
        this.token = token;
        this.expiresAt = expiresAt;
        this.createdAt = createdAt;
        this.appUser = appUser;
    }

    @Id
    @SequenceGenerator(
            name = "auth_token_sequence",
            sequenceName = "auth_token_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "auth_token_sequence")
    private Long id;
    @Column(nullable = false)
    private String token;
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )

    private AppUser appUser;

}
