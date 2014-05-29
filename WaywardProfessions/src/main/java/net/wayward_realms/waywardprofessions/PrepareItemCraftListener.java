package net.wayward_realms.waywardprofessions;

import net.wayward_realms.waywardlib.character.CharacterPlugin;
import net.wayward_realms.waywardlib.professions.ToolType;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.Random;

public class PrepareItemCraftListener implements Listener {

    private WaywardProfessions plugin;

    public PrepareItemCraftListener(WaywardProfessions plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPrepareItemCraft(PrepareItemCraftEvent event) {
        if (event.getViewers().size() > 0 && event.getViewers().get(0).getGameMode() != GameMode.CREATIVE) {
            if (plugin.canGainCraftEfficiency(event.getRecipe().getResult().getType())) return;
            if (ToolType.getToolType(event.getInventory().getResult().getType()) != null) {
                ToolType type = ToolType.getToolType(event.getInventory().getResult().getType());
                RegisteredServiceProvider<CharacterPlugin> characterPluginProvider = Bukkit.getServer().getServicesManager().getRegistration(CharacterPlugin.class);
                if (characterPluginProvider != null) {
                    CharacterPlugin characterPlugin = characterPluginProvider.getProvider();
                    net.wayward_realms.waywardlib.character.Character character = characterPlugin.getActiveCharacter((Player) event.getViewers().get(0));
                    event.getInventory().getResult().setDurability((short) (event.getInventory().getResult().getType().getMaxDurability() - (0.75D * (double) plugin.getMaxToolDurability(character, type))));
                    //Random random = new Random();
                    //plugin.setMaxToolDurability(character, type, Math.min(plugin.getMaxToolDurability(character, type) + (random.nextInt(100) <= 30 ? random.nextInt(3) : 0), event.getInventory().getResult().getType().getMaxDurability()));
                }
            }
            RegisteredServiceProvider<CharacterPlugin> characterPluginProvider = Bukkit.getServer().getServicesManager().getRegistration(CharacterPlugin.class);
            if (characterPluginProvider != null) {
                CharacterPlugin characterPlugin = characterPluginProvider.getProvider();
                net.wayward_realms.waywardlib.character.Character character = characterPlugin.getActiveCharacter((Player) event.getViewers().get(0));
                //Material material = event.getInventory().getResult().getType();
                Random random = new Random();
                int craftEfficiency = plugin.getCraftEfficiency(character, event.getInventory().getResult().getType());
                int amount = (int) ((double) (random.nextInt(100) <= 30 ? (craftEfficiency > 10 ? random.nextInt(craftEfficiency) : random.nextInt(10)) : 25) * (4D / 100D) * (double) event.getInventory().getResult().getAmount());
                event.getInventory().getResult().setAmount(amount);
                //plugin.setCraftEfficiency(character, material, plugin.getCraftEfficiency(character, material) + (random.nextInt(100) <= 30 ? random.nextInt(3) : 0));
            }
        }
    }

}
