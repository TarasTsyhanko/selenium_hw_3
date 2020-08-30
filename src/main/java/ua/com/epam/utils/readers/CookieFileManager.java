package ua.com.epam.utils.readers;

import org.codehaus.jackson.map.ObjectMapper;
import org.openqa.selenium.Cookie;
import ua.com.epam.utils.entity.MyCookie;
import ua.com.epam.utils.config.ConfigProperties;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CookieFileManager {
    public static List<Cookie> readFile(String fileName) {
        MyCookie[] cookies = new MyCookie[0];
        try {
            cookies = new ObjectMapper()
                    .readValue(new File(ConfigProperties.getCookieDirectoryPath() + fileName), MyCookie[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Cookie> cookieList = new ArrayList<>();
        Date expiryTime = new Date(System.currentTimeMillis() + 100000000);
        Arrays.stream(cookies).forEach(c -> {
            Cookie cookie = new Cookie(c.getName()
                    , c.getValue()
                    , c.getDomain()
                    , c.getPath()
                    , expiryTime
                    , c.isSecure()
                    , c.isHttpOnly());
            cookieList.add(cookie);
        });
        return cookieList;
    }
}
