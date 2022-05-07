package ThMod.action;

import ThMod.powers.Cirno.ChillPower;
import ThMod.powers.Cirno.MotivationPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class IcicleConeCrushFollowAction extends AbstractGameAction {
	AbstractMonster target;
	int damage, block, chill, motivation;
	
	public IcicleConeCrushFollowAction(AbstractMonster target, int damage, int block, int chill, int motivation) {
		this.actionType = ActionType.CARD_MANIPULATION;
		
		this.damage = damage;
		this.block = block;
		this.chill = chill;
		this.motivation = motivation;
		
		this.target = target;
	}
	
	public void update() {
		if (!this.isDone) {
			AbstractPlayer p = AbstractDungeon.player;
			
			for (AbstractCard card : DrawCardAction.drawnCards) {
				if (card.type == AbstractCard.CardType.ATTACK) {
					if (target != null)
						this.addToBot(new DamageAction(target, new DamageInfo(
								p, this.damage), AttackEffect.SLASH_VERTICAL));
				}
				else if (card.type == AbstractCard.CardType.SKILL)
					this.addToBot(new GainBlockAction(p, this.block));
				else {
					this.addToBot(new ApplyPowerAction(p, p, new ChillPower(this.chill)));
					this.addToBot(new ApplyPowerAction(p, p, new MotivationPower(this.motivation)));
				}
			}
			
			this.isDone = true;
		}
	}
}
