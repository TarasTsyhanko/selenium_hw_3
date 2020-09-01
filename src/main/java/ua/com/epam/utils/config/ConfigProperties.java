package ua.com.epam.utils.config;

public class ConfigProperties {
    public static String getBaseUrl() {
        return PropertyFile.getProperty("base_url");
    }

    public static String getDriverPath() {
        return PropertyFile.getProperty("driver_path");
    }

    public static String getChromeDriver() {
        return PropertyFile.getProperty("chrome_driver");
    }

    public static String getUsersFilePath() {
        return PropertyFile.getProperty("users_file_path");
    }

    public static String getCookieDirectoryPath() {
        return PropertyFile.getProperty("cookie_directory_path");
    }

    public static String getLogsFilePath() {
        return PropertyFile.getProperty("logs_file_path");
    }

    public static String getLetterFilePath() {
        return PropertyFile.getProperty("letter_file_path");
    }

    public static int getSizeOfMarkMessages() {
        return Integer.parseInt(PropertyFile.getProperty("size_of_mark_messages"));
    }

}
