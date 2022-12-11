package ua.hero.archiheroes;

import ua.hero.archiheroes.items.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.hero.archiheroes.handlers.ExampleConfig;
import ua.hero.archiheroes.handlers.GenericEventHandler;
import ua.hero.archiheroes.network.packets.ExamplePacket;
import ua.hero.archiheroes.proxy.CommonProxy;

@Mod(
        modid = ArchiHeroes.MODID,
        version = ArchiHeroes.VERSION,
        name = ArchiHeroes.NAME,
        dependencies = ArchiHeroes.DEPENDENCIES
)
public class ArchiHeroes {
    public static final String MODID = "archiheroes";
    public static final String NAME = "Archi Hero";
    public static final String VERSION = "1.0.0";
    public static final String DEPENDENCIES = "required-after:lucraftcore@[1.12.2-2.4.4,)";

    public static SimpleNetworkWrapper packetHandler;

    @SidedProxy(clientSide = "ua.hero.archiheroes.proxy.ClientProxy", serverSide = "ua.hero.archiheroes.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static final Logger logger = LogManager.getLogger("ArchiHeroes");
    public static final int howCoolAmI = Integer.MAX_VALUE;

    @EventHandler
    public void load(FMLInitializationEvent event) {
        proxy.registerRenderers();
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        // NO-OP
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ExampleConfig.load(event);

        packetHandler = NetworkRegistry.INSTANCE.newSimpleChannel("ExampleModChannel");
        packetHandler.registerMessage(ExamplePacket.Handler.class, ExamplePacket.class, 1, Side.CLIENT);

        MinecraftForge.EVENT_BUS.register(new GenericEventHandler());
        NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
    }

    public static CreativeTabs tabExampleMod = new CreativeTabs("tabExampleMod") {
        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack createIcon() {
            return new ItemStack(ModItems.FIRST_ITEM);
        }
    };
}
