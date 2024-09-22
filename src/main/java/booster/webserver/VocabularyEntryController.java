package booster.webserver;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/vocabulary-entry")
@RequiredArgsConstructor
class VocabularyEntryController {

    final VocabularyEntryService vocabularyEntryService;


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    CreateVocabularyEntryResponse create(@RequestBody @Valid CreateVocabularyEntryRequest request) {
        log.info("[{}]", request);
        CreateVocabularyEntryResponse response = vocabularyEntryService.create(request);
        log.info("[{}]", response);
        return response;
    }

}
