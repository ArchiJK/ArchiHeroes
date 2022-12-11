package ua.hero.archiheroes.items;

import ua.hero.archiheroes.ArchiHeroes;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = ArchiHeroes.MODID)
public class RegistrationHandler {

    @SubscribeEvent
    public static void registerItems(Register<Item> event) {
        final Item[] items = {
                new Item().setRegistryName(ArchiHeroes.MODID, "first_item").setTranslationKey(ArchiHeroes.MODID + "." + "first_item").setCreativeTab(CreativeTabs.MISC)
        };

        event.getRegistry().registerAll(items);
    }

}