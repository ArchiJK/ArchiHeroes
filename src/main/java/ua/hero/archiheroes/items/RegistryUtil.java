package ua.hero.archiheroes.items;

import ua.hero.archiheroes.ArchiHeroes;
import net.minecraft.item.Item;

public class RegistryUtil {

    public static Item setItemName(final Item item, final String name) {
        item.setRegistryName(ArchiHeroes.MODID, name).setTranslationKey(ArchiHeroes.MODID + "." + name);
        return item;
    }
}
