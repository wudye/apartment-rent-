package com.mwu.webadmin.custom.convert;

import com.mwu.model.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

@Component
public class StringToBaseEnumConverterFactory implements ConverterFactory<String, BaseEnum> {


    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return code -> {
            for (T enumConstant : targetType.getEnumConstants()) {
                if (enumConstant
                        .getCode()
                        .equals(Integer.valueOf(code))) {
                    return enumConstant;
                }
            }
            throw new IllegalArgumentException("No enum constant " + targetType.getName() + " with code " + code);
        };
    }
}
