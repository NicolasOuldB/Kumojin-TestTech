package fr.nicolas.kumojin_be.helper.format.OffsetDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class OffsetDateTimeDeserializer extends JsonDeserializer<OffsetDateTime> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(OffsetDateTimeConstants.DATETIME_TIMEZONE_FORMAT);
    private static final DateTimeFormatter FORMATTER2 = DateTimeFormatter.ofPattern(OffsetDateTimeConstants.DATETIME_UTC_FORMAT);

    @Override
    public OffsetDateTime deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException {
        String date = parser.getText();
        try {
            return OffsetDateTime.parse(date, FORMATTER);
        } catch (DateTimeParseException e) {
            try {
                return OffsetDateTime.parse(date, FORMATTER2);
            } catch (DateTimeParseException ex) {
                throw new IOException("Unable to parse OffsetDateTime", ex);
            }
        }
    }
}
