package com.bordozer.searchengine.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@JsonDeserialize(
        builder = ImmutableDocumentDto.Builder.class
)
@JsonIgnoreProperties({"initialized"})
@Value.Immutable
@Value.Modifiable
public abstract class DocumentDto {

    public abstract String getKey();

    public abstract String getName();

    public abstract String getContent();

    public static ImmutableDocumentDto.Builder builder() {
        return new ImmutableDocumentDto.Builder();
    }
}
