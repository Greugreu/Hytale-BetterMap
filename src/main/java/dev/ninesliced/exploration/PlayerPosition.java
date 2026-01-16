package dev.ninesliced.exploration;

import com.hypixel.hytale.math.vector.Vector3d;

import javax.annotation.Nonnull;

public class PlayerPosition {
    private final double x;
    private final double y;
    private final double z;

    public PlayerPosition(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Nonnull
    public static PlayerPosition fromVector(@Nonnull Vector3d vector) {
        return new PlayerPosition(vector.x, vector.y, vector.z);
    }

    public int getChunkX() {
        return ChunkUtil.blockToChunkCoord(x);
    }

    public int getChunkZ() {
        return ChunkUtil.blockToChunkCoord(z);
    }

    public double distanceTo(@Nonnull PlayerPosition other) {
        double dx = x - other.x;
        double dy = y - other.y;
        double dz = z - other.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public double chunkDistanceTo(@Nonnull PlayerPosition other) {
        int dx = getChunkX() - other.getChunkX();
        int dz = getChunkZ() - other.getChunkZ();
        return Math.sqrt(dx * dx + dz * dz);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public String toString() {
        return String.format("PlayerPosition{%.1f, %.1f, %.1f} [Chunk: %d, %d]",
                x, y, z, getChunkX(), getChunkZ());
    }
}
