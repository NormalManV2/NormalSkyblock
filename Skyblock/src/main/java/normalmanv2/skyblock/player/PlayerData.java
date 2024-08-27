package normalmanv2.skyblock.player;

import normalmanv2.skyblock.island.IslandImpl;
import normalmanv2.skyblock.role.Role;
import org.bukkit.Location;

import java.util.UUID;

public class PlayerData {

    private final UUID playerId;
    private Role islandRole;
    private IslandImpl islandImpl;

    public PlayerData(UUID playerId){
        this.playerId = playerId;
    }

    public void setIslandRole(Role islandRole) {
        this.islandRole = islandRole;
    }

    public void setIsland(IslandImpl islandImpl) {
        this.islandImpl = islandImpl;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public Location getIslandHome() {
        return islandImpl != null ? islandImpl.getIslandHome() : null;
    }

    public Role getIslandRole() {
        return islandRole;
    }

    public IslandImpl getIsland() {
        return islandImpl;
    }
}
