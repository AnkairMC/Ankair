package xyz.ankairmc.ankair.util;

import java.util.Random;
import java.util.UUID;

public class MathUtil {
    public static UUID randomUUID(Random random) {
        long i = random.nextLong() & -61441L | 16384L;
        long j = random.nextLong() & 4611686018427387903L | Long.MIN_VALUE;
        return new UUID(i, j);
    }
}
