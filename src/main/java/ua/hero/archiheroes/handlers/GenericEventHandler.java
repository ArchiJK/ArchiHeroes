package ua.hero.archiheroes.handlers;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import ua.hero.archiheroes.ArchiHeroes;
import ua.hero.archiheroes.network.packets.ExamplePacket;

public class GenericEventHandler {
    private int messageCountdown = 10;

    @SubscribeEvent
    public void onEntityJoinWorld(PlayerEvent.PlayerLoggedInEvent event) {
        Entity entity = event.player;

        if (!entity.world.isRemote && entity instanceof EntityPlayerMP) {
            System.out.println("Entity joined world: " + entity);
        }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.player instanceof EntityPlayerMP && this.messageCountdown != 0) {
            this.messageCountdown--;

            if (this.messageCountdown == 0) {
                ArchiHeroes.packetHandler.sendTo(new ExamplePacket(), (EntityPlayerMP)event.player);
            }
        }
    }

}
