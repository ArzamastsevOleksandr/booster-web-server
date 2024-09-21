CREATE TABLE IF NOT EXISTS vocabulary_entry (
    name VARCHAR NOT NULL,
    correct_answers_count SMALLINT NOT NULL DEFAULT 0
);
