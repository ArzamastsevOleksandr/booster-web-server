package booster.webserver;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Table("vocabulary_entry")
@Data
@Builder
@Accessors(fluent = true)
public class VocabularyEntry {
    @Id
    private Long id;
    @Transient
    private Word word;
    private String description;
    @Builder.Default
    @Transient
    private Set<Word> synonyms = new HashSet<>();
    @Builder.Default
    private Integer correctAnswerCount = 0;
    @CreatedDate
    private LocalDateTime createdAt;
}
