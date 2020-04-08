package com.avatarduel.event;

import com.avatarduel.model.card.SelectedCard;

public interface AttachSkillEvent extends Subscriber {
    void onAttachSkillEvent(SelectedCard firstCard, SelectedCard secondCard);
}
