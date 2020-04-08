package com.avatarduel.model.card.summonable;

import com.avatarduel.model.card.CardType;
import com.avatarduel.model.card.summonable.skill.PowerUp;

public class SummonedPowerUpCard extends SummonedSkillCard {
    public SummonedPowerUpCard(PowerUp card) {
        super(card, CardType.POWERUP);
    }
}
