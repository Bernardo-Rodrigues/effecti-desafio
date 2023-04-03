package com.bernardo.desafio.model.enums;

import com.bernardo.desafio.model.exception.BadRequestException;

import java.util.HashMap;
import java.util.Map;
import java.text.Normalizer;

public enum Modality {
    CHAMADA_PUBLICA,
    CHAMAMENTO_PUBLICO,
    CONCORRENCIA_PUBLICA,
    CONCURSO,
    CONVITE,
    CREDENCIAMENTO,
    DISPENSA,
    INEXIGIBILIDADE,
    LEILAO,
    PREGAO,
    RDC,
    TOMADA_DE_PRECOS;

    private static final Map<String, Modality> modalities = new HashMap<>();

    static {
        for (Modality modality : Modality.values()) {
            modalities.put(modality.toString().toLowerCase().replace("_", ""), modality);
        }
    }

    public static Modality fromString(String modality) {
        if (modality == null) return null;

        String modalityWithoutSpaces = modality.replaceAll("\\s+", "");
        Modality modalityEnum = modalities.get(normalizeString(modalityWithoutSpaces.toLowerCase()));
        if (modalityEnum == null) {
            throw new BadRequestException("Invalid modality: " + modality);
        }
        return modalityEnum;
    }

    private static String normalizeString(String input) {
        String normalizedString = Normalizer.normalize(input, Normalizer.Form.NFD);
        normalizedString = normalizedString.replaceAll("[^\\p{ASCII}]", "");
        return normalizedString;
    }
}
