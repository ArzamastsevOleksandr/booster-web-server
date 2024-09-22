CREATE TABLE IF NOT EXISTS word
(
    id         BIGSERIAL NOT NULL PRIMARY KEY,
    name       VARCHAR   NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

ALTER TABLE word
    ADD CONSTRAINT word_name_unique UNIQUE (name);

CREATE TABLE IF NOT EXISTS vocabulary_entry
(
    id                   BIGSERIAL NOT NULL PRIMARY KEY,
    word_id              BIGINT    NOT NULL,
    description          VARCHAR,
    correct_answer_count SMALLINT  NOT NULL DEFAULT 0,
    created_at           TIMESTAMP NOT NULL DEFAULT NOW()
);

ALTER TABLE vocabulary_entry
    ADD CONSTRAINT vocabulary_entry_word_id_fk FOREIGN KEY (word_id) REFERENCES word (id);

ALTER TABLE vocabulary_entry
    ADD CONSTRAINT vocabulary_entry_word_id_unique UNIQUE (word_id);


CREATE TABLE IF NOT EXISTS vocabulary_entry_synonym
(
    vocabulary_entry_id BIGINT NOT NULL,
    word_id             BIGINT NOT NULL
);

ALTER TABLE vocabulary_entry_synonym
    ADD CONSTRAINT vocabulary_entry_synonym_vocabulary_entry_id_fk FOREIGN KEY (vocabulary_entry_id) REFERENCES vocabulary_entry (id);

ALTER TABLE vocabulary_entry_synonym
    ADD CONSTRAINT vocabulary_entry_synonym_word_id_fk FOREIGN KEY (word_id) REFERENCES word (id);

ALTER TABLE vocabulary_entry_synonym
    ADD CONSTRAINT vocabulary_entry_synonym_vocabulary_entry_id_word_id_unique UNIQUE (vocabulary_entry_id, word_id);
