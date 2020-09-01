package ua.com.epam.utils.readers;

import org.openqa.selenium.Cookie;
import ua.com.epam.utils.entity.Letter;
import ua.com.epam.utils.entity.MyCookie;
import ua.com.epam.utils.entity.User;
import ua.com.epam.utils.config.ConfigProperties;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileManager {

    public static List<User> getUsers() {
        List<User> userList = FileReader.readFile(ConfigProperties.getUsersFilePath(), User.class);
        userList.forEach(user -> user.setCookies(getCoolies(user.getCookieFileName())));
        return userList;
    }

    public static List<Letter> getLetters() {
        return FileReader.readFile(ConfigProperties.getLetterFilePath(), Letter.class);
    }

    private static List<Cookie> getCoolies(String path) {
        List<Cookie> cookieList = new ArrayList<>();
        List<MyCookie> myCookies = FileReader.readFile(ConfigProperties.getCookieDirectoryPath() + path, MyCookie.class);
        Date expiryTime = new Date(System.currentTimeMillis() + 100000000);
        myCookies.forEach(c -> {
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
