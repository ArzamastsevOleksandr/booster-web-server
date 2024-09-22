package booster.webserver;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.Set;

@Builder
public record CreateVocabularyEntryRequest(
        @NotBlank(message = "Vocabulary entry name must not be blank")
        String name,
        String description,
        Set<String> synonyms) {
}
