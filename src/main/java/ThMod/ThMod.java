package ThMod;

// import static ThMod.patches.AbstractCardEnum.MARISA_COLOR;
// import static ThMod.patches.CardTagEnum.SPARK;
// import static ThMod.patches.ThModClassEnum.MARISA;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.cards.Cirno.*;
import ThMod.cards.CirnoChoiceCards.*;
import ThMod.characters.Cirno;
import ThMod.relics.CrystalWings;
import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
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
import java.util.Properties;

import static ThMod.patches.AbstractCardEnum.*;
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
		PostEnergyRechargeSubscriber {
	
	public static final Logger logger = LogManager.getLogger(ThMod.class.getName());
	
	private static final String MOD_BADGE = "img/UI/badge.png";
	
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
	
	private Properties ThModModDefaultProp = new Properties();
	
	//public static boolean OrinEvent = false;
	
	private ArrayList<AbstractCard> cardsToAdd = new ArrayList<>();
	//private ArrayList<AbstractRelic> relicsToAdd = new ArrayList<>();
	
	public static int calcMotivated(AbstractCirnoCard card) { // 只计算 不修改
		AbstractPlayer p = AbstractDungeon.player;
		
		if (!p.hasPower("MotivationPower"))
			return 0;
		
		AbstractPower motivation = p.getPower("MotivationPower");
		
		if (motivation.amount <= 0)
			return 0;
		
		if (card.motivationCost < 0)
			return motivation.amount;
		
		return motivation.amount >= card.motivationCost ? card.motivationCost : 0;
	}

  /*
  //For Spark Themed cards
  public static boolean isSpark(AbstractCard card) {
    return (
        (card.cardID.equals("Spark")) ||
            (card.cardID.equals("DarkSpark")) ||
            (card.cardID.equals("Strike_MRS")) ||
            (card.cardID.equals("FinalSpark")) ||
            (card.cardID.equals("DoubleSpark")) ||
            (card.cardID.equals("RefractionSpark")) ||
            (card.cardID.equals("MachineGunSpark")) ||
            (card.cardID.equals("MasterSpark"))
    );
  }*/
	
	//For the FXXKING Exhaustion curse
  /*
  public static boolean ExhaustionCheck() {
    boolean res = false;
    for (AbstractCard c : AbstractDungeon.player.hand.group) {
      if (c instanceof Exhaustion_MRS) {
        res = true;
      }
    }
    return res;
  }
*/
	//For Amplify cards
//  public static boolean Amplified(AbstractCard card, int AMP) {
//    logger.info(
//        "ThMod.Amplified : card to check : "
//            + card.cardID
//            + " ; costForTurn : "
//            + card.costForTurn
//    );
//    AbstractPlayer p = AbstractDungeon.player;
//    if ((p.hasPower("OneTimeOffPlusPower")) || (p.hasPower("OneTimeOffPower"))) {
//      logger.info("ThMod.Amplified :OneTimeOff detected,returning false.");
//      return false;
//    }
//
//    boolean res = false;
//    if ((p.hasPower("MilliPulsaPower")) || (p.hasPower("PulseMagicPower"))
//        || (card.freeToPlayOnce) || (card.purgeOnUse)) {
//      logger.info(
//          "ThMod.Amplified :Free Amplify tag detected,returning true : Milli :"
//              + (p.hasPower("MilliPulsaPower"))
//              + " ; Pulse :"
//              + (p.hasPower("PulseMagicPower"))
//              + " ; Free2Play :"
//              + card.freeToPlayOnce
//              + " ; purge on use :"
//              + card.purgeOnUse
//      );
//      res = true;
//    } else {
//      if (EnergyPanel.totalCount >= (card.costForTurn + AMP)) {
//        logger.info("ThMod.Amplified : Sufficient energy ,adding and returning true;");
//        card.costForTurn += AMP;
//        res = true;
//        if (card.costForTurn > 0) {
//          logger
//              .info("ThMod.Amplified : False instance of 0 cost card,decreasing typhoon counter.");
//          typhoonCounter--;
//          logger.info("current Typhoon Counter : " + typhoonCounter);
//        }
//      }
//    }
//
//    if (res) {
//      AbstractDungeon.actionManager.addToTop(
//          new ApplyPowerAction(
//              p,
//              p,
//              new GrandCrossPower(p)
//          )
//      );
//      if (p.hasPower("EventHorizonPower")) {
//        p.getPower("EventHorizonPower").onSpecificTrigger();
//      }
//      if (p.hasRelic("AmplifyWand")) {
//        AbstractRelic r = p.getRelic("AmplifyWand");
//        r.onTrigger();
//      }
//    }
//    logger.info(
//        "ThMod.Amplified : card : " + card.cardID + " ; Amplify : " + res + " ; costForTurn : "
//            + card.costForTurn);
//    return res;
//  }
	
	public static void frostKing() {
		AbstractPlayer p = AbstractDungeon.player;
		
		ArrayList<String> powers = new ArrayList<>();
		powers.add("FrostKingMotivationPower");
		powers.add("FrostKingBlockPower");
		
		for (String id : powers)
			if (p.hasPower(id))
				p.getPower(id).onSpecificTrigger();
	}
	
	public ThMod() {
		BaseMod.subscribe(this);
		logger.info("creating the color : CIRNO_COLOR and CIRNO_CHOICE_COLOR");
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
				CIRNO_CHOICE_COLOR,
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
//		ThModModDefaultProp.setProperty("isCatEventEnabled", "TRUE");
//		try {
//			final SpireConfig config = new SpireConfig("vexMod", "vexModConfig", ThModModDefaultProp);
//			config.load();
////			isCatEventEnabled = config.getBool("isCatEventEnabled");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
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
//		BaseMod.addRelicToCustomPool(
//				new BigShroomBag(),
//				MARISA_COLOR
//		);
		
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
	
	public static void initialize() {
		new ThMod();
	}
	
	@Override
	public void receivePostExhaust(AbstractCard c) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void receivePostBattle(AbstractRoom r) {
//		typhoonCounter = 0;
//		logger.info("ThMod : PostBattle ; typhoon-counter reset");
	}
	
	@Override
	public void receiveCardUsed(AbstractCard card) {
		ThMod.logger.info("ThMod : Card used : " + card.cardID + " ; cost : " + card.costForTurn);
		if (
				(card.costForTurn == 0) ||
						(card.costForTurn <= -2) ||
						((card.costForTurn == -1) && (AbstractDungeon.player.energy.energy <= 0))
		) {
//			typhoonCounter++;
//			ThMod.logger.info("typhoon-counter increased , now :" + typhoonCounter);
		}
		if (card.retain) {
			card.retain = false;
		}
//		if (card.hasTag(SPARK)) {
//			AbstractDungeon.actionManager.addToTop(
//					new SparkCostAction()
//			);
//		}
	}
	
	@Override
	public void receivePostEnergyRecharge() {
//		if (!AbstractDungeon.player.hand.isEmpty()) {
//			for (AbstractCard c : AbstractDungeon.player.hand.group) {
//				if (c instanceof GuidingStar) {
//					AbstractDungeon.actionManager.addToBottom(
//							new GainEnergyAction(1)
//					);
//					c.flash();
//				}
//			}
//		}
	}
	
	@Override
	public void receivePowersModified() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void receivePostDungeonInitialize() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void receivePostDraw(AbstractCard arg0) {
		// TODO Auto-generated method stub
	}
	
	private static String loadJson(String jsonPath) {
		return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
	}
	
	@Override
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
		}
		else {
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
	
	@Override
	public void receivePostInitialize() {
		// Nothing
/*
    //BaseMod.addEvent(TestEvent.ID, TestEvent.class);
    BaseMod.addEvent(TestEvent.ID, TestEvent.class, Exordium.ID);
    BaseMod.addEvent(TestEvent.ID, TestEvent.class, TheBeyond.ID);
    BaseMod.addEvent(TestEvent.ID, TestEvent.class, TheCity.ID);
*/
    /*
    String orin, zombieFairy;
    switch (Settings.language) {
      case ZHS:
        orin = ORIN_ENCOUNTER_ZHS;
        zombieFairy = ZOMBIE_FAIRY_ENC_ZHS;
        break;
      default:
        orin = ORIN_ENCOUNTER;
        zombieFairy = ZOMBIE_FAIRY_ENC;
        break;
    }
    */
	}
//	@Override
//	public void receiveEditCards() {
//		// This finds and adds all cards in the same package (or sub-package) as MyAbstractCard
//		// along with marking all added cards as seen
//		new AutoAdd("CirnoMod")
//				.packageFilter(AbstractCirnoCard.class)
//				.setDefaultSeen(true)
//				.cards();
//	}
	private void loadCardsToAdd() {
		cardsToAdd.clear();
		
		cardsToAdd.add(new AbsoluteZero());
//		cardsToAdd.add(new AchiCirno());
		cardsToAdd.add(new AssaultArmor());
		cardsToAdd.add(new ButterflyFairysHelp());
		cardsToAdd.add(new Chirumiru());
		cardsToAdd.add(new ColdBeer());
		cardsToAdd.add(new ColdSprinkler());
		cardsToAdd.add(new DaiyouseisHelp());
		cardsToAdd.add(new DiamondBlizzard());
		cardsToAdd.add(new FairySpin());
		cardsToAdd.add(new Flee());
		cardsToAdd.add(new FreezeActress());
		cardsToAdd.add(new FreezeAtmosphere());
		cardsToAdd.add(new FreezeDrying());
		cardsToAdd.add(new FreezeTouchMe());
		cardsToAdd.add(new FreezingBeams());
		cardsToAdd.add(new Fridge());
		cardsToAdd.add(new FrostKing());
		cardsToAdd.add(new FrostMeteor());
		cardsToAdd.add(new FrostPillars());
		cardsToAdd.add(new FrozenFreeze());
		cardsToAdd.add(new FrozenSpring());
		cardsToAdd.add(new GiganticIceMagic());
		cardsToAdd.add(new GreatCrusher());
		cardsToAdd.add(new Hailstorm());
		cardsToAdd.add(new HighSpirit());
		cardsToAdd.add(new IceBarrier());
		cardsToAdd.add(new IceFall());
		cardsToAdd.add(new IceFishing());
		cardsToAdd.add(new IceGrain());
		cardsToAdd.add(new IceKick());
		cardsToAdd.add(new IceMachineGun());
		cardsToAdd.add(new IceSpear());
		cardsToAdd.add(new IcicleConeCrush());
		cardsToAdd.add(new IcicleShot());
		cardsToAdd.add(new ImFunky());
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
		cardsToAdd.add(new SwordFreezer());
		cardsToAdd.add(new TengusCamera());
		cardsToAdd.add(new ThreeFairiesHelp());
		cardsToAdd.add(new WishOfBreeze());
		
		cardsToAdd.add(new RedTextbook());
		cardsToAdd.add(new YellowTextbook());
		cardsToAdd.add(new BlueTextbook());
		
		cardsToAdd.add(new SunnyMilksHelp());
		cardsToAdd.add(new LunaChildsHelp());
		cardsToAdd.add(new StarSapphiresHelp());
	}
	
	static class Keywords {
		
		Keyword[] keywords;
	}
	
	public static AbstractCard getRandomCirnoCard() {
		return AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
	}
	
}
