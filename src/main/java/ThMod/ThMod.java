package ThMod;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.cards.Cirno.*;
import ThMod.cards.CirnoDerivation.*;
import ThMod.characters.Cirno;
import ThMod.powers.Cirno.FairyPunchPower;
import ThMod.powers.Cirno.FrostKingPower;
import ThMod.powers.Cirno.MotivationPower;
import ThMod.relics.CrystalWings;
import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static ThMod.patches.AbstractCardEnum.CIRNO_COLOR;
import static ThMod.patches.AbstractCardEnum.CIRNO_DERIVATION_COLOR;
import static ThMod.patches.ThModClassEnum.CIRNO;

@SuppressWarnings("Duplicates")
@SpireInitializer
public class ThMod implements PostExhaustSubscriber,
		PostBattleSubscriber,
		PostDungeonInitializeSubscriber,
		EditCharactersSubscriber,
		PostInitializeSubscriber,
		EditRelicsSubscriber,
		EditCardsSubscriber,
		EditStringsSubscriber,
		OnCardUseSubscriber,
		EditKeywordsSubscriber,
		OnPowersModifiedSubscriber,
		PostDrawSubscriber,
		PostEnergyRechargeSubscriber,
		OnPlayerLoseBlockSubscriber,
		OnPlayerDamagedSubscriber,
		OnStartBattleSubscriber {
	
	public static final Logger logger = LogManager.getLogger(ThMod.class.getName());
	
//	private static final String MOD_BADGE = "img/UI/badge.png";
	
	//card backgrounds
	private static final String ATTACK_CC = "img/512/bg_attack_cirno_s.png";
	private static final String SKILL_CC = "img/512/bg_skill_cirno_s.png";
	private static final String POWER_CC = "img/512/bg_power_cirno_s.png";
	private static final String ENERGY_ORB_CC = "img/512/cardOrb.png";
	
	private static final String ATTACK_CC_PORTRAIT = "img/1024/bg_attack_cirno.png";
	private static final String SKILL_CC_PORTRAIT = "img/1024/bg_skill_cirno.png";
	private static final String POWER_CC_PORTRAIT = "img/1024/bg_power_cirno.png";
	private static final String ENERGY_ORB_CC_PORTRAIT = "img/1024/cardOrb.png";
	
	public static final Color CHILLED = CardHelper.getColor(0, 191, 255);
	public static final Color CHILLED_FLAVOR = CardHelper.getColor(204, 255, 255);
	public static final String CARD_ENERGY_ORB = "img/UI/energyOrb.png";
	
	private static final String MY_CHARACTER_BUTTON = "img/charSelect/CirnoButton.png";
	private static final String CIRNO_PORTRAIT = "img/charSelect/CirnoPortrait.jpg";
	
	private static final String CARD_STRING = "localization/ThMod_Fnh_cards.json";
	private static final String CARD_STRING_ZH = "localization/ThMod_Fnh_cards-zh.json";
	private static final String RELIC_STRING = "localization/ThMod_Fnh_relics.json";
	private static final String RELIC_STRING_ZH = "localization/ThMod_Fnh_relics-zh.json";
	private static final String POWER_STRING = "localization/ThMod_Fnh_powers.json";
	private static final String POWER_STRING_ZH = "localization/ThMod_Fnh_powers-zh.json";
	private static final String POTION_STRING = "localization/ThMod_Cirno_potions.json";
	private static final String POTION_STRING_ZH = "localization/ThMod_Cirno_potions-zh.json";
	private static final String KEYWORD_STRING = "localization/ThMod_Cirno_keywords.json";
	private static final String KEYWORD_STRING_ZH = "localization/ThMod_Cirno_keywords-zh.json";
	private static final String EVENT_PATH = "localization/ThMod_Cirno_events.json";
	private static final String EVENT_PATH_ZH = "localization/ThMod_Cirno_events-zh.json";

//	public static int typhoonCounter = 0;
//	public static boolean isCatEventEnabled;
//	public static boolean isDeadBranchEnabled;
	
//	private Properties ThModModDefaultProp = new Properties();
	
	//public static boolean OrinEvent = false;
	
	private final ArrayList<AbstractCard> cardsToAdd = new ArrayList<>();
	//private ArrayList<AbstractRelic> relicsToAdd = new ArrayList<>();
	
	public static int calcMotivated(AbstractCirnoCard card) { // 只计算 不修改
		if (card.motivationCost == 0)
			return 0;
		
		AbstractPlayer p = AbstractDungeon.player;
		
		if (!p.hasPower(MotivationPower.POWER_ID))
			return 0;
		
		AbstractPower motivation = p.getPower(MotivationPower.POWER_ID);
		
		if (motivation.amount <= 0)
			return 0;
		
		if (card.motivationCost < 0)
			return motivation.amount;
		
		return motivation.amount >= card.motivationCost ? card.motivationCost : 0;
	}
	
//	public static void frostKing(int amount) {
//		AbstractPlayer p = AbstractDungeon.player;
//
//		ArrayList<String> powers = new ArrayList<>();
//		powers.add(FrostKingMotivationPower.POWER_ID);
//		powers.add(FrostKingBlockPower.POWER_ID);
//
//		for (int i = 0; i < amount; i++)
//			for (String id : powers)
//				if (p.hasPower(id))
//					p.getPower(id).onSpecificTrigger();
//	}
	
	public static void frostKing(AbstractCard card) {
		AbstractPlayer p = AbstractDungeon.player;
		
		if (p.hasPower(FrostKingPower.POWER_ID))
			((FrostKingPower) p.getPower(FrostKingPower.POWER_ID)).trigger(card);
	}
	
	private static int iceWaveCounter = 0;
	
	public static void iceWaveClear() {
		iceWaveCounter = 0;
	}
	
	public static void iceWaveAdd(int amount) {
		iceWaveCounter += amount;
		
		logger.info("CirnoMod : iceWaveAdd, counter += " + amount +
				", and now counter = " + iceWaveCounter);
	}
	
	public static int iceWaveGet() {
		return iceWaveCounter;
	}
	
	private static final ArrayList<AbstractCard> fairyPunchList = new ArrayList<>();
	
	private static void fairyPunchAdd(AbstractCard card) {
		fairyPunchList.add(card);
	}
	
	private static boolean fairyPunchCheck(AbstractCard card) {
		return fairyPunchList.contains(card);
	}
	
	public static void fairyPunch(AbstractCard card) {
		if (AbstractDungeon.player.hasPower(FairyPunchPower.POWER_ID) &&
				card.type == AbstractCard.CardType.ATTACK && card.cost > 0) {
			
			if (!fairyPunchCheck(card)) {
				fairyPunchAdd(card);
				card.updateCost(-1);
			}
		}
	}
	
	public static void fairyPunchClear() {
		for (AbstractCard card : fairyPunchList) {
			card.updateCost(1);
			card.isCostModified = (card.cost != card.costForTurn);
		}
		
		fairyPunchList.clear();
	}
	
	public ThMod() {
		BaseMod.subscribe(this);
		logger.info("creating the color : CIRNO_COLOR and CIRNO_DERIVATION_COLOR");
		BaseMod.addColor(
				CIRNO_COLOR,
				CHILLED,
				CHILLED,
				CHILLED,
				CHILLED,
				CHILLED,
				CHILLED,
				CHILLED,
				ATTACK_CC,
				SKILL_CC,
				POWER_CC,
				ENERGY_ORB_CC,
				ATTACK_CC_PORTRAIT,
				SKILL_CC_PORTRAIT,
				POWER_CC_PORTRAIT,
				ENERGY_ORB_CC_PORTRAIT,
				CARD_ENERGY_ORB
		);
		BaseMod.addColor(
				CIRNO_DERIVATION_COLOR,
				CHILLED,
				CHILLED,
				CHILLED,
				CHILLED,
				CHILLED,
				CHILLED,
				CHILLED,
				ATTACK_CC,
				SKILL_CC,
				POWER_CC,
				ENERGY_ORB_CC,
				ATTACK_CC_PORTRAIT,
				SKILL_CC_PORTRAIT,
				POWER_CC_PORTRAIT,
				ENERGY_ORB_CC_PORTRAIT,
				CARD_ENERGY_ORB
		);
	}
	
	public void receiveEditCharacters() {
		logger.info("begin editing characters");
		logger.warn("What the FUCK is going on?");
		
		logger.info("add " + CIRNO.toString());
		BaseMod.addCharacter(
				new Cirno("Cirno"),
				MY_CHARACTER_BUTTON,
				CIRNO_PORTRAIT,
				CIRNO
		);
		logger.info("done editing characters");
	}
	
	public void receiveEditRelics() {
		logger.info("Begin editing relics.");
		BaseMod.addRelicToCustomPool(
				new CrystalWings(),
				CIRNO_COLOR
		);
		
		logger.info("Relics editing finished.");
	}
	
	public void receiveEditCards() {
		logger.info("starting editing cards");
		
		loadCardsToAdd();
		
		logger.info("adding cards for CIRNO");
		
		for (AbstractCard card : cardsToAdd) {
			logger.info("Adding card : " + card.name);
			BaseMod.addCard(card);
			
			UnlockTracker.unlockCard(card.cardID);
		}
		
		logger.info("done editing cards");
	}
	
	// 必须有这个函数才能初始化
	public static void initialize() {
		new ThMod();
	}
	
	public void receivePostExhaust(AbstractCard c) {
		// Auto-generated method stub
	}
	
	public void receivePostBattle(AbstractRoom r) {
	
	}
	
	public void receiveOnBattleStart(AbstractRoom abstractRoom) {
		iceWaveClear();
		logger.info("CirnoMod : iceWaveCounter reset");
		
		fairyPunchList.clear();
	}
	
	public void receiveCardUsed(AbstractCard c) {
		if (c instanceof AbstractCirnoCard) {
			AbstractCirnoCard card = (AbstractCirnoCard) c;
			if (!card.dontUsePatch) {
				card.setMotivated(ThMod.calcMotivated(card));
				
				logger.info("Try to calculateMotivated: " + card.cardID);
			}
			else
				card.setMotivated(0);
		}
	}
	
	public void receivePostEnergyRecharge() {
	
	}
	
	public void receivePowersModified() {
		// Auto-generated method stub
		
	}
	
	public void receivePostDungeonInitialize() {
		// Auto-generated method stub
	}
	
	public void receivePostDraw(AbstractCard arg0) {
		// Auto-generated method stub
	}
	
	public int receiveOnPlayerDamaged(int amount, DamageInfo damageInfo) {
		return amount; // 已经转移到patch了
	}
	
	public int receiveOnPlayerLoseBlock(int amount) {
		return amount;
	}
	
	private static String loadJson(String jsonPath) {
		return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
	}
	
	public void receiveEditKeywords() {
		logger.info("Setting up custom keywords");
		
		String keywordsPath;
		switch (Settings.language) {
			case ZHT:
			case ZHS:
				keywordsPath = KEYWORD_STRING_ZH;
				break;
			default:
				keywordsPath = KEYWORD_STRING;
				break;
		}
		
		Gson gson = new Gson();
		Keywords keywords;
		keywords = gson.fromJson(loadJson(keywordsPath), Keywords.class);
		for (Keyword key : keywords.keywords) {
			logger.info("Loading keyword : " + key.NAMES[0]);
			BaseMod.addKeyword(key.NAMES, key.DESCRIPTION);
		}
		logger.info("Keywords setting finished.");
	}
	
	@Override
	public void receiveEditStrings() {
		logger.info("start editing strings");
		
		String relicStrings,
				cardStrings,
				powerStrings,
				potionStrings,
				eventStrings,
				relic,
				card,
				power,
				potion,
				event;
		
		if (Settings.language == Settings.GameLanguage.ZHS || Settings.language == Settings.GameLanguage.ZHT) {
			logger.info("lang == zh");
			card = CARD_STRING_ZH;
			relic = RELIC_STRING_ZH;
			power = POWER_STRING_ZH;
			potion = POTION_STRING_ZH;
			event = EVENT_PATH_ZH;
		} else {
			logger.info("lang == eng");
			card = CARD_STRING;
			relic = RELIC_STRING;
			power = POWER_STRING;
			potion = POTION_STRING;
			event = EVENT_PATH;
		}
		
		relicStrings = Gdx.files.internal(relic).readString(
				String.valueOf(StandardCharsets.UTF_8)
		);
		BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
		cardStrings = Gdx.files.internal(card).readString(
				String.valueOf(StandardCharsets.UTF_8)
		);
		BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
		powerStrings = Gdx.files.internal(power).readString(
				String.valueOf(StandardCharsets.UTF_8)
		);
		BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
		potionStrings = Gdx.files.internal(potion).readString(
				String.valueOf(StandardCharsets.UTF_8)
		);
		BaseMod.loadCustomStrings(PotionStrings.class, potionStrings);
		eventStrings = Gdx.files.internal(event).readString(
				String.valueOf(StandardCharsets.UTF_8)
		);
		BaseMod.loadCustomStrings(EventStrings.class, eventStrings);
		logger.info("done editing strings");
	}
	
	public void receivePostInitialize() {
		// Nothing
	}
	
	private void loadCardsToAdd() {
		cardsToAdd.clear();
		
		cardsToAdd.add(new AbsoluteZero());
//		cardsToAdd.add(new AchiCirno());
		cardsToAdd.add(new AchiForm());
//		cardsToAdd.add(new AssaultArmor());
		cardsToAdd.add(new ButterflyFairysHelp());
		cardsToAdd.add(new Chirumiru());
		cardsToAdd.add(new ColdBeer());
		cardsToAdd.add(new ColdSprinkler());
		cardsToAdd.add(new ColdWind());
		cardsToAdd.add(new CoolSunflower());
		cardsToAdd.add(new DaiyouseisHelp());
		cardsToAdd.add(new DiamondBlizzard());
		cardsToAdd.add(new FairyInSpring());
		cardsToAdd.add(new FairyPunch());
		cardsToAdd.add(new FairySpin());
		cardsToAdd.add(new Flee());
//		cardsToAdd.add(new FreezeActress());
		cardsToAdd.add(new FreezeAtmosphere());
		cardsToAdd.add(new FreezeDrying());
		cardsToAdd.add(new FreezeTouchMe());
		cardsToAdd.add(new FreezingBeams());
		cardsToAdd.add(new Fridge());
		cardsToAdd.add(new FrostColumns());
		cardsToAdd.add(new FrostKing());
		cardsToAdd.add(new FrostMeteor());
		cardsToAdd.add(new FrostPillars());
		cardsToAdd.add(new FrozenFreeze());
		cardsToAdd.add(new FrozenFrog());
		cardsToAdd.add(new FrozenSpring());
		cardsToAdd.add(new GiganticIceMagic());
		cardsToAdd.add(new GlacialAugment());
		cardsToAdd.add(new GreatCrusher());
		cardsToAdd.add(new Hailstorm());
		cardsToAdd.add(new HighSpirit());
		cardsToAdd.add(new IceArmor());
		cardsToAdd.add(new IceBarrier());
		cardsToAdd.add(new IceCharge());
		cardsToAdd.add(new IceCreamMachine());
		cardsToAdd.add(new IceDumplings());
		cardsToAdd.add(new IceDuplicate());
		cardsToAdd.add(new IceExperience());
		cardsToAdd.add(new IceFall());
		cardsToAdd.add(new IceFishing());
		cardsToAdd.add(new IceGrain());
//		cardsToAdd.add(new IceKick());
		cardsToAdd.add(new IceMachineGun());
		cardsToAdd.add(new IceSpear());
		cardsToAdd.add(new IceTornado());
		cardsToAdd.add(new IceWave());
		cardsToAdd.add(new IcicleConeCrush());
		cardsToAdd.add(new IcicleShot());
		cardsToAdd.add(new ImFunky());
		cardsToAdd.add(new KirisameMahouten());
		cardsToAdd.add(new LilyWhitesHelp());
		cardsToAdd.add(new LunaticFairysHelp());
		cardsToAdd.add(new MinusK());
		cardsToAdd.add(new NineMathTextbooks());
		cardsToAdd.add(new PerfectFreeze());
		cardsToAdd.add(new PerfectGlacialist());
		cardsToAdd.add(new PerfectSummerIce());
		cardsToAdd.add(new SecretSmoothie());
		cardsToAdd.add(new ShowOff());
		cardsToAdd.add(new SnowmanInMidsummer());
		cardsToAdd.add(new Sunbathe());
		cardsToAdd.add(new SuperIceKick());
//		cardsToAdd.add(new SwordFreezer());
		cardsToAdd.add(new TengusCamera());
		cardsToAdd.add(new ThreeFairiesHelp());
		cardsToAdd.add(new WishOfBreeze());
		cardsToAdd.add(new ZettaiRyoiki());
		
		cardsToAdd.add(new RedTextbook());
		cardsToAdd.add(new YellowTextbook());
		cardsToAdd.add(new BlueTextbook());
		
		cardsToAdd.add(new SunnyMilksHelp());
		cardsToAdd.add(new LunaChildsHelp());
		cardsToAdd.add(new StarSapphiresHelp());
		
		cardsToAdd.add(new IceConical());
		cardsToAdd.add(new IceCube());
		
		cardsToAdd.add(new MarisasPotion());
	}
	
	
	static class Keywords {
		
		Keyword[] keywords;
	}
	
//	public static AbstractCard getRandomCirnoCard() {
//		return AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
//	}
}

/*
                                                                                                    #W&888888888&&88888&W
                                                    88888&W                                 W88W*abdqmZ0QQLCCCJJJJJJCQ0ZqdaM8%
                                                  8WwUUJQOmb*8&                        &8Madm0CYzvvuuuuuuvvvvvvvvvvvvvuuuuvXw&8
                                 8               8aYuvXJCCJccJZkW8888&#ohkkhaa*##8%%%&bZCXvuuuvvvvvvvvvvvvvvvvvvvvvvvvvvzJZqb&8
                                 88             8kcuuOaooooaqJYYJCccYzvnjjf/\)}[[{(tnvzUCLLCYcvvvvvvvvvvvvvvvvvvvvvvucCmbhkkW8
                                 %88bk@        8#cXJCOLzurrxcQvjjjjncvvvvcc)})||}+>iii><+?1tvC0QUYC00LYvvvvvvvvvvvuz0dhhkkkW8
                                   8kJnncCZqqqwpLux(]~>iiiii>|rxxxxxrrjf/(}<>>><_]_<<<<<<>>>i<-|vZa*ohk0vvvvvvvvvvCdhkkkkkW8
                                    $8wX\?_?][_<>>>>><<<<<<<<<+-]]]?-_+~<>><<<<<<<<<<<<<<<<<<<<>i<?fLk#quvvvzzvvuYhkkkkkkW8
                                       $%WbOx|_><<<<<>~<<<<<<<<>>>>>><<<<<<<<<<<<<<~_>><<<<<<<<<<<>i>-rZZCwdkdpwLXphhkkkM8
                                     $%kLn)_>><<<<<<>)(><<<<<<<<<<<<<<<<<<<<<<<<<<<<\j}~<>><<<<<<<<<<>i<|0#okkkhhqcCZqoW8
                                  $%pX/?>i><<<<<<<<i/j<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>]cLj([+>><<<<<<<<<<>>}JookhhwvvuUo88
                                BoJ\+ii><<<<<<<<<<>(ci<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>i\mXrr|[~><<<<<<<<<<>i}0omJcvvvvcCb88
                              %hc]i><<<<<<<<<<<<<<<Q_><<<<<<<<<<<<<<<<<<<<<<<><<<<<<<<<i?ZJjxxf{+>><<<<<<<<<>>/wXvvvvvvvuJh88
                            %ac+__<<<<<<<<<<<<<<<>)Ci<<<<<<<<<<<<<<<<<<<<<<<<]<<<<<<<<<<>+wUjxxxj(->><<<<<<<<<!(hzvvvvvvvvv0#8
                          BWU-l\Y+<<<<<<<<<<<<<<<ixci<<<<<<<<<<<<<<<<<<>><<<~C><<<i><<<<~>]dzrxxxxx|_><<<<<<<<<irkcvvvvvvvvuX#88
                        $8p)i!xC~><<<<<<<<<<<<<<<invi<<l"";<<<>ll<<<<<i''!>ick<<<` 'i<<>,":/krxxxxxxr1~><<<<<<<<!UhvvvvvvvvvXk*88
                       %Z|<!iu0<<<<<<<<<<I^`l<>::lnJ!~!    i<<i^"><>><_])/xmppci<!I!<<<>"^;>pUrxxxxxxnf?><<<<<<<>{bwuvvvvvvuQhb*88
                      %J>!>/mb!":i<><<<<i.  :~<;:1bm><i"`^I<<<<<<<<]\xj/{(qJ|/p{i<<<<>><<<>?pdjxxxxxxxxx{><<<<<<>}v*UvvvvvvXbkkb*8B
                     %m>![Z%8+   l>`^><<<>!i<<<>{hjm\i<<<<<<<<<<<>~)]~ii/mu-!]jp(_-[|X(_+~>tm#nrxxxxxxxxn)><<<<<>\r0huvvvvXdkkkkbM8
                    %o-!f*B%nl;;i<<>><<<<<<~+_~?bv)jq+><<<<<<<>><])?--/QL)!`'':[mJrrUMbxjftUOMzrxxxxxxxxxn1><<<>]xrcMXvvuUbkkkkkkh88
                    %cIf&B%p><<<<<<<<<>><<<{|tcoqYvi10+>>>>>><_{frXQvmmx~``,Ill;>zOckuYbcjuQQ#Xrxxxxxxxxxxx]><>}rxrx#CucOkkkkkkkkk&8
                   %W]\M% %ji<<<<<<<><}[><~_}|wm(_>;.{mf{}}{(fxxrJCqCr)jX0ZmqpbhhbW@@JfupLUQQ#crxxxxxxxxxxxf<~(xxxxro0JpkkkkkkkkkkM8
                   8#fh8 %W-><<<<<>>-v0pC/fxxcM/:,!l;"<QQnxxxxxrXQ1;>XapYrUZwbaa*hkbh@$kYQkw0#urxxxxxxxxxxxx|jxxxxxroaaaaaaahkkkkkM8
                   8Mu#8 8o[+<<<<+]\xLQQhaYxfQd-(vcuxfI^|QUxrrxjZf:._v{;<OhOCZqqqboWkXO@8mzLhoxrxxxxxxxxxxxxxxxxxxrvW*aaaaaao#okka88
                   %&Q#8 8axrf//fjxxxLQLa0XJJkBMdZp*Mp<.':|JJvxjm+'^,` `<I;XpaM#MZQwo8\/bWd_cpjxxxxxxxxxxxxxxxxxxxr0MbbkkkkkkkMhk&8
                    %8&8$8#nxxxxxxxxxCLq#1.}88C/0#bbo#a('`'"-xJXd?'^^. vQ/}!|Bbwa#||rqk:10/;pXrxxxxxxxxxxxrxxxxxxx(tMoahkkkkkbkaW8
                      888880jxxxxxxxrX0WL!]&Mt>UQfJdhdp8\'^`'':_{:`^^' Co{{_v8oa#8/+Ir#.!l.)oCcrxxxxxrrrxuvuxxxxrf<]&&8&MMM###M8&
                          8WUjrxxxxxxxC%U{p8U!(/;<xohWqjo+'^^^`''`^^^^.+a/}}{fzvx]_-1k)..'>bmujxxxrrxuzULQ0CrxxjCJIf%       bb
                           8W0xjrxxxxjU8#jOMx'v$x{!Qah@_Jz.^^^^^^^^^^^^.-w0u/)>::l[UC-.,?uz1:"ifxxuYCQ0QQQ0XrrrQ8r~h%
                            8WpQcxxxxj0ohq-t['!Wz[{rzX/|*{'^^^^^^^^^^^`^':}uUUcjrnf[~]jcx]>i"^+rcJQ0QQQQQQYrrXpMQ1b&&
                            8Y(nCLxxxrJp08m"^` ~mU/]:i/bj'^^^^^```'''`"`'`''`""I])fvu/?>i<+?{/cCQQQQQQQQCurzq**bLoadOb*88&Mb
                           %*!.^<1xxxxxCa&aI`^^`,}rnuJc+'`^^`''^;i+}(t\f~'^^^`'_JpUx//f(\fjxcCQQQQQQQCXuvCb*ooMaakkkOuzJ0mwpbbkhahka&88
                           %mi><>_xrrrrjcov.',>~~>>:"^''^^^'+xnLn\(}?+~?z_'^^^^``:>)jbQxxxvJQQQQQLJYXXCpaohkhhkkkkkkhZvuuuuuuuuuvJmh&8%
                           %0<~+?txvcccvnuZY~";lllI"``^^^^^'<bq\<<<<~~~<?X`^^^^^^`''JJfxvULQQQLQ0ZmdhaohkkkkkkkkkkkkkkqYuvczYLOmpW8*
                           8*jfvujcQQQZqpoqCv:'`````^^^^^^^^']Z]<<~~~~~<+C^`^^^`''._oxcLmpbh*hhaaaahkkkkkkkkkkkkhaoo*#M#bha*&88b
                           $8wjZ#wQ00QLLL0pc?:'.'```^^^^^^^^^';\t([-___?f}'`'.`;~(zk*mho*&& 8Mkbbkkkkkkkkkkhao#M&88&WMb
                             8bC8W8&M*aahkk*8bX/?l"`'..''''''`'.:~})((\vt;"I+fx|Xb*##ahpXCk8 8%Moakkkkkkaapb#@$
                              &88%      o#MM8 $$%owQXnt|{?<>ii<~~}-+_|ObkhQwhJ;  '>rdWMha8BWX||ru0kaoaaoqt>;!<](xL@
                                %$               Jn/|nJZwkmOk#qaMkhaMWowc|jab'      ix/jmv[)((/juzJL0OO0x>}j(]<ll>+](rCh
                                             Cn}+~>+""}}}qhdw*(/aaW*oWhl.-Oqc^".    I)qqLj/frucXUJJJUUYXc|fvxjf(}?~l"`:>-{/nYC
                                             cr{](fvrnJJU0dhhLZZ0hhk#a#u>wUUQnnmr~"l)\Qc)/Y0CYcxjfjt\|())(())(()1{[]?_++_])fcJ
                                         %%B%&8$omJznxxnn(1fLc(_C0vwkkCCzqcvvYb%$M\<Od}"..';~]|xY0ZOLUzvvvChbmJXJOW$$$
                                       $MqQf}<<<!:"^`''""`_rrqkdqncQpwcvccvvzd8   Wm880t?+I.....',>}fcCq88L/+;;xzmMB
                                 $%#bm0LQCLJf({jUQ0QLCJ0JcncLCd*JmdbqLvvvvccU0whCjfnjjXwdp0v/]i:^'....',+~`.',)v;~zO*%$
                            $#bkwZJYn((]I `+{)|trcJQZwpp0YzQ0O*OhhbOQQ0QCCCXCddkhw)}trf/\\\(j0obQXuf([_>;,`.`]-I:|vh&8
                          Bpn}-_<<<<~_]|cc|,    '>/vuuuvczXXU0baaakpZLYzcvvvuYko*8Yr)fYCUYXn{_-|O  $$B%MhbCfnO<wxO&#%
                         %Q<i><<<<<<<<>}(\vQ/''-jcccccvvvvY0dkbwQYcvvcvvvvvvvu0a*&&wYf1rUJJJU[!;!}ud         %b%$W8
                         %Q>><<<<<<<<<<}\|(|U0-lI;Ili|cvLdkq0Uvvvczv/[xcvvvczcChkkM8 mc\)nUJU{)v[,"_tL$
                          %w|>><<<<<<<<+(|||)rq}    `nccQJcf)?xzcj?"  Invvnj)+-1OhkW8  Cx\(((\\\\}l. l}nZ
                           $BZ)><<<<<<<<?\|||(/w/^  !Xcn(+:.  [/_l!~_--fYt?l"l>!~Cha8$   Lvunxf\){]?~>>?jJY
                           Bpv{<<<<<<<<<<]\|||)fwz\>-j<^  .:<}cj[])trxrjjfJbvzvj1+Cn#B              $@aQuY~
                           BJ>!><<<<<<<<<<-)()x0l"{cUznxnLLvndx,'''^;_)||Cmj(|/jXO]-W%
                            Bpv1~ii>>>>>>>i~/Lw>'`',~}/z0Y\1X/.`^^^``'^/0X((|||(1LQt%
                              $&qUuf|(((\xXYr\!.``''I|QX/((fL'`^^^^^`!YL\)|||||\(/ck@
                                 $$BB%%BBdr?_-+l:"~xXv/(||(x0"'````^`{{cJj(||\|{~~ZB
                                      B#n__-???_]nu]>+}(|\\|vJf`;lI^^`'`~uY\1[+!]pB
                                     %&t+-???__rdd}ii><~-]{1{(cn}l;^^^^^''?O{!]zoB
                                     %L+???-_\ZB $WQj[~>ii>><<>_jn~'`^^^^^.~w0*B
                                     BU~__+]J&$    $@*qQv/{]?___+)0c:.`^^^^.]%$
                                     $MY/fXb@          $@B&*abdbh#&Bp{''^^^^.X%
                                       $B@$                         $%Ul'`'''<&%
                                                                      @k["_?l"o%
                                                                       $a:;I"_oB
                                                                        8XtfYM@
                                                                         $$$
*/