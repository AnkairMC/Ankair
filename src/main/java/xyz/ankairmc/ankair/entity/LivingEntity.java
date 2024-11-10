package xyz.ankairmc.ankair.entity;

import java.util.UUID;

public class LivingEntity {
    private static int nextEntity;
    private final UUID entityUuid;
    private final int entityId;

    public LivingEntity() {
        this.entityId = nextEntity++;
        this.entityUuid = UUID.randomUUID();
    }

    public int getEntityId() {
        return entityId;
    }

    public UUID getEntityUuid() {
        return entityUuid;
    }
}
