package akuto2;

import java.util.logging.Logger;

import akuto2.gui.GuiHandler;
import akuto2.proxies.CommonProxy;
import akuto2.utils.CreativeTabAkutoEngine;
import lib.utils.UpdateChecker;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.Metadata;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = "akutoengine", name = "AkutoEngine", version = "2.0.0")
public class AkutoEngine {
	@Instance("akutoengine")
	public static AkutoEngine instance;
	@Metadata("akutoengine")
	public static ModMetadata meta;
	@SidedProxy(clientSide = "akuto2.proxies.ClientProxy", serverSide = "akuto2.proxies.CommonProxy")
	public static CommonProxy proxy;

	public static UpdateChecker update = null;
	public static final CreativeTabs tabs = new CreativeTabAkutoEngine("AkutoEngine");

	public static Logger logger = Logger.getLogger("AkutoEngine");

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(AkutoEngine.instance, new GuiHandler());
	}

	@EventHandler
	public void Init(FMLInitializationEvent event) {
		proxy.registerTileEntitySpecialRenderer();
		ObjHandler.registerTileEntity();
	}
}
