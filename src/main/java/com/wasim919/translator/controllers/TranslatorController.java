package com.wasim919.translator.controllers;

import com.wasim919.translator.services.TranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/translate")
public class TranslatorController {
    final TranslatorService translatorService;

    @Autowired
    public TranslatorController(TranslatorService translatorService) {
        this.translatorService = translatorService;
    }

    @GetMapping("/greet")
    public String greet() {
        return "Hello";
    }

    @PostMapping("/translateFromEnToEs")
    public String translateFromEnToEs(@RequestBody @NotNull @NotBlank String text) {
        return this.translatorService.translateFromEnToEs(text);
    }

}
