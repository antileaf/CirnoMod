package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Cirno.MotivationPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Collections;
import java.util.HashSet;

public class FairyInSpring extends AbstractCirnoCard {
	
	public static final String ID = FairyInSpring.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 5;
	private static final int CNT = 2;
	
	public FairyInSpring() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.ATTACK,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.UNCOMMON,
			CardTarget.ENEMY
		);
		
		this.damage = this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = CNT;
		this.exhaust = true;
	}
	
	@Override
	public void applyPowers() {
		HashSet<String> set = new HashSet<>();
		for (AbstractCard card : AbstractDungeon.player.hand.group)
			set.add(card.cardID);
		
		this.rawDescription = (this.upgraded ? cardStrings.UPGRADE_DESCRIPTION :  DESCRIPTION) + " NL " +
				cardStrings.EXTENDED_DESCRIPTION[0] +
			set.size() / 3 + cardStrings.EXTENDED_DESCRIPTION[1];
		this.initializeDescription();
	}
	
	@Override
	public void onMoveToDiscard() {
		this.rawDescription = (this.upgraded ? cardStrings.UPGRADE_DESCRIPTION : DESCRIPTION);
		this.initializeDescription();
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		for (int i = 0; i < this.magicNumber; i++)
			this.addToBot(new DamageAction(m, new DamageInfo(p,
					this.damage, this.damageTypeForTurn)));
		
		HashSet<String> set = new HashSet<>(Collections.singleton(this.cardID));
		for (AbstractCard card : AbstractDungeon.player.hand.group)
			set.add(card.cardID);
		
		this.addToBot(new ApplyPowerAction(p, p, new MotivationPower(set.size() / 3)));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new FairyInSpring();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			this.exhaust = false;
			this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
			this.initializeDescription();
		}
	}
}