package com.avatarduel.model.card.summonable;

import com.avatarduel.exception.NoCharaterOnFieldException;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.summonable.character.Character;
import com.avatarduel.model.card.summonable.skill.Aura;
import com.avatarduel.model.card.summonable.skill.Destroy;
import com.avatarduel.model.card.summonable.skill.PowerUp;
import com.avatarduel.model.card.summonable.skill.Skill;
import com.avatarduel.model.player.field.FieldController;

public class CardSummoner<T extends Card> {
    private T card;

    public CardSummoner(T card) {
        this.card = card;
    }

    public SummonedCard summon(FieldController field, Integer i, Integer j) throws NoCharaterOnFieldException {
        if (card instanceof EmptyCard) {
            return SummonedEmptyCard.getInstance();

        } else if (card instanceof Character) {
            return new SummonedCharacterCard((Character) card);

        } else if (card instanceof Skill) {
            if (!field.thereIsCharacter()) {
                throw new NoCharaterOnFieldException();
            }

            if (card instanceof Aura) {
                return new SummonedAuraCard((Aura) card);

            } else if (card instanceof Destroy) {
                return new SummonedDestroyCard((Destroy) card);

            } else if (card instanceof PowerUp) {
                return new SummonedPowerUpCard((PowerUp) card);
            }
        }
        assert false;
        return null;
    }
}
