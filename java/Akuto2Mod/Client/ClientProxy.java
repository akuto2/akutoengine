package Akuto2Mod.Client;

import static lib.utils.ClientRegistry.*;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import Akuto2Mod.Akuto2Core;
import Akuto2Mod.CommonProxy;
import Akuto2Mod.Items.ManualInfo;
import Akuto2Mod.Renderer.RenderFillerEX;
import Akuto2Mod.Renderer.RenderTankEX;
import Akuto2Mod.TileEntity.TileFillerEX;
import Akuto2Mod.TileEntity.TileTankEX;
import Akuto2Mod.TileEntity.Engine.TileAutoEngineCore;
import buildcraft.core.lib.engines.RenderEngine;
import buildcraft.core.lib.engines.TileEngineBase;
import buildcraft.core.render.RenderingEntityBlocks;
import buildcraft.core.render.RenderingEntityBlocks.EntityRenderIndex;
import buildcraft.factory.render.RenderTank;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import lib.utils.XMLReader;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy{
	private static ResourceLocation chamberResourceLocation = new ResourceLocation("buildcraftenergy:textures/blocks/engineStone/chamber.png");
	public static Document filler;
	public static ManualInfo manualInfo;

	public void registerRenderInformation(){}

	@Override
	public void initialize() {
		readManuals();
		initManualRecipes();
	}

	public void readManuals() {
		DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
		String currentLanguage = Minecraft.getMinecraft().getLanguageManager().getCurrentLanguage().getLanguageCode();

		Document filler_cl = XMLReader.readManual(Akuto2Core.class, "/assets/akutoengine/manuals/" + currentLanguage + "/filler.xml");

		filler = filler_cl != null ? filler_cl : XMLReader.readManual(Akuto2Core.class, "/assets/akutoengine/manuals/en_US/filler.xml");
		manualInfo = new ManualInfo();
	}

	private void initManualRecipes() {
		ItemStack brick = new ItemStack(Blocks.brick_block);
		ItemStack glass = new ItemStack(Blocks.glass);
		ItemStack water = new ItemStack(Items.water_bucket);
		registerManualCraftingRecipe("fillall", new ItemStack(Akuto2Core.fillerModule, 1, 0), brick, brick, brick, brick, brick, brick, brick, brick, brick);
		registerManualCraftingRecipe("erase", new ItemStack(Akuto2Core.fillerModule, 1, 1), glass, glass, glass, glass, null, glass, glass, glass, glass);
		registerManualCraftingRecipe("removeUp", new ItemStack(Akuto2Core.fillerModule, 1, 2), glass, glass, glass, glass, glass, glass, glass, glass, glass);
		registerManualCraftingRecipe("removeDown", new ItemStack(Akuto2Core.fillerModule, 1, 3), null, null, null, glass, glass, glass, glass, glass, glass);
		registerManualCraftingRecipe("flattener", new ItemStack(Akuto2Core.fillerModule, 1, 4), null, null, null, glass, glass, glass, brick, brick, brick);
		registerManualCraftingRecipe("holefill", new ItemStack(Akuto2Core.fillerModule, 1, 5), null, null, null, null, null, null, brick, brick, brick);
		registerManualCraftingRecipe("underfill", new ItemStack(Akuto2Core.fillerModule, 1, 6), null, null, null, brick, brick, brick, brick, brick, brick);
		registerManualCraftingRecipe("fillbox", new ItemStack(Akuto2Core.fillerModule, 1, 7), brick, brick, brick, brick, null, brick, brick, brick, brick);
		registerManualCraftingRecipe("fillwall", new ItemStack(Akuto2Core.fillerModule, 1, 8), brick, null, brick, brick, null, brick, brick, null, brick);
		registerManualCraftingRecipe("flooring", new ItemStack(Akuto2Core.fillerModule, 1, 9), null, null, null, brick, brick, brick, glass, glass, glass);
		registerManualCraftingRecipe("filltorch", new ItemStack(Akuto2Core.fillerModule, 1, 10), brick, null, brick, null, brick, null, brick, null, brick);
		registerManualCraftingRecipe("tower", new ItemStack(Akuto2Core.fillerModule, 1, 11), null, brick, brick, null, brick, brick, null, brick, brick);
		registerManualCraftingRecipe("clearliquid", new ItemStack(Akuto2Core.fillerModule, 1, 12), null, null, null, glass, glass, glass, water, water, water);
		registerManualCraftingRecipe("fillhoe", new ItemStack(Akuto2Core.fillerModule, 1, 13), brick, brick, null, null, brick, null, null, brick, null);
		registerManualCraftingRecipe("quarry", new ItemStack(Akuto2Core.fillerModule, 1, 14), glass, null, glass, glass, null, glass, brick, brick, brick);
	}

	@Override
	public void registerTileEntitySpecialRenderer() {
		 ClientRegistry.bindTileEntitySpecialRenderer(TileAutoEngineCore.class, new RenderEngine());
		 ClientRegistry.bindTileEntitySpecialRenderer(TileTankEX.class, new RenderTank());
		 ClientRegistry.bindTileEntitySpecialRenderer(TileFillerEX.class, new RenderFillerEX());
         for(int i = 0; i < 9; ++i) {
            RenderingEntityBlocks.blockByEntityRenders.put(new EntityRenderIndex(Akuto2Core.engineBlock, i), new RenderEngine((ResourceLocation)Akuto2Core.RESOURCE_LOCATION_LIST.get(i), chamberResourceLocation, TileEngineBase.TRUNK_RED_TEXTURE));
         }
         MinecraftForgeClient.registerItemRenderer(GameRegistry.findItem("AkutoEngine", "TankEX"), new RenderTankEX());
	}

	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}
}