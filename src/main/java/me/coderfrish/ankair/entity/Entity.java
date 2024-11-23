package me.coderfrish.ankair.entity;

public class Entity {
    private static int nextEntity;
    private final int entityId;

    public Entity() {
        this.entityId = nextEntity++;
    }

    public int getEntityId() {
        return entityId;
    }
}
