package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.HashSet;

public class IceDuplicate extends AbstractCirnoCard {
	
	public static final String ID = IceDuplicate.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int CNT = 1;
	private static final int UPGRADE_PLUS_CNT = 1;
	
	public IceDuplicate() {
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
	}
	
	@Override
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		HashSet<String> set = new HashSet<>();
		
		for (AbstractCard card : p.hand.group) {
			if (set.contains(card.cardID)) {
				this.cantUseMessage = this.getCantPlayMessage();
				return false;
			}
			else
				set.add(card.cardID);
		}
		
		return true;
	}
	
	@Override
	public String getCantPlayMessage() {
		StringBuilder builder = new StringBuilder(cardStrings.EXTENDED_DESCRIPTION[0]);
		
		HashSet<String> set = new HashSet<>(), dup = new HashSet<>();
		
		for (AbstractCard card : AbstractDungeon.player.hand.group) {
			if (set.contains(card.cardID)) {
				if (!dup.contains(card.cardID)) {
					if (!dup.isEmpty())
						builder.append(cardStrings.EXTENDED_DESCRIPTION[1]);
					builder.append(card.name);
					
					dup.add(card.cardID);
				}
			}
			else
				set.add(card.cardID);
		}
		
		builder.append(cardStrings.EXTENDED_DESCRIPTION[2]);
		
		return builder.toString();
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new SelectCardsInHandAction(
				cardStrings.EXTENDED_DESCRIPTION[3] + this.magicNumber +
						cardStrings.EXTENDED_DESCRIPTION[4] + " (" + this.name + ")",
				(cards) -> {
					if (!cards.isEmpty()) {
						AbstractCard c = cards.get(0);
						this.addToBot(new MakeTempCardInHandAction(
								c.makeStatEquivalentCopy(), this.magicNumber));
					}
				}
		));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new IceDuplicate();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			this.upgradeMagicNumber(UPGRADE_PLUS_CNT);
			this.initializeDescription();
		}
	}
}