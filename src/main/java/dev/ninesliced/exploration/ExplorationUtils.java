package dev.ninesliced.exploration;

import com.hypixel.hytale.server.core.entity.entities.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.logging.Logger;

public class ExplorationUtils {

    private static final Logger LOGGER = Logger.getLogger(ExplorationUtils.class.getName());

    @Nullable
    public static ExplorationTracker.PlayerExplorationData getPlayerExplorationData(@Nonnull Player player) {
        return ExplorationTracker.getInstance().getPlayerData(player);
    }

    public static int getExploredChunkCount(@Nonnull Player player) {
        ExplorationTracker.PlayerExplorationData data = getPlayerExplorationData(player);
        if (data == null) {
            return 0;
        }
        return data.getExploredChunks().getExploredCount();
    }

    @Nullable
    public static MapExpansionManager.MapBoundaries getExplorationBoundaries(@Nonnull Player player) {
        ExplorationTracker.PlayerExplorationData data = getPlayerExplorationData(player);
        if (data == null) {
            return null;
        }
        return data.getMapExpansion().getCurrentBoundaries();
    }

    public static long getTotalExploredArea(@Nonnull Player player) {
        ExplorationTracker.PlayerExplorationData data = getPlayerExplorationData(player);
        if (data == null) {
            return 0;
        }
        return data.getMapExpansion().getTotalExploredArea();
    }

    public static void resetExploration(@Nonnull Player player) {
        ExplorationTracker.PlayerExplorationData data = getPlayerExplorationData(player);
        if (data != null) {
            data.getMapExpansion().reset();
            LOGGER.info("Reset exploration for player: " + player.getDisplayName());
        }
    }

    @Nonnull
    public static String getExplorationStats(@Nonnull Player player) {
        ExplorationTracker.PlayerExplorationData data = getPlayerExplorationData(player);
        if (data == null) {
            return "No exploration data available";
        }

        int chunkCount = data.getExploredChunks().getExploredCount();
        long totalArea = data.getMapExpansion().getTotalExploredArea();
        MapExpansionManager.MapBoundaries boundaries = data.getMapExpansion().getCurrentBoundaries();

        return String.format(
                "Exploration Stats for %s:%n" +
                        "  Chunks Explored: %d%n" +
                        "  Total Map Area: %d chunks (%.1f blocks)%n" +
                        "  Boundaries: %s",
                player.getDisplayName(),
                chunkCount,
                totalArea,
                totalArea * 256.0,
                boundaries
        );
    }

    public static boolean isChunkExplored(@Nonnull Player player, int chunkX, int chunkZ) {
        ExplorationTracker.PlayerExplorationData data = getPlayerExplorationData(player);
        if (data == null) {
            return false;
        }
        long chunkIndex = ChunkUtil.chunkCoordsToIndex(chunkX, chunkZ);
        return data.getExploredChunks().isChunkExplored(chunkIndex);
    }

    @Nonnull
    public static String formatBoundaries(@Nullable MapExpansionManager.MapBoundaries boundaries) {
        if (boundaries == null) {
            return "No boundaries";
        }
        return String.format("X: [%d to %d] Z: [%d to %d] (Size: %dx%d)",
                boundaries.minX, boundaries.maxX,
                boundaries.minZ, boundaries.maxZ,
                boundaries.getWidth(), boundaries.getHeight());
    }
}
