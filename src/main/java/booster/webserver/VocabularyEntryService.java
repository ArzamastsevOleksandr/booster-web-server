package booster.webserver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
@Transactional
@RequiredArgsConstructor
public class VocabularyEntryService {

    final VocabularyEntryRepository vocabularyEntryRepository;
    final WordRepository wordRepository;

    public CreateVocabularyEntryResponse create(CreateVocabularyEntryRequest request) {
        Word word = wordRepository.createIfNotExists(request.name());
        Set<Word> synonyms = request.synonyms().stream()
                .map(wordRepository::createIfNotExists)
                .collect(toSet());

        VocabularyEntry vocabularyEntry = vocabularyEntryRepository.create(VocabularyEntry.builder()
                .word(word)
                .description(request.description())
                .synonyms(synonyms)
                .build());

        return CreateVocabularyEntryResponse.builder()
                .id(vocabularyEntry.id())
                .name(vocabularyEntry.word().name())
                .description(vocabularyEntry.description())
                .synonyms(vocabularyEntry.synonyms().stream().map(Word::name).collect(toSet()))
                .correctAnswerCount(vocabularyEntry.correctAnswerCount())
                .build();
    }
}
