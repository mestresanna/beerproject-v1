package be.kdg.programming3.prog3_spring.presentation.converter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {
    private DateTimeFormatter formatter;
    public LocalDateAdapter() {
        this.formatter = DateTimeFormatter.ofPattern("dd-MMM-yy");
    }

    @Override
    public void write(final JsonWriter jsonWriter,
                      final LocalDate localDate) throws IOException {
        jsonWriter.value(formatter.format(localDate));
    }

    @Override
    public LocalDate read(final JsonReader jsonReader)
            throws IOException {
        return LocalDate.parse(jsonReader.nextString(), formatter);
    }
}
