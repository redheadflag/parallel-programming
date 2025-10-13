package utils;

import java.text.SimpleDateFormat;

public class Logger {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String RESET = "\u001B[0m";

    public enum LogLevel {
        DEBUG,
        INFO,
        WARN,
        ERROR
    }

    private static void log(String message, LogLevel level) {
        String date = dateFormat.format(System.currentTimeMillis());
        String name = Thread.currentThread().getName();

        String messagePrefix = "[" + date + "]" + "[" + name + "] " + level + ": ";

        switch (level) {
            case DEBUG -> messagePrefix = BLUE + messagePrefix + RESET;
            case INFO -> messagePrefix = GREEN + messagePrefix + RESET;
            case WARN -> messagePrefix = YELLOW + messagePrefix + RESET;
            case ERROR -> messagePrefix = RED + messagePrefix + RESET;
        }

        System.out.println(messagePrefix + " " + message);
    }

    public static void debug(String message) {
        log(message, LogLevel.DEBUG);
    }

    public static void info(String message) {
        log(message, LogLevel.INFO);
    }

    public static void warn(String message) {
        log(message, LogLevel.WARN);
    }

    public static void error(String message) {
        log(message, LogLevel.ERROR);
    }
}
