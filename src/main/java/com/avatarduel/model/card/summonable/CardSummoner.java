package com.avatarduel.model.card.summonable;

import com.avatarduel.exception.CannotSummonCardException;
import com.avatarduel.exception.NoCharaterOnFieldException;
import com.avatarduel.exception.NotImplementedException;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.summonable.character.Character;
import com.avatarduel.model.card.summonable.skill.Aura;
import com.avatarduel.model.card.summonable.skill.Destroy;
import com.avatarduel.model.card.summonable.skill.PowerUp;
import com.avatarduel.model.player.field.FieldController;

public class CardSummoner<T extends Card> {
    private T card;

    public CardSummoner(T card) {
        this.card = card;
    }

    public SummonedCard summon(FieldController field, Integer i, Integer j) throws CannotSummonCardException, NotImplementedException {
        if (card instanceof EmptyCard) {
            return SummonedEmptyCard.getInstance();

        } else if (card instanceof Character) {
            return new SummonedCharacterCard((Character) card);

        } else if (card instanceof Aura) {
            if (!field.thereIsCharacter()) {
                throw new NoCharaterOnFieldException();
            }
            return new SummonedAuraCard((Aura) card);

        } else if (card instanceof Destroy) {
            throw new NotImplementedException();
            //            return new SummonedDestroyCard((Destroy) card);

        } else if (card instanceof PowerUp) {
            throw new NotImplementedException();
            //            return new SummonedPowerUpCard((PowerUp) card);

        }
        throw new CannotSummonCardException();
    }
}
