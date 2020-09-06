package ua.com.epam.utils;

import ua.com.epam.utils.entity.GmailCredentials;
import ua.com.epam.utils.entity.Letter;
import ua.com.epam.utils.entity.User;
import ua.com.epam.utils.config.ConfigProperties;
import ua.com.epam.utils.readers.FileReader;

import java.util.List;
import java.util.stream.Collectors;

public class FileManager {

    public static List<User> getUsers() {
        return FileReader.readListFile(ConfigProperties.getUsersFilePath(), User.class);
    }

    public static List<Letter> getLetters() {
        return FileReader.readListFile(ConfigProperties.getLetterFilePath(), Letter.class)
                .stream().limit(ConfigProperties.getSizeOfMarkMessages()).collect(Collectors.toList());
    }

    public static GmailCredentials getApiCredentials() {
        return FileReader.readFile(ConfigProperties.getUserApiFilePath(), GmailCredentials.class);
    }

    public static User getUserAPI() {
        return FileReader.readFile(ConfigProperties.getUserApiFilePath(), User.class);
    }
}
