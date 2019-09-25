package com.bordozer.searchengine.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString(of = {"key", "token"})
@Table(name = "SE_DOCUMENT_TOKENS")
public class DocumentTokenEntity {

    @Id
    @Column(name = "ID")
    @GenericGenerator(
            name = "Document_Token_Sequence",
            strategy = "sequence",
            parameters = {
                    @Parameter(name = "sequence_name", value = "SE_DOCUMENT_TOKEN_SEQ"),
                    @Parameter(name = "initial_value", value = "1000"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Document_Token_Sequence")
    private Long id;

    @Column(name = "KEY", nullable = false)
    private String key;

    @Column(name = "TOKEN", nullable = false)
    private String token;
}
