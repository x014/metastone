package net.pferdimanzug.hearthstone.analyzer.game.cards.concrete.neutral;

import net.pferdimanzug.hearthstone.analyzer.game.GameTag;
import net.pferdimanzug.hearthstone.analyzer.game.actions.Battlecry;
import net.pferdimanzug.hearthstone.analyzer.game.actions.TargetSelection;
import net.pferdimanzug.hearthstone.analyzer.game.cards.MinionCard;
import net.pferdimanzug.hearthstone.analyzer.game.cards.Rarity;
import net.pferdimanzug.hearthstone.analyzer.game.entities.heroes.HeroClass;
import net.pferdimanzug.hearthstone.analyzer.game.entities.minions.Minion;
import net.pferdimanzug.hearthstone.analyzer.game.spells.SingleTargetDamageSpell;

public class ElvenArcher extends MinionCard {

	private static final int BATTLECRY_DAMAGE = 1;

	public ElvenArcher() {
		super("Elven Archer", Rarity.FREE, HeroClass.ANY, 1);
	}

	@Override
	public Minion summon() {
		Minion elvenArcher = createMinion(1, 1);
		Battlecry battlecry = Battlecry.createBattlecry(new SingleTargetDamageSpell(BATTLECRY_DAMAGE), TargetSelection.ANY);
		elvenArcher.setTag(GameTag.BATTLECRY, battlecry);
		return elvenArcher;
	}

}