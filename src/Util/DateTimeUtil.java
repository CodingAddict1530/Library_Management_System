package Util;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeUtil {

    // ISO Offset Date Time Formatter
    private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    // Format Patterns
    public static final String PATTERN_1 = "yyyy-MM-dd HH:mm:ss OOOO";
    public static final String PATTERN_2 = "yyyy-MM-dd HH:mm:ss XXX";
    public static final String PATTERN_3 = "EEEE, MMM dd, yyyy HH:mm:ss OOOO";

    // Custom Formatters
    private static final DateTimeFormatter CUSTOM_FORMATTER_1 = DateTimeFormatter.ofPattern(PATTERN_1);
    private static final DateTimeFormatter CUSTOM_FORMATTER_2 = DateTimeFormatter.ofPattern(PATTERN_2);
    private static final DateTimeFormatter CUSTOM_FORMATTER_3 = DateTimeFormatter.ofPattern(PATTERN_3);

    /**
     * Format OffsetDateTime to ISO Offset Date Time String
     *
     * @param offsetDateTime the OffsetDateTime object to format
     * @return the formatted string
     */
    public static String formatToIso(OffsetDateTime offsetDateTime) {
        return offsetDateTime.format(ISO_FORMATTER);
    }

    /**
     * Format OffsetDateTime to Custom Format 1
     *
     * @param offsetDateTime the OffsetDateTime object to format
     * @return the formatted string
     */
    public static String formatToCustom1(OffsetDateTime offsetDateTime) {
        return offsetDateTime.format(CUSTOM_FORMATTER_1);
    }

    /**
     * Format OffsetDateTime to Custom Format 2
     *
     * @param offsetDateTime the OffsetDateTime object to format
     * @return the formatted string
     */
    public static String formatToCustom2(OffsetDateTime offsetDateTime) {
        return offsetDateTime.format(CUSTOM_FORMATTER_2);
    }

    /**
     * Format OffsetDateTime to Custom Format 3
     *
     * @param offsetDateTime the OffsetDateTime object to format
     * @return the formatted string
     */
    public static String formatToCustom3(OffsetDateTime offsetDateTime) {
        return offsetDateTime.format(CUSTOM_FORMATTER_3);
    }

    /**
     * Parse a string to OffsetDateTime using a given pattern
     *
     * @param dateTimeString the date-time string to parse
     * @param pattern the pattern to use for parsing. Should be one of the predefined constants
     * @return the parsed OffsetDateTime object
     * @throws DateTimeParseException if the text cannot be parsed
     */
    public static OffsetDateTime parse(String dateTimeString, String pattern) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return OffsetDateTime.parse(dateTimeString, formatter);
    }

    /**
     * Parse a string to OffsetDateTime using the ISO format
     *
     * @param dateTimeString the date-time string to parse
     * @return the parsed OffsetDateTime object
     * @throws DateTimeParseException if the text cannot be parsed
     */
    public static OffsetDateTime parseIso(String dateTimeString) throws DateTimeParseException {
        return OffsetDateTime.parse(dateTimeString, ISO_FORMATTER);
    }
}
