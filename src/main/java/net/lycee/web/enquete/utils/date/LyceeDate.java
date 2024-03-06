package net.lycee.web.enquete.utils.date;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.function.Supplier;

public class LyceeDate implements Supplier<LocalDateTime> {

    private final ZoneOffset zoneOffset;

    public LyceeDate(ZoneOffset zoneOffset) {
        this.zoneOffset = zoneOffset;
    }

    @Override
    public LocalDateTime get() {
        return (LocalDateTime) RequestContextHolder
                .currentRequestAttributes()
                .getAttribute("lyceeDate", RequestAttributes.SCOPE_REQUEST);
    }

    public long getMilliseconds() {
        return get().toInstant(zoneOffset).toEpochMilli();
        //return get().toEpochSecond(ZoneOffset.ofHours(9));
    }

}
