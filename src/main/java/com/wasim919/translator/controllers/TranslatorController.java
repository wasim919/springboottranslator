package com.wasim919.translator.controllers;

import com.wasim919.translator.services.TranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author  Wasim
 * @version 1.0.0
 * It has 2 end points that translate text from english to spanish and vice-versa
 */
@RestController
@RequestMapping("/translate")
public class TranslatorController {
    final TranslatorService translatorService;

    @Autowired
    public TranslatorController(TranslatorService translatorService) {
        this.translatorService = translatorService;
    }

    /**
     * This method returns the translation of input english test into spanish
     * @param   text      text from english language that you want to translate to spanish
     * @return  text      translation of the text into spanish
     *
     * I have accepted the text-to-be-translated as a body argument for privacy reasons and not
     * making the request too long (by using query params)
     */
    @PostMapping("/translateFromEnToEs")
    public String translateFromEnToEs(@RequestBody @NotNull @NotBlank String text) {
        return this.translatorService.translateFromEnToEs(text);
    }

    /**
     * This method returns the translation of input spanish test into english
     * @param   text      text from spanish language that you want to translate to english
     * @return  text      translation of the text into english
     *
     * I have accepted the text-to-be-translated as a body argument for privacy reasons and not
     * making the request too long (by using query params)
     */
    @PostMapping("/translateFromEsToEn")
    public String translateFromEsToEn(@RequestBody @NotNull @NotBlank String text) {
        return this.translatorService.translateFromEsToEn(text);
    }
}
