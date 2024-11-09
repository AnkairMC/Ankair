package xyz.ankairmc.ankair.core;

public record Identifier(String namespace, String path) {
    public Identifier(String path) {
        this("minecraft", path);
    }

    @Override
    public String toString() {
        return namespace + ":" + path;
    }
}
