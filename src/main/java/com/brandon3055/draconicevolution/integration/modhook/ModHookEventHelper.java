package com.brandon3055.draconicevolution.integration.modhook;

import com.brandon3055.draconicevolution.common.utills.LogHelper;
import com.gamerforea.eventhelper.util.EventUtils;
import cpw.mods.fml.common.Loader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nonnull;

public class ModHookEventHelper {

   private static Boolean isPresent = null;
   private static boolean isPresent(){
      if (isPresent == null && (isPresent = Loader.isModLoaded("EventHelper"))){
         LogHelper.info("[EventHelper DirectInjection] - EventHelper has been enabled on DraconicEvolution!");
      }
      return isPresent;
   }

   public static boolean cantBreak(@Nonnull EntityPlayer player, double x, double y, double z){
      if (isPresent()){
         return EventUtils.cantBreak(player,x,y,z);
      }
      return false;
   }

   public static boolean cantAttack(Entity attacker, Entity victim){
      if (isPresent()){
         return EventUtils.cantDamage(attacker,victim);
      }
      return false;
   }


}
