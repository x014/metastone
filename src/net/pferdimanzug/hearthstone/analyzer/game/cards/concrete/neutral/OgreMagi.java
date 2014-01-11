package net.pferdimanzug.hearthstone.analyzer.game.cards.concrete.neutral;

import net.pferdimanzug.hearthstone.analyzer.game.GameTag;
import net.pferdimanzug.hearthstone.analyzer.game.cards.MinionCard;
import net.pferdimanzug.hearthstone.analyzer.game.cards.Rarity;
import net.pferdimanzug.hearthstone.analyzer.game.entities.heroes.HeroClass;
import net.pferdimanzug.hearthstone.analyzer.game.entities.minions.Minion;

public class OgreMagi extends MinionCard {

	public OgreMagi() {
		super("Ogre Magi", Rarity.FREE, HeroClass.ANY, 4);
	}

	@Override
	public Minion summon() {
		Minion ogreMagi = createMinion(4, 4);
		ogreMagi.setTag(GameTag.SPELL_POWER, 1);
		return ogreMagi;
	}

}