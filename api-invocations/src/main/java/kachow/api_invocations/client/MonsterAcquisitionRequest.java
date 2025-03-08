package kachow.api_invocations.client;

import java.util.UUID;

public class MonsterAcquisitionRequest {
    private final String username;
    private final String monsterId;

    public MonsterAcquisitionRequest(String username, String monsterId) {
        this.username = username;
        this.monsterId = monsterId;
    }

    public String getUsername() {
        return username;
    }

    public String getMonsterId() {
        return monsterId;
    }
}