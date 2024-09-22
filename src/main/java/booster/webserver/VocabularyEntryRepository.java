package booster.webserver;

import org.springframework.data.repository.CrudRepository;

public interface VocabularyEntryRepository extends CrudRepository<VocabularyEntry, Long>, CustomRepository<VocabularyEntry> {
}
