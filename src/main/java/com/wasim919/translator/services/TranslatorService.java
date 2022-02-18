package com.wasim919.translator.services;

import java.util.Optional;
import com.wasim919.translator.entities.TranslatedEntity;
import com.wasim919.translator.repositories.TranslatorRepository;
import org.json.JSONObject;
import org.springframework.data.domain.Example;
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
		try {
			TranslatedEntity translatedEntity = TranslatedEntity.builder()
					.q(text)
					.source("en")
					.target("es")
					.build();
			return getTranslatedText(translatedEntity);
		} catch(Exception e) {
			return e.getMessage();
		}
	}

	public String translateFromEsToEn(String text) {
		try {
			TranslatedEntity translatedEntity = TranslatedEntity.builder()
					.q(text)
					.source("es")
					.target("en")
					.build();
			return getTranslatedText(translatedEntity);
		} catch(Exception e) {
			return e.getMessage();
		}
	}

	private String getTranslatedText(TranslatedEntity translatedEntity) {
		Optional<TranslatedEntity> result = this.translatorRepository.findOne(Example.of(translatedEntity));

		if (result.isPresent()) {
			return result.get().getTranslatedText();
		}
		ResponseEntity<TranslatedEntity> response = restTemplate.postForEntity(translateApiUri, translatedEntity,
				TranslatedEntity.class);
		if (!response.hasBody()) {
			return "No translation found";
		}
		if (response.getStatusCodeValue() == 200 && response.hasBody()) {
			JSONObject jsonObject = new JSONObject(response.getBody());
			translatedEntity.setTranslatedText(jsonObject.getString("translatedText"));
			this.translatorRepository.save(translatedEntity);
		}
		return translatedEntity.getTranslatedText();
	}
}
