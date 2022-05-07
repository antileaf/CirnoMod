//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class CirnoExhaustSpecificCardAction extends AbstractGameAction {
    private AbstractCard targetCard;
    private CardGroup group;

    public CirnoExhaustSpecificCardAction(AbstractCard targetCard, CardGroup group) {
        this.targetCard = targetCard;
        this.actionType = ActionType.EXHAUST;
        this.group = group;
    }

    public void update() {
        if (!this.isDone) {
            boolean correct = false;
            for (AbstractCard card : this.group.group)
                correct |= (card == this.targetCard);
            
            if (correct) {
                this.group.moveToExhaustPile(targetCard);
                CardCrawlGame.dungeon.checkForPactAchievement();
                this.targetCard.exhaustOnUseOnce = false;
                this.targetCard.freeToPlayOnce = false;
            }
            
            this.isDone = true;
        }
    }
}
