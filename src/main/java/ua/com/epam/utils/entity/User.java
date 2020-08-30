package ua.com.epam.utils.entity;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.openqa.selenium.Cookie;

import java.util.List;

@Data
public class User {
    private String fullName;
    private String login;
    private String password;
    private String cookieFileName;
    @JsonIgnore
    private List<Cookie> cookies;
}
