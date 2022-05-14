package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GiganticIceMagic extends AbstractCirnoCard {
	
	public static final String ID = GiganticIceMagic.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	
	private static final int ATTACK_DMG = 30;
	private static final int UPGRADE_PLUS_DMG = 10;
	
	public GiganticIceMagic() {
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
		this.magicNumber = this.baseMagicNumber = -1;
	}
	
	@Override
	public void applyPowers() {
		super.applyPowers();
		
		int cnt = 0;
		for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn)
			if (c != this)
				cnt++;
		this.magicNumber = cnt;
		
		int tmp = this.baseDamage;
		
		this.baseDamage -= 12 * cnt;
		this.calculateCardDamage(null);
		this.baseDamage = tmp;
		
		initializeDescription();
	}
	
	@Override
	public void triggerOnCardPlayed(AbstractCard c) {
		this.applyPowers();
	}
	
	@Override
	public void onMoveToDiscard() {
		this.magicNumber = -1;
		initializeDescription();
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.applyPowers();
//		this.addToBot(new VFXAction()); TODO
		this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage,
				this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
	}
	
	@Override
	public void initializeDescription() {
		this.rawDescription = DESCRIPTION;
		if (this.magicNumber >= 0)
			this.rawDescription += " NL " + cardStrings.EXTENDED_DESCRIPTION[0] +
					this.magicNumber + cardStrings.EXTENDED_DESCRIPTION[1];
		
		super.initializeDescription();
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new GiganticIceMagic();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			this.upgradeDamage(UPGRADE_PLUS_DMG);
			this.initializeDescription();
		}
	}
}