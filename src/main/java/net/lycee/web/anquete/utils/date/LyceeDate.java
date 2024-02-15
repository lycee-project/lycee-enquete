package net.lycee.web.anquete.utils.date;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.function.Supplier;

public class LyceeDate implements Supplier<LocalDateTime> {

    @Override
    public LocalDateTime get() {
        return (LocalDateTime) RequestContextHolder
                .currentRequestAttributes()
                .getAttribute("lyceeDate", RequestAttributes.SCOPE_REQUEST);
    }

    public long getMilliseconds() {
        return get().toEpochSecond(ZoneOffset.ofHours(9));
    }

}
