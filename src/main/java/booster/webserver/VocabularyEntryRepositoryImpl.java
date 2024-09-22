package booster.webserver;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Repository
@Transactional
@RequiredArgsConstructor
public class VocabularyEntryRepositoryImpl implements CustomRepository<VocabularyEntry> {

    final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public VocabularyEntry create(VocabularyEntry in) {
        VocabularyEntry out = namedParameterJdbcTemplate.query("""
                        INSERT INTO vocabulary_entry
                        (word_id, description, correct_answer_count, created_at)
                        VALUES
                        (:word_id, :description, :correct_answer_count, NOW())
                        RETURNING *
                        """,
                Map.of(
                        "word_id", in.word().id(),
                        "description", in.description(),
                        "correct_answer_count", 0),
                rs -> {
                    rs.next();
                    return VocabularyEntry.builder()
                            .id(rs.getLong("id"))
                            .description(rs.getString("description"))
                            .correctAnswerCount(rs.getInt("correct_answer_count"))
                            .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                            .word(in.word())
                            .synonyms(in.synonyms())
                            .build();
                });

        for (Word synonym : in.synonyms()) {
            namedParameterJdbcTemplate.query("""
                            INSERT INTO vocabulary_entry_synonym
                            (vocabulary_entry_id, word_id)
                            VALUES
                            (:vocabulary_entry_id, :word_id)
                            RETURNING *
                            """, Map.of(
                            "vocabulary_entry_id", out.id(),
                            "word_id", synonym.id()),
                    rs -> {
                        rs.next();
                        return rs.getLong("vocabulary_entry_id");
                    });
        }
        return out;
    }

}
