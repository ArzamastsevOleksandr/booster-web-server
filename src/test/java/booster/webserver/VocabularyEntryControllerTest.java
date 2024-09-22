package booster.webserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VocabularyEntryControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void createBook() {
        CreateVocabularyEntryRequest request = CreateVocabularyEntryRequest.builder()
                .name("Onset")
                .description("Who cares")
                .synonyms(Set.of("Uga", "Buga"))
                .build();

        webTestClient.post()
                .uri("/vocabulary-entry")
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CreateVocabularyEntryResponse.class).value(newEntry -> {
                    assertThat(newEntry).isNotNull();
                    assertThat(newEntry.getId()).isNotNull();
                    assertThat(newEntry.getName()).isEqualTo("Onset");
                    assertThat(newEntry.getDescription()).isEqualTo("Who cares");
                    assertThat(newEntry.getSynonyms()).containsAll(request.synonyms());
                    assertThat(newEntry.getCorrectAnswerCount()).isEqualTo(0);
                });
    }
}
