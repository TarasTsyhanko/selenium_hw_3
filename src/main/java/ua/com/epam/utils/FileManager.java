package ua.com.epam.utils;

import ua.com.epam.utils.entity.GmailCredentials;
import ua.com.epam.utils.entity.Letter;
import ua.com.epam.utils.entity.User;
import ua.com.epam.config.ConfigProperties;
import ua.com.epam.utils.readers.FileReader;

import java.util.List;

public class FileManager {

    public static List<User> getUsers() {
        return FileReader.readListFile(ConfigProperties.getUsersFilePath(), User.class);
    }

    public static List<Letter> getLetters() {
        return FileReader.readListFile(ConfigProperties.getLetterFilePath(), Letter.class);
    }

    public static User getUserAPI() {
        return FileReader.readFile(ConfigProperties.getUserApiFilePath(), User.class);
    }
}
