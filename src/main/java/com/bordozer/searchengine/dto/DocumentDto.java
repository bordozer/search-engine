package com.bordozer.searchengine.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;

@SuppressWarnings("checkstyle:magicnumber")
@JsonDeserialize(
        builder = ImmutableDocumentDto.Builder.class
)
@JsonIgnoreProperties("initialized")
@Value.Immutable
@Value.Modifiable
@ToString
public abstract class DocumentDto {

    @NotBlank
    @Length(max = 100)
    public abstract String getKey();

    @NotBlank
    @Length(max = 1000)
    public abstract String getContent();

    public static ImmutableDocumentDto.Builder builder() {
        return new ImmutableDocumentDto.Builder();
    }
}
