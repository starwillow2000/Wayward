package net.wayward_realms.waywardskills.specialisation;

import net.wayward_realms.waywardlib.skills.Specialisation;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SwordMagicSpecialisation extends SpecialisationBase {

    public SwordMagicSpecialisation(Specialisation parent) {
        super(parent);
    }

    @Override
    public String getName() {
        return "Sword Magic";
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public boolean meetsAttackRequirement(ItemStack item) {
        return item.getType() == Material.WOOD_SWORD || item.getType() == Material.STONE_SWORD || item.getType() == Material.IRON_SWORD || item.getType() == Material.GOLD_SWORD || item.getType() == Material.DIAMOND_SWORD;
    }

    @Override
    public boolean meetsDefenceRequirement(ItemStack item) {
        return item.getType() == Material.WOOD_SWORD || item.getType() == Material.STONE_SWORD || item.getType() == Material.IRON_SWORD || item.getType() == Material.GOLD_SWORD || item.getType() == Material.DIAMOND_SWORD;
    }

}
