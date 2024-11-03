package xyz.ankairmc.ankair.core;

public record Position(int x, int y, int z) {
    public long toLong() {
        return (((long) x & (long) 67108863) << 38) | (((long) y & (long) 4095)) | (((long) z & (long) 67108863) << 12);
    }

    public static Position toPosition(long value) {
        return new Position((int) (value >> 38), (int) ((value << 52) >> 52), (int) ((value << 26) >> 38));
    }
}
