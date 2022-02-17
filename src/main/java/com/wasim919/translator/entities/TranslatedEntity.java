package com.wasim919.translator.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TranslatedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please add text to translate")
    private String q;

    private String translatedText;

    @NotBlank(message = "Please add the source of the language to be translated")
    private String source;

    @NotBlank(message = "Please add the target language to translate to")
    private String target;
}
