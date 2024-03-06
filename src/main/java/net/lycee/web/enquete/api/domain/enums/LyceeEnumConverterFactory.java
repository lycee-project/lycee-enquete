package net.lycee.web.enquete.api.domain.enums;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

@Component
public class LyceeEnumConverterFactory implements ConverterFactory<String, LyceeCodeEnum> {

    @Override
    public <T extends LyceeCodeEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new LyceeEnumConverter(targetType);
    }

    @AllArgsConstructor
    private static class LyceeEnumConverter<T extends LyceeCodeEnum> implements Converter<String, T> {
        private Class<T> targetType;

        @Override
        public T convert(String source) {
            if (source.isEmpty()) {
                return null;
            }
            return LyceeCodeEnum.of(targetType, source.trim());
        }
    }
}
