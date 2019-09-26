package com.bordozer.searchengine.entity;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressFBWarnings("NP_NONNULL_FIELD_NOT_INITIALIZED_IN_CONSTRUCTOR")
@Entity
@Getter
@Setter
@ToString(of = {"id", "document", "token"})
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

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "DOC_ID")
    private DocumentEntity document;

    @Column(name = "TOKEN", nullable = false)
    private String token;
}
