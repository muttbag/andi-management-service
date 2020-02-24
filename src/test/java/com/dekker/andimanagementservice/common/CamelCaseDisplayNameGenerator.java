package com.dekker.andimanagementservice.common;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CamelCaseDisplayNameGenerator extends DisplayNameGenerator.Standard {

    @Override
    public String generateDisplayNameForClass(Class<?> aClass) {
        return convertCamelCaseToSpaces(aClass.getSimpleName());
    }

    @Override
    public String generateDisplayNameForNestedClass(Class<?> aClass) {
        return convertCamelCaseToSpaces(aClass.getSimpleName());
    }

    @Override
    public String generateDisplayNameForMethod(Class<?> aClass, Method method) {
        return convertCamelCaseToSpaces(method.getName());
    }

    private String convertCamelCaseToSpaces(final String text) {
        return Arrays.stream(text.split("(?<=[a-z])(?=[A-Z])")).collect(Collectors.joining(" "));
    }
}