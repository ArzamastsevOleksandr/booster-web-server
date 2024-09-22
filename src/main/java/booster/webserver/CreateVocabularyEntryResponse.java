package booster.webserver;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CreateVocabularyEntryResponse {
    private Long id;
    private String name;
    private String description;
    private Set<String> synonyms;
    private Integer correctAnswerCount;
}
