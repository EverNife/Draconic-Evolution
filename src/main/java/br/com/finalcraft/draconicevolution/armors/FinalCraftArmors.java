package br.com.finalcraft.draconicevolution.armors;


import com.brandon3055.draconicevolution.common.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class FinalCraftArmors {

    // Armor nanoUmArmor                == 96                   |/\ 1.2
    // Armor neutroniumt1Armor          == 144                  |/\ 1.4
    // Armor spectralArmor              == 180                  |/\ 1.8
    // Armor zeusArmor                  == 228                  |/\ 2.0
    // Armor neutroniumt2Armor          == 252 --> 325          |/\ 2.3 -->2,5


    private static int protectionNeutroniumt2Armor = 325 / 6;
    private static double defenceRateNeutroniumt2Armor = 2.5 / 6;

    public static void manageArmorProtection(LivingHurtEvent event, EntityPlayer player){

        boolean helmet = false;
        boolean chest = false;
        boolean legs = false;
        boolean boots = false;

        int protection = 0; //protection que sera retirada do dano base
        double defenceRate = 0; //defenceRate é um numero pelo qual o restante do dano será dividido
        int armorParts = 0; //armorParts é o contador que permitirá saber qual armadura o jogador esta vestindo

        if (player.inventory.armorInventory[3] != null)            helmet = true;
        if (player.inventory.armorInventory[2] != null)            chest = true;
        if (player.inventory.armorInventory[1] != null)            legs = true;
        if (player.inventory.armorInventory[0] != null)            boots = true;


        if (helmet){
            if (player.inventory.armorInventory[3].getItem() == ModItems.neutroniumT2Helm){
                protection += protectionNeutroniumt2Armor;
                defenceRate += defenceRateNeutroniumt2Armor;
            }
        }
        if (chest){
            if (player.inventory.armorInventory[2].getItem() == ModItems.neutroniumT2Chest){
                protection += protectionNeutroniumt2Armor * 2;
                defenceRate += defenceRateNeutroniumt2Armor * 2;
            }
        }
        if (legs){
            if (player.inventory.armorInventory[1].getItem() == ModItems.neutroniumT2Leggs){
                protection += protectionNeutroniumt2Armor * 2;
                defenceRate += defenceRateNeutroniumt2Armor * 2;
            }
        }
        if (boots){
            if (player.inventory.armorInventory[0].getItem() == ModItems.neutroniumT2Boots){
                protection += protectionNeutroniumt2Armor;
                defenceRate += defenceRateNeutroniumt2Armor;
            }
        }

        double dmg = event.ammount;
        if (protection != 0){
            dmg = dmg - protection;
            if (dmg > 0){
                if (defenceRate > 1.0){
                    dmg = dmg / defenceRate;
                }
            }
            else {
                dmg = 0;
            }
            event.ammount = (float) dmg;
        }
    }

    /*

    public static void onHurtArmors(LivingHurtEvent event){
        if (event.isCanceled()) {
            return;
        }

        float protection = 0; //protection que sera retirada do dano base
        double defenceHate = 0; //defenceHate é um numero pelo qual o restante do dano será dividido
        int armorParts = 0; //armorParts é o contador que permitirá saber qual armadura o jogador esta vestindo

        EntityPlayer player = (EntityPlayer) event.entityLiving;

        //Checando os capacetes:
        if (player.inventory.armorInventory[3] != null){
            String helmetName = player.inventory.armorInventory[3].getUnlocalizedName();

            if ( helmetName.equalsIgnoreCase("item.amor_nano1Helm") ){
                protection = protection + 23;
                defenceHate = defenceHate + 1.2;
            }
            else if( helmetName.equalsIgnoreCase("item.armor_neutroniumHelm") ){
                protection = protection + 36;
                defenceHate = defenceHate + 1.75;
            }
            else if ( helmetName.equalsIgnoreCase("item.armor_spectrumHelm") ){
                protection = protection + 41;
                defenceHate = defenceHate + 2.0;
                armorParts = armorParts + 10;
            }
            else if ( helmetName.equalsIgnoreCase("item.amor_zeusHelm") ){
                protection = protection + 58;
                defenceHate = defenceHate + 2.25;
                armorParts = armorParts + 50;
            }
            else if ( helmetName.equalsIgnoreCase("item.armor_neutroniumtier2Helm") ){
                protection = protection + 71;
                defenceHate = defenceHate + 3.5;
            }
            else if ( helmetName.equalsIgnoreCase("item.armor_dragonscaleHelm") ){
                protection = protection + 95;
                defenceHate = defenceHate + 5.5;
            }
        }
        //Checando os peitorais:
        if (player.inventory.armorInventory[2] != null){
            String chestName = player.inventory.armorInventory[2].getUnlocalizedName();

            if ( chestName.equalsIgnoreCase("item.amor_nano1Chest") ){
                protection = protection + 23;
                defenceHate = defenceHate + 1.2;
            }
            else if( chestName.equalsIgnoreCase("item.armor_neutroniumChest") ){
                protection = protection + 36;
                defenceHate = defenceHate + 1.75;
            }
            else if ( chestName.equalsIgnoreCase("item.armor_spectrumChest") ){
                protection = protection + 41;
                defenceHate = defenceHate + 2.0;
                armorParts = armorParts + 10;
            }
            else if ( chestName.equalsIgnoreCase("item.amor_zeusChest") ){
                protection = protection + 58;
                defenceHate = defenceHate + 2.25;
                armorParts = armorParts + 50;
            }
            else if ( chestName.equalsIgnoreCase("item.armor_neutroniumtier2Chest") ){
                protection = protection + 71;
                defenceHate = defenceHate + 3.5;
            }
            else if ( chestName.equalsIgnoreCase("item.armor_dragonscaleChest") ){
                protection = protection + 95;
                defenceHate = defenceHate + 5.5;
            }
        }

        //Checando os Calças:
        if (player.inventory.armorInventory[1] != null){
            String legginsName = player.inventory.armorInventory[1].getUnlocalizedName();

            if ( legginsName.equalsIgnoreCase("item.amor_nano1Legs") ){
                protection = protection + 23;
                defenceHate = defenceHate + 1.2;
            }
            else if( legginsName.equalsIgnoreCase("item.armor_neutroniumLegs") ){
                protection = protection + 36;
                defenceHate = defenceHate + 1.75;
            }
            else if ( legginsName.equalsIgnoreCase("item.armor_spectrumLegs") ){
                protection = protection + 41;
                defenceHate = defenceHate + 2.0;
                armorParts = armorParts + 10;
            }
            else if ( legginsName.equalsIgnoreCase("item.amor_zeusLegs") ){
                protection = protection + 58;
                defenceHate = defenceHate + 2.25;
                armorParts = armorParts + 50;
            }
            else if ( legginsName.equalsIgnoreCase("item.armor_neutroniumtier2Legs") ){
                protection = protection + 71;
                defenceHate = defenceHate + 3.5;
            }
            else if ( legginsName.equalsIgnoreCase("item.armor_dragonscaleLegs") ){
                protection = protection + 95;
                defenceHate = defenceHate + 5.5;
            }
        }

        //Checando as botas:
        if (player.inventory.armorInventory[0] != null){
            String bootsName = player.inventory.armorInventory[0].getUnlocalizedName();

            if ( bootsName.equalsIgnoreCase("item.amor_nano1Boots") ){
                protection = protection + 23;
                defenceHate = defenceHate + 1.2;
            }
            else if( bootsName.equalsIgnoreCase("item.armor_neutroniumBoots") ){
                protection = protection + 36;
                defenceHate = defenceHate + 1.75;
            }
            else if ( bootsName.equalsIgnoreCase("item.armor_spectrumBoots") ){
                protection = protection + 41;
                defenceHate = defenceHate + 2.0;
                if (armorParts == 30){
                    ItemStack chestplate = player.inventory.armorInventory[2];
                    NBTTagCompound nbt;

                    //player.addChatComponentMessage(new ChatComponentText("§b§lArmor of Spectrum: Ativada"));

                    //checando se o item possui NBT, senão cria uma nova!
                    if (chestplate.hasTagCompound())
                    {
                        nbt = chestplate.getTagCompound();
                    }
                    else
                    {
                        nbt = new NBTTagCompound();
                    }
                    //Verifica se na NBT tem o campo, "SoulCharge"
                    if (nbt.hasKey("SoulCharge"))
                    {
                        //Se sim, atualiza ele somando 1
                        Integer charge = nbt.getInteger("SoulCharge");
                        //player.addChatComponentMessage(new ChatComponentText("Charge: " + charge));
                        charge++;
                        if (charge <= 30){
                            nbt.setInteger("SoulCharge", charge);
                        }
                    }
                    else
                    {
                        //Se não cria o campo com uma carga
                        nbt.setInteger("SoulCharge", 1);
                    }

                    chestplate.setTagCompound(nbt);
                }
            }
            else if ( bootsName.equalsIgnoreCase("item.amor_zeusBoots") ){
                protection = protection + 58;
                defenceHate = defenceHate + 2.25;
            }
            else if ( bootsName.equalsIgnoreCase("item.armor_neutroniumtier2Boots") ){
                protection = protection + 71;
                defenceHate = defenceHate + 3.5;
            }
            else if ( bootsName.equalsIgnoreCase("item.armor_dragonscaleBoots") ){
                protection = protection + 95;
                defenceHate = defenceHate + 5.5;
            }
        }





        double dmg = event.ammount;
        //player.addChatComponentMessage(new ChatComponentText("Dano Recebido: " + dmg + " - " + protection + " = " + (dmg - protection)));
        if (protection != 0){
            dmg = dmg - protection;
            if (dmg > 0){
                dmg = dmg / defenceHate;
            }
            else {
                dmg = 0;
            }
            event.ammount = (float) dmg;
        }
        //player.addChatComponentMessage(new ChatComponentText("Dividido pela ratio " + defenceHate + " = " + dmg));


    //    player.addChatComponentMessage(new ChatComponentText("PlayerHelmet: " + player.inventory.armorInventory[2].getUnlocalizedName()));
    //    player.addChatMessage(new ChatComponentTranslation("msg.finalcraft.onhurt.txt"));

    }

    */

}
