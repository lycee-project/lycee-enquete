package net.lycee.web.anquete.utils.date;

import java.time.LocalDateTime;

public class LyceeFixedDate extends LyceeDate {

    private final LocalDateTime date;

    public LyceeFixedDate(String fixedDate) {

        date = DateFormat.parseToDateTime(fixedDate);
    }

    @Override
    public LocalDateTime get() {
        return date;
    }
}
