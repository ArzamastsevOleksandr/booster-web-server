package booster.webserver;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface WordRepository extends CrudRepository<Word, Long> {

    @Query("""
            INSERT INTO word
            (name, created_at)
            VALUES
            (:name, NOW())
            ON CONFLICT (name)
                DO UPDATE
                SET name = EXCLUDED.name
            RETURNING *
            """)
    Word createIfNotExists(String name);
}
