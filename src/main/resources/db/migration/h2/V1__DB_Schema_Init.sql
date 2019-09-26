DROP TABLE SE_DOCUMENTS IF EXISTS;
DROP TABLE SE_DOCUMENT_TOKENS IF EXISTS;

DROP SEQUENCE SE_DOCUMENTS_SEQ IF EXISTS;
DROP SEQUENCE SE_DOCUMENT_TOKEN_SEQ IF EXISTS;

CREATE SEQUENCE SE_DOCUMENTS_SEQ AS INTEGER START WITH 1000 INCREMENT BY 1;
CREATE SEQUENCE SE_DOCUMENT_TOKEN_SEQ AS INTEGER START WITH 1000 INCREMENT BY 1;

CREATE TABLE SE_DOCUMENTS (
  ID          INTEGER       GENERATED BY DEFAULT AS SEQUENCE SE_DOCUMENTS_SEQ PRIMARY KEY,
  KEY         VARCHAR(10)   NOT NULL,
  CONTENT     VARCHAR(1000) NOT NULL
);
create unique index IDX_DOC_KEY on SE_DOCUMENTS(KEY);

CREATE TABLE SE_DOCUMENT_TOKENS (
  ID          INTEGER       GENERATED BY DEFAULT AS SEQUENCE SE_DOCUMENT_TOKEN_SEQ PRIMARY KEY,
  DOC_ID      INTEGER       NOT NULL,
  TOKEN       VARCHAR(100)  NOT NULL
);

create index IDX_DOC_TOKEN on SE_DOCUMENT_TOKENS(TOKEN);

ALTER TABLE SE_DOCUMENT_TOKENS
  ADD CONSTRAINT FK_DOCUMENT_ID
FOREIGN KEY (DOC_ID)
REFERENCES SE_DOCUMENTS
ON DELETE CASCADE;