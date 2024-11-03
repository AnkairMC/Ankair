package xyz.ankairmc.ankair.server;

@FunctionalInterface
public interface MinecraftRunnable<T> {
    void apply(T p);
}
