package me.coderfrish.ankair.util;

import me.coderfrish.ankair.core.BlockPosition;

public class PositionUtil {
    private static final int NUM_X_BITS = 1 + MathUtil.log2(MathUtil.smallestEncompassingPowerOfTwo(30000000));
    private static final int NUM_Z_BITS = NUM_X_BITS;
    private static final int NUM_Y_BITS = 64 - NUM_X_BITS - NUM_Z_BITS;
    private static final int Y_SHIFT = 0 + NUM_Z_BITS;
    private static final int X_SHIFT = Y_SHIFT + NUM_Y_BITS;

    public static BlockPosition of(long serialized) {
        int x = (int)(serialized << 64 - X_SHIFT - NUM_X_BITS >> 64 - NUM_X_BITS);
        int y = (int)(serialized << 64 - Y_SHIFT - NUM_Y_BITS >> 64 - NUM_Y_BITS);
        int z = (int)(serialized << 64 - NUM_Z_BITS >> 64 - NUM_Z_BITS);

        return new BlockPosition(x, y, z);
    }

    private static final long X_MASK = (1L << NUM_X_BITS) - 1L;
    private static final long Y_MASK = (1L << NUM_Y_BITS) - 1L;
    private static final long Z_MASK = (1L << NUM_Z_BITS) - 1L;

    public static long to(BlockPosition position) {
        return ((long) position.getX() & X_MASK) << X_SHIFT | ((long) position.getY() & Y_MASK) << Y_SHIFT | ((long) position.getZ() & Z_MASK) << 0;
    }
}
