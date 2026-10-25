package net.kapitencraft.tutorial.event;

import net.kapitencraft.tutorial.commands.OpenScreenCommand;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ClientEvents {

    @SubscribeEvent
    public static void registerClientCommands(RegisterClientCommandsEvent event) {
        OpenScreenCommand.register(event.getDispatcher());
    }
}
