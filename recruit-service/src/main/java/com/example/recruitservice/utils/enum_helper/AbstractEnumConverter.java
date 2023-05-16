package com.example.recruitservice.utils.enum_helper;

import javax.persistence.AttributeConverter;


/**
 * E stands for Enum class
 * T stands for type data in db
 */
public abstract class AbstractEnumConverter<E extends Enum<E> & ValueEnumInterface<T>, T>
        implements AttributeConverter<E, T> {

    private final Class<E> enumCLass;

    protected AbstractEnumConverter(Class<E> enumCLass) {
        this.enumCLass = enumCLass;
    }

    @Override
    public T convertToDatabaseColumn(E attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public E convertToEntityAttribute(T value) {
        E[] enums = enumCLass.getEnumConstants();
        for (E type : enums) {
            if (type.getValue() == value || type.getValue().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value enum " + enumCLass.getName() + " with value " + value);
    }
}
