package dev.ninesliced.commands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.AbstractCommand;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.WorldMapTracker;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import dev.ninesliced.BetterMapConfig;
import dev.ninesliced.exploration.ExplorationTicker;
import dev.ninesliced.exploration.ExplorationTracker;
import dev.ninesliced.exploration.WorldMapHook;
import java.util.logging.Logger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class BetterMapCommand extends AbstractCommand {
    private static final Logger LOGGER = Logger.getLogger(BetterMapCommand.class.getName());

    public BetterMapCommand() {
        super("bettermap", "Manage BetterMap plugin");
    }

    @Nullable
    @Override
    protected CompletableFuture<Void> execute(@Nonnull CommandContext context) {
        BetterMapConfig.getInstance().reload();
        context.sendMessage(Message.raw("BetterMap configuration reloaded!"));
        context.sendMessage(Message.raw("Exploration Radius: " + BetterMapConfig.getInstance().getExplorationRadius()));
        context.sendMessage(Message.raw("Map Quality: " + BetterMapConfig.getInstance().getMapQuality()));
        context.sendMessage(Message.raw("NOTE: Players must rejoin the server for map changes to take effect."));

        return CompletableFuture.completedFuture(null);
    }
}
