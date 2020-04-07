package com.avatarduel.model.card;

public class SummonedSkillCard extends SummonedCard{
    Skill card;

    public Skill getCard() {
        return card;
    }

    public Integer getAttack(){
        if(this.getCard() instanceof Aura){
            return ((Aura) this.getCard()).getAttack();
        } else {
            return 0;
        }
    }

    public Integer getDefend(){
        if(this.getCard() instanceof Aura){
            return ((Aura) this.getCard()).getDefend();
        } else {
            return 0;
        }
    }
}
