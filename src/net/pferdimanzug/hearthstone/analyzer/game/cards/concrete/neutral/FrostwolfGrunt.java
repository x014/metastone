package net.pferdimanzug.hearthstone.analyzer.game.cards.concrete.neutral;

import net.pferdimanzug.hearthstone.analyzer.game.GameTag;
import net.pferdimanzug.hearthstone.analyzer.game.cards.MinionCard;
import net.pferdimanzug.hearthstone.analyzer.game.cards.Rarity;
import net.pferdimanzug.hearthstone.analyzer.game.entities.heroes.HeroClass;
import net.pferdimanzug.hearthstone.analyzer.game.entities.minions.Minion;

public class FrostwolfGrunt extends MinionCard {

	public FrostwolfGrunt() {
		super("Frostwolf Grunt", Rarity.FREE, HeroClass.ANY, 2);
	}

	@Override
	public Minion summon() {
		return createMinion(2, 2, GameTag.TAUNT);
	}

}