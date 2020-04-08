package com.avatarduel.event;

import com.avatarduel.model.card.SelectedCard;

public interface AttackCardEvent extends Subscriber {
    void onAttackCardEvent(SelectedCard firstCard, SelectedCard secondCard);
}
