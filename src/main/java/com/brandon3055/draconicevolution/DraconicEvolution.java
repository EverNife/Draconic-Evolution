package com.brandon3055.draconicevolution;

import java.util.Arrays;

import com.brandon3055.draconicevolution.common.core.utills.LogHelper;
import com.brandon3055.draconicevolution.common.items.ModItems;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import com.brandon3055.draconicevolution.client.creativetab.TolkienTab;
import com.brandon3055.draconicevolution.common.core.network.ChannelHandler;
import com.brandon3055.draconicevolution.common.core.proxy.CommonProxy;
import com.brandon3055.draconicevolution.common.lib.References;

@Mod(modid = References.MODID, name = References.MODNAME, version = References.VERSION, canBeDeactivated = false)
public class DraconicEvolution {

	@Mod.Instance(References.MODID)
	public static DraconicEvolution instance;

	@SidedProxy(clientSide = References.CLIENTPROXYLOCATION, serverSide = References.COMMONPROXYLOCATION)
	public static CommonProxy proxy;

	private static CreativeTabs tolkienTabToolsWeapons = new TolkienTab(CreativeTabs.getNextID(), References.MODID, "toolsAndWeapons");
	private static CreativeTabs tolkienTabBlocksItems = new TolkienTab(CreativeTabs.getNextID(), References.MODID, "blocksAndItems");
	public static final String networkChannelName = "DraconicEvolution";
	public static ChannelHandler channelHandler = new ChannelHandler(References.MODID, networkChannelName);
	//public static FMLEventChannel channel;
	public static boolean debug = true;
	
	public DraconicEvolution()
	{
		//logger.info("This is Draconic Evolution");
	}
	
	@Mod.EventHandler
	public static void preInit(final FMLPreInitializationEvent event)
	{if(debug)
		LogHelper.info("Initialization");

		event.getModMetadata().autogenerated = false;
		event.getModMetadata().credits = "";
		event.getModMetadata().description = "This is a mod originally made for the Tolkiencraft mod pack";
		event.getModMetadata().authorList = Arrays.asList("brandon3055");
		event.getModMetadata().logoFile = "banner.png";
		event.getModMetadata().url = "http://dragontalk.net/draconic_evolution";
		event.getModMetadata().version = References.VERSION + "-MC1.7.10";

		proxy.preInit(event);

		/*
		public static Achievement ultimatePower;
		ultimatePower = new Achievement("achievment.ultimatePower", "Ultimate Power!!!", 1, -2, ModItems.draconicDistructionStaff, null).registerStat();
		AchievementPage draconicEvolution = new AchievementPage("Draconic Evolution", ultimatePower);
		AchievementPage.registerAchievementPage(draconicEvolution);
		*/

	}

	@Mod.EventHandler
	public void init(final FMLInitializationEvent event)
	{if(debug)
		System.out.println("init()");
		
		proxy.init(event);
	}

	@Mod.EventHandler
	public void postInit(final FMLPostInitializationEvent event)
	{if(debug)
		System.out.println("postInit()");
	
		proxy.postInit(event);
		
	}

	public static CreativeTabs getCreativeTab(int tab)
	{
		return tab == 1 ? tolkienTabToolsWeapons : tolkienTabBlocksItems;
	}
}