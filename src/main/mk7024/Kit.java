package main.mk7024;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Kit {
    public static void setGameItem(Player player){
        player.getInventory().clear();
        ItemStack[] inventory = new ItemStack[36];
        ItemStack[] backpack = new ItemStack[4];
        inventory[0] = new ItemStack(Material.DIAMOND_SWORD);
        inventory[1] = new ItemStack(Material.FISHING_ROD);
        inventory[2] = new ItemStack(Material.BOW);
        for(int i = 3; i<=33;i++){
            if(i == 7 || i == 8 || i==17 || i==16 || i==26||i==25||i==14){
                i++;
            }
            inventory[i] = new ItemStack(Material.SPLASH_POTION);
            PotionMeta meta = (PotionMeta) inventory[i].getItemMeta();
            meta.addCustomEffect(new PotionEffect(PotionEffectType.HEAL,0,1),true);
            inventory[i].setItemMeta(meta);
        }
        inventory[7] = new ItemStack(Material.ENDER_PEARL);
        inventory[8] = new ItemStack(Material.GOLDEN_APPLE);
        inventory[17] = new ItemStack(Material.GOLDEN_APPLE);
        inventory[16] = new ItemStack(Material.ARROW,15);
        inventory[26] = new ItemStack(Material.GOLDEN_APPLE);
        inventory[25] = new ItemStack(Material.ENDER_PEARL);
        inventory[14] = new ItemStack(Material.ENDER_PEARL);
        inventory[35] = new ItemStack(Material.GOLDEN_APPLE);
        inventory[34] = new ItemStack(Material.ENDER_PEARL);
        backpack[0] = new ItemStack(Material.IRON_BOOTS);
        backpack[1] = new ItemStack(Material.IRON_LEGGINGS);
        backpack[2] = new ItemStack(Material.IRON_CHESTPLATE);
        backpack[3] = new ItemStack(Material.IRON_HELMET);
        for(int i = 0;i<=3;i++){
            ItemMeta meta = backpack[i].getItemMeta();
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1,true);
            backpack[i].setItemMeta(meta);
        }
        player.getInventory().setContents(inventory);
        player.getInventory().setArmorContents(backpack);
        player.updateInventory();
    }

    public static void setLobbyItem(Player player){
        player.getInventory().clear();

        player.updateInventory();
    }
}
