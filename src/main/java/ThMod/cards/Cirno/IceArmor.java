package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Cirno.ChillPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class IceArmor extends AbstractCirnoCard {
	
	public static final String ID = IceArmor.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int BLOCK = 4;
	private static final int UPGRADE_PLUS_BLOCK = 2;
	private static final int HAND_SIZE = 5;
	private static final int UPGRADE_PLUS_HAND_SIZE = -1;
	private static final int CHILL_GAIN = 1;
	
	public IceArmor() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.SKILL,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.COMMON,
			CardTarget.SELF
		);
		
		this.block = this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = HAND_SIZE;
		this.chillGain = CHILL_GAIN;
	}
	
	@Override
	public void triggerOnGlowCheck() {
		super.triggerOnGlowCheck();
		
		if (AbstractDungeon.player.hand.size() >= this.magicNumber)
			this.glowColor = GOLD_BORDER_GLOW_COLOR.cpy();
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		for (int i = 0; i < 2; i++)
			this.addToBot(new GainBlockAction(p, this.block));
		
		int size = p.hand.group.size();
		if (!p.hand.group.contains(this))
			size++;
		
		if (size >= this.magicNumber)
			this.addToBot(new ApplyPowerAction(p, p, new ChillPower(this.chillGain)));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new IceArmor();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			this.upgradeBlock(UPGRADE_PLUS_BLOCK);
			this.upgradeMagicNumber(UPGRADE_PLUS_HAND_SIZE);
			this.initializeDescription();
		}
	}
}