package ua.com.epam.utils.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class GmailCredentials {
    private String userEmail;
    private String clientId;
    private String clientSecret;
    private String accessToken;
    private String refreshToken;
}
