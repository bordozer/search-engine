package com.bordozer.searchengine.utils;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import javax.annotation.CheckForNull;
import java.util.function.Function;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class LoggableJson {

    @CheckForNull
    private final Object value;

    private final Function<Object, String> toJsonFunction;

    public static LoggableJson of(@CheckForNull final Object value) {
        return new LoggableJson(value, JsonUtils::write);
    }

    @Override
    public String toString() {
        return toJsonFunction.apply(value);
    }
}
