package com.nullbyte001.enchanter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import java.util.Random;

public class NullchantCommand implements CommandExecutor {
	private Enchantment enchants[] = {    Enchantment.SILK_TOUCH,    Enchantment.DURABILITY, Enchantment.DIG_SPEED, Enchantment.LOOT_BONUS_BLOCKS, Enchantment.DAMAGE_ALL, Enchantment.KNOCKBACK, Enchantment.FIRE_ASPECT, Enchantment.DAMAGE_ARTHROPODS, Enchantment.DAMAGE_UNDEAD, Enchantment.LOOT_BONUS_MOBS, Enchantment.ARROW_DAMAGE, Enchantment.ARROW_FIRE,
		    Enchantment.ARROW_INFINITE,
		    Enchantment.ARROW_KNOCKBACK,
		    Enchantment.PROTECTION_ENVIRONMENTAL,
		    Enchantment.PROTECTION_EXPLOSIONS,
		    Enchantment.PROTECTION_FALL,
		    Enchantment.PROTECTION_FIRE,
		    Enchantment.PROTECTION_PROJECTILE,
		    Enchantment.LURE,
		    Enchantment.LUCK,
		    Enchantment.OXYGEN,
		    Enchantment.THORNS,
		    Enchantment.WATER_WORKER };
	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		// TODO Auto-generated method stub
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can use this command!");
			return false;
		}
		Player player = (Player) sender;
		ItemStack hand = player.getInventory().getItemInMainHand();
		if(hand == null || hand.getType() == Material.AIR) {
			player.sendMessage("You need to be holding an item to nullchant it!");
			return true;
		}
		Random random = new Random();
		int enchantmentId = random.nextInt(enchants.length+5);
		int enchantmentLevel = 1+random.nextInt(10);
		Enchantment enchant = null;
		Inventory inv = player.getInventory();
		boolean found = inv.contains(Material.DIAMOND); 
        if(!found) {
        	player.sendMessage("You need at least 1 diamond to nullchant you item!");
        	return true;
        }
        ItemStack DiamondSlot = inv.getItem(inv.first(Material.DIAMOND));
        DiamondSlot.setAmount(DiamondSlot.getAmount()-1);
        
		if (enchantmentId < enchants.length)
			enchant = enchants[enchantmentId];
		if(enchant != null) {
			hand.addUnsafeEnchantment(enchant, enchantmentLevel);
			if(hand.getItemMeta().hasDisplayName()) {
				Bukkit.broadcastMessage(ChatColor.BLUE+""+player.getDisplayName()+" has nullchanted their "+hand.getItemMeta().getDisplayName()+" with "+enchant.getName()+" lv "+Integer.toString(enchantmentLevel));
				player.sendMessage(ChatColor.GREEN+"nullchanting your "+hand.getItemMeta().getDisplayName()+" with "+enchant.getName()+" lv "+Integer.toString(enchantmentLevel));
			}
			else {
				Bukkit.broadcastMessage(ChatColor.BLUE+""+player.getDisplayName()+" has nullchanted their "+hand.getType().toString()+" with "+enchant.getName()+" lv "+Integer.toString(enchantmentLevel));
				player.sendMessage(ChatColor.GREEN+"nullchanting your "+hand.getType().toString()+" with "+enchant.getName()+" lv "+Integer.toString(enchantmentLevel));
			
			}
		}
		else {
			for(Enchantment e : enchants)
				hand.removeEnchantment(e);
			if(hand.getItemMeta().hasDisplayName()) {
				Bukkit.broadcastMessage(ChatColor.DARK_PURPLE+""+player.getDisplayName()+" has failed to nullchant their "+hand.getItemMeta().getDisplayName());
				player.sendMessage(ChatColor.RED+"Oops! The nullchantment has failed and your "+hand.getItemMeta().getDisplayName()+" has been stripped of all enchantments!");
			}
			else {
				Bukkit.broadcastMessage(ChatColor.DARK_PURPLE+""+player.getDisplayName()+" has failed to nullchant their "+hand.getType().toString());
				player.sendMessage(ChatColor.RED+"Oops! The nullchantment has failed and your "+hand.getType().toString()+" has been stripped of all enchantments!");
			}
			}
		return true;
	}

}
