package kachow.api_invocations.client;

import java.util.UUID;

public class MonsterAcquisitionRequest {
    private final String username;
    private final UUID monsterId;

    public MonsterAcquisitionRequest(String username, UUID monsterId) {
        this.username = username;
        this.monsterId = monsterId;
    }

    public String getUsername() {
        return username;
    }

    public UUID getMonsterId() {
        return monsterId;
    }
}