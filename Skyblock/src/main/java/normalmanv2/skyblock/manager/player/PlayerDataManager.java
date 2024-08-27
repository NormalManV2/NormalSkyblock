package normalmanv2.skyblock.manager.player;

import normalmanv2.skyblock.island.IslandImpl;
import normalmanv2.skyblock.player.PlayerData;
import normalmanv2.skyblock.role.Role;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerDataManager {

    private final Map<UUID, PlayerData> playerDataMap;
    private final PlayerFileManager fileUtils;

    public PlayerDataManager(PlayerFileManager fileUtils) {
        this.playerDataMap = new ConcurrentHashMap<>();
        this.fileUtils = fileUtils;
    }

    public PlayerData createPlayerData(UUID playerId){
        PlayerData playerData = new PlayerData(playerId);
        playerDataMap.put(playerId, playerData);
        fileUtils.saveData(playerData, playerId.toString());
        return playerData;
    }

    public PlayerData getPlayerData(UUID playerId){
        return playerDataMap.computeIfAbsent(playerId, PlayerData::new);
    }

    public void setPlayerRole(UUID playerId, Role role){
        getPlayerData(playerId).setIslandRole(role);
    }

    public Role getPlayerRole(UUID playerId){
        return getPlayerData(playerId).getIslandRole();
    }

    public void setPlayerIsland(UUID playerId, IslandImpl islandImpl){
        getPlayerData(playerId).setIsland(islandImpl);
    }

    public IslandImpl getPlayerIsland(UUID playerId){
        return getPlayerData(playerId).getIsland() !=null ? getPlayerData(playerId).getIsland() : null;
    }

}
