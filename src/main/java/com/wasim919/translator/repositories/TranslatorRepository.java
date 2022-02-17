package com.wasim919.translator.repositories;

import com.wasim919.translator.entities.TranslatedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslatorRepository extends JpaRepository<TranslatedEntity, Long> {
}
