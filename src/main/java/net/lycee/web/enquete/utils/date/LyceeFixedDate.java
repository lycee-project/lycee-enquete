package net.lycee.web.enquete.utils.date;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class LyceeFixedDate extends LyceeDate {

    private final LocalDateTime date;

    public LyceeFixedDate(String fixedDate) {
        super(ZoneOffset.ofHours(9));
        date = DateFormat.parseToDateTime(fixedDate);
    }

    @Override
    public LocalDateTime get() {
        return date;
    }
}
