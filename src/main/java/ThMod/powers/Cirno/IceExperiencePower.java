package ThMod.powers.Cirno;

import ThMod.ThMod;
import ThMod.action.CirnoExhaustSpecificCardAction;
import ThMod.action.CirnoSelectSomeAndMoveAction;
import ThMod.cards.Cirno.IceExperience;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.cards.CardGroup.CardGroupType.UNSPECIFIED;

public class IceExperiencePower extends AbstractPower {
	
	public static final String POWER_ID = IceExperiencePower.class.getSimpleName();
	private static final PowerStrings powerStrings =
			CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS =
			powerStrings.DESCRIPTIONS;
	
	CardGroup group;
	
	public IceExperiencePower(ArrayList<AbstractCard> cards) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = AbstractDungeon.player;
		
		this.group = new CardGroup(UNSPECIFIED);
		for (AbstractCard card : cards)
			this.group.addToBottom(card);
		
		this.amount = this.group.size();
		
		this.type = PowerType.BUFF;
		this.updateDescription();
		this.img = new Texture("img/powers/Nineball32.png");
//		this.img = new Texture("img/powers/FunkyPower.png");
	}
	
	public void stackPower(ArrayList<AbstractCard> cards) {
		for (AbstractCard card : cards)
			this.group.addToBottom(card);
		
		this.fontScale = 8F;
		this.amount = this.group.size();
		this.updateDescription();
	}
	
	@Override
	public void updateDescription() {
		this.group.sortAlphabetically(true);
		this.group.sortByRarityPlusStatusCardType(true);
		
		StringBuilder builder = new StringBuilder(DESCRIPTIONS[0]);
		
		for (AbstractCard card : this.group.group) {
			if (card != this.group.group.get(0))
				builder.append(DESCRIPTIONS[1]);
			builder.append(card.name);
		}
		
		builder.append(DESCRIPTIONS[2]);
		this.description = builder.toString();
	}
	
	@Override
	public void atEndOfTurn(boolean isPlayer) {
		if (isPlayer) {
			ArrayList<AbstractGameAction> actions = new ArrayList<>();
			
			AbstractPlayer p = AbstractDungeon.player;
			int cnt = Integer.min(p.hand.size(), this.group.size());
			
			ThMod.logger.info("FUCK cnt = " + cnt);
			
			for (AbstractCard card : p.hand.group)
				actions.add(0, new CirnoExhaustSpecificCardAction(card, p.hand));
			
			actions.add(0, new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
			
			if (cnt > 0)
				actions.add(0, new CirnoSelectSomeAndMoveAction(this.group.group, this.group, p.hand, cnt,
					DESCRIPTIONS[3] + cnt + DESCRIPTIONS[4] + " (" + IceExperience.NAME + ")",
					(card) -> {card.retain = true;}));
			
//			this.addToBot(new MoveCardsAction(p.discardPile, this.group, 114514));
//			actions.add(0, new MoveCardsAction(p.discardPile, this.group,
//					(c) -> (!p.hand.contains(c)), cnt));
			
			for (AbstractGameAction act : actions)
				this.addToTop(act);
			
			/*
			
			CardGroup selected = new CardGroup(UNSPECIFIED);
			
			this.addToBot(new SelectCardsAction(this.group.group, cnt,
					DESCRIPTIONS[3] + cnt + DESCRIPTIONS[4]
						 + " (" + IceExperience.NAME + ")",
					(selectedCards) -> {
						for (AbstractCard card : selectedCards)
							selected.addToBottom(card);
					}));
			
			this.addToBot(new FetchAction(selected, cnt));
			*/
		}
	}
}