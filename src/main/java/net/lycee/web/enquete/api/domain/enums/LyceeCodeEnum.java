package net.lycee.web.enquete.api.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public interface LyceeCodeEnum<T extends Enum<T>> {

    String getCode();

    static <E extends LyceeCodeEnum> E of(Class<E> clazz, String code) {
        return Arrays.stream(clazz.getEnumConstants())
                .filter(it -> it.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> {
                    return new IllegalArgumentException(
                            String.format("not use such code => [%s]", code)
                    );
                });
    }

}
