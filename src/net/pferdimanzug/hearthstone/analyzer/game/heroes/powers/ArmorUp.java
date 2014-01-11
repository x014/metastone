package net.pferdimanzug.hearthstone.analyzer.game.heroes.powers;

import net.pferdimanzug.hearthstone.analyzer.game.actions.TargetSelection;
import net.pferdimanzug.hearthstone.analyzer.game.spells.BuffHeroSpell;

public class ArmorUp extends HeroPower {

	public static final int ARMOR_BONUS = +2;

	public ArmorUp() {
		super("Armor Up!");
		setTargetRequirement(TargetSelection.NONE);
		setSpell(new BuffHeroSpell(0, ARMOR_BONUS));
	}

}