package com.avatarduel.event;

import com.avatarduel.model.card.SelectedCard;

public interface AttackEnemyEvent extends Subscriber {
    void onAttackEnemyEvent(SelectedCard firstCard, SelectedCard secondCard);
}
