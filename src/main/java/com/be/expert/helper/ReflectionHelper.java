package com.be.expert.helper;

import static java.lang.reflect.Modifier.isFinal;
import static java.lang.reflect.Modifier.isStatic;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectionHelper {

    private static final String MODIFIER_FIELD_NAME = "modifiers";

    private ReflectionHelper() {
    }

    public static <C, T> void setStaticField(Class<C> clazz, String fieldName, T fieldValue)
            throws NoSuchFieldException, IllegalAccessException {
        setField(clazz, null, fieldName, fieldValue);
    }

    public static <C, T> void setInstanceField(Class<C> clazz, C instance, String fieldName, T fieldValue)
            throws NoSuchFieldException, IllegalAccessException {
        if (instance == null) {
            throw new IllegalArgumentException("Instance Should not be null");
        }
        setField(clazz, instance, fieldName, fieldValue);
    }

    public static <C, T> T getInstanceField(Class<C> clazz, C instance, String fieldName, Class<T> fieldValueClazz)
            throws NoSuchFieldException, IllegalAccessException {
        if (instance == null) {
            throw new IllegalArgumentException("Instance Should not be null");
        }
        return getField(clazz, instance, fieldName, fieldValueClazz);
    }

    public static <C, T> T getStaticField(Class<C> clazz, String fieldName, Class<T> fieldValueClazz)
            throws NoSuchFieldException, IllegalAccessException {
        return getField(clazz, null, fieldName, fieldValueClazz);
    }

    private static <C, T> T getField(Class<C> clazz, C instance, String fieldName, Class<T> fieldValueClazz)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        Object fieldValue = null;
        if (isStatic(field.getModifiers())) {
            fieldValue = field.get(clazz);
        } else {
            fieldValue = field.get(instance);
        }
        return fieldValueClazz.cast(fieldValue);
    }

    private static <C, T> void setField(Class<C> clazz, C instance, String fieldName, T fieldValue)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        if (isFinal(field.getModifiers())) {
            Field modifierField = Field.class.getDeclaredField(MODIFIER_FIELD_NAME);
            modifierField.set(field, field.getModifiers() & ~Modifier.FINAL);
        }
        if (isStatic(field.getModifiers())) {
            field.set(instance, fieldValue);
        } else {
            field.set(clazz, fieldValue);
        }
    }

}
