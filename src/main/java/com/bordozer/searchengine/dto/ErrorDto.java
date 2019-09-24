package com.bordozer.searchengine.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.ToString;
import org.immutables.value.Value;

@JsonDeserialize(
        builder = ImmutableErrorDto.Builder.class
)
@JsonIgnoreProperties({"initialized"})
@Value.Immutable
@Value.Modifiable
@ToString
public abstract class ErrorDto {

    public abstract String getMessage();

    public static ImmutableErrorDto.Builder builder() {
        return new ImmutableErrorDto.Builder();
    }
}
