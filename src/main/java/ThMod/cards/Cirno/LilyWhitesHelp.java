package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.action.CirnoExhaustSpecificCardAction;
import ThMod.action.LilyWhitesHelpFollowAction;
import ThMod.patches.AbstractCardEnum;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LilyWhitesHelp extends AbstractCirnoCard {
	
	public static final String ID = LilyWhitesHelp.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int CNT = 3;
	private static final int UPGRADE_PLUS_CNT = 2;
	
	public LilyWhitesHelp() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.SKILL,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.UNCOMMON,
			CardTarget.NONE
		);
		
		this.magicNumber = this.baseMagicNumber = CNT;
		this.exhaust = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new SelectCardsInHandAction(this.magicNumber,
				cardStrings.EXTENDED_DESCRIPTION[0] + this.magicNumber +
				cardStrings.EXTENDED_DESCRIPTION[1] + " (" + this.name + ")",
				true,
				true,
				(c) -> (c != this),
				(cards) -> {
					for (AbstractCard card : cards)
						AbstractDungeon.actionManager.addToBottom(
								new CirnoExhaustSpecificCardAction(card, p.hand));
					
					AbstractDungeon.actionManager.addToBottom(
							new LilyWhitesHelpFollowAction(cards.size()));
				}));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new LilyWhitesHelp();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			this.upgradeMagicNumber(UPGRADE_PLUS_CNT);
			this.initializeDescription();
		}
	}
}