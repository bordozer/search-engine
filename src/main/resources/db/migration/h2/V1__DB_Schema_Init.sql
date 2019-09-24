DROP TABLE SE_DOCUMENTS IF EXISTS;

DROP SEQUENCE SE_DOCUMENTS_SEQ IF EXISTS;

CREATE SEQUENCE SE_DOCUMENTS_SEQ AS INTEGER START WITH 1000 INCREMENT BY 1;

CREATE TABLE SE_DOCUMENTS (
  ID          INTEGER       GENERATED BY DEFAULT AS SEQUENCE SE_DOCUMENTS_SEQ PRIMARY KEY,
  KEY         VARCHAR(10)   NOT NULL,
  CONTENT     VARCHAR(1000) NOT NULL
);
create unique index IDX_DOC_KEY on SE_DOCUMENTS(KEY);
