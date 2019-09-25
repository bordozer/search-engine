package com.bordozer.searchengine.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(of = {"key", "content"})
@Table(name = "SE_DOCUMENTS")
public class DocumentEntity {

    @Id
    @Column(name = "ID")
    @GenericGenerator(
            name = "Document_Sequence",
            strategy = "sequence",
            parameters = {
                    @Parameter(name = "sequence_name", value = "SE_DOCUMENTS_SEQ"),
                    @Parameter(name = "initial_value", value = "1000"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Document_Sequence")
    private Long id;

    @Column(name = "KEY", nullable = false, unique = true)
    private String key;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "key")
    private List<DocumentTokenEntity> tokens;
}
