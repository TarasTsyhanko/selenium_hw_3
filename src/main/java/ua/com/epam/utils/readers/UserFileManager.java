package ua.com.epam.utils.readers;

import org.codehaus.jackson.map.ObjectMapper;
import ua.com.epam.utils.entity.User;
import ua.com.epam.utils.config.ConfigProperties;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class UserFileManager {
    public static List<User> readFile() {
        User[] users = new User[0];
        try {
            users = new ObjectMapper().readValue(new File(ConfigProperties.getUsersFilePath()), User[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (User user : users) {
            user.setCookies(CookieFileManager.readFile(user.getCookieFileName()));
        }
        return Arrays.asList(users);
    }
}
