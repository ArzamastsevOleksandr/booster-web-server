package booster.webserver;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("word")
@Data
@Accessors(fluent = true)
public class Word {
    @Id
    private Long id;
    private String name;
    @CreatedDate
    private LocalDateTime createdAt;
}
