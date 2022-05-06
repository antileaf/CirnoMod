package ThMod.cards.CirnoChoiceCards;

import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.unique.DiscoveryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SanyouseisHelp extends CustomCard {
	private static final int COST = -2;
	protected CardType cardType;
	
	public SanyouseisHelp(
			String id,
			String name,
			String img,
			String rawDescription) {
		super(
			id,
			name,
			"img/cards/Cirno/Th123Cirno.png",
			COST,
			rawDescription,
			CardType.POWER,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.SPECIAL,
			CardTarget.NONE
		);
	}
	
	@Override
	public void onChoseThisOption() {
		if (!this.upgraded) {
			AbstractCard card = AbstractDungeon.returnTrulyRandomCardInCombat(this.cardType).makeCopy();
			card.setCostForTurn(0);
			this.addToBot(new MakeTempCardInHandAction(card));
		}
		else {
			this.addToBot(new DiscoveryAction(this.cardType, 1));
		}
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.onChoseThisOption();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			
			this.rawDescription = CardCrawlGame.languagePack.getCardStrings(this.cardID).UPGRADE_DESCRIPTION;
			initializeDescription();
		}
	}
}