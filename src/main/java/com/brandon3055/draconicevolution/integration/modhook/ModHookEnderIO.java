package com.brandon3055.draconicevolution.integration.modhook;

import com.enderio.core.common.enchant.EnchantAutoSmelt;
import cpw.mods.fml.common.Loader;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.MathHelper;

import java.util.List;
import java.util.Map;

public class ModHookEnderIO {

   private static Boolean isPresent = null;
   private static boolean isPresent(){
      return isPresent != null
              ? isPresent
              : (isPresent = Loader.isModLoaded("EnderIO"));
   }


   public static void handleEnderIoSmelting(EntityPlayer player, ItemStack toolStack, List<ItemStack> drops, int fortune){
      if (!isPresent()){
         return;
      }

      if (toolStack == null){
         return;
      }

      boolean isSilkTouching = EnchantmentHelper.getEnchantmentLevel(Enchantment.silkTouch.effectId, toolStack) > 0;

      if (isSilkTouching){
         return;
      }

      Map<Integer, Integer> enchants = EnchantmentHelper.getEnchantments(toolStack);
      int level = -1;
      for (int i : enchants.keySet()) {
         Enchantment enchant = Enchantment.enchantmentsList[i];
         if (enchant == EnchantAutoSmelt.INSTANCE) {
            level = enchants.get(i);
            break;
         }
      }
      if (level >= 0) {
         for (int i = 0; i < drops.size(); i++) {
            {
               ItemStack stack = drops.get(i);
               if (stack != null) {
                  if (FurnaceRecipes.smelting().getSmeltingResult(stack) != null) {
                     ItemStack furnaceStack = FurnaceRecipes.smelting().getSmeltingResult(stack).copy();
                     //Fortune stuffs
                     if (fortune > 0 && com.enderio.core.common.config.ConfigHandler.allowAutoSmeltWithFortune)
                        furnaceStack.stackSize *= (player.worldObj.rand.nextInt(fortune + 1) + 1);

                     drops.set(i, furnaceStack);

                     //XP (adapted vanilla code)
                     int xp = furnaceStack.stackSize;
                     float f = FurnaceRecipes.smelting().func_151398_b(furnaceStack);
                     int j;

                     if (f == 0.0F) {
                        xp = 0;
                     } else if (f < 1.0F) {
                        j = MathHelper.floor_float((float) xp * f);

                        if (j < MathHelper.ceiling_float_int((float) xp * f) && (float) Math.random() < (float) xp * f - (float) j) {
                           ++j;
                        }

                        xp = j;
                     }

                     while (xp > 0) {
                        j = EntityXPOrb.getXPSplit(xp);
                        xp -= j;
                        player.worldObj.spawnEntityInWorld(new EntityXPOrb(player.worldObj, player.posX, player.posY + 0.5, player.posZ, j));
                     }
                  }
               }
            }
         }
      }

   }


}
