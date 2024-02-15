package net.lycee.web.enquete.utils.date;

import java.time.LocalDateTime;
import java.util.function.Supplier;

public class LyceeDateFactory implements Supplier<LocalDateTime> {

    @Override
    public LocalDateTime get() {
        return LocalDateTime.now();
    }
}
