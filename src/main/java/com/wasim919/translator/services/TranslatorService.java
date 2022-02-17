package com.wasim919.translator.services;

import com.wasim919.translator.entities.TranslatedEntity;
import com.wasim919.translator.repositories.TranslatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TranslatorService {
    private final TranslatorRepository translatorRepository;
    private final RestTemplate restTemplate;

    private final String translateApiUri = "https://libretranslate.de/translate";

    @Autowired
    public TranslatorService(TranslatorRepository translatorRepository, RestTemplate restTemplate) {
        this.translatorRepository = translatorRepository;
        this.restTemplate = restTemplate;
    }

    public String translateFromEnToEs(String text) {
            TranslatedEntity translatedEntity = TranslatedEntity.builder()
                    .q(text)
                    .source("en")
                    .target("es")
                    .build();
            ResponseEntity<TranslatedEntity> response = restTemplate.postForEntity(translateApiUri, translatedEntity, TranslatedEntity.class);
            System.out.println(response.getBody());
            return response.getBody().getTranslatedText();
    }

    public String translateFromEsToEn(String text) {
            TranslatedEntity translatedEntity = TranslatedEntity.builder()
                    .q(text)
                    .source("es")
                    .target("en")
                    .build();
            ResponseEntity<TranslatedEntity> response = restTemplate.postForEntity(translateApiUri, translatedEntity, TranslatedEntity.class);
            System.out.println(response.getBody());
            return response.getBody().getTranslatedText();
    }
}
