package com.avatarduel.event;

import com.avatarduel.model.card.SelectedCard;

public class AttachSkillEvent implements Subscriber {
    @Override
    public void onEvent(EventType type, SelectedCard firstCard, SelectedCard secondCard) {
        // TODO
        System.out.println("Attach Skill event!");
    }
}
