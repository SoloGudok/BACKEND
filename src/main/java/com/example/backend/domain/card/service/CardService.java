package com.example.backend.domain.card.service;

import com.example.backend.domain.card.entity.Card;
import com.example.backend.domain.card.dto.CardDTO;
import com.example.backend.domain.card.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public CardDTO getCardDetails(String cardName){
        Card card = cardRepository.findByCardName(cardName);

        if (card == null) {
            return null;
        }

        return CardDTO.builder()
                .id(card.getId())
                .cardName(card.getCardName())
                .description(card.getDescription())
                .shortDescription(card.getShort_Description())
                .build();
    }
}
