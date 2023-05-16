package com.example.recruitservice.utils.enum_helper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExampleEnum implements ValueEnumInterface<Integer> {

    EP_1(1), EP_2(2), EP_3(3);

    public final int value;

    @Override
    public Integer getValue() {
        return value;
    }

    public static class Converter extends AbstractEnumConverter<ExampleEnum, Integer> {
        public Converter() {
            super(ExampleEnum.class);
        }
    }
}
