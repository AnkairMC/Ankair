package xyz.ankairmc.ankair.entity;

import xyz.ankairmc.ankair.util.MathUtil;

import java.util.Random;
import java.util.UUID;

public class Entity {
    private static int nextEntity;
    private final UUID entityUuid;
    private final int entityId;

    public Entity() {
        this.entityId = nextEntity++;
        this.entityUuid = MathUtil.randomUUID(new Random());
    }

    public int getEntityId() {
        return entityId;
    }

    public UUID getEntityUuid() {
        return entityUuid;
    }
}
