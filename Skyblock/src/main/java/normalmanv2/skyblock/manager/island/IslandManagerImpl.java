package normalmanv2.skyblock.manager.island;

import normalmanv2.common.invite.InviteService;
import normalmanv2.skyblock.Skyblock;
import normalmanv2.skyblock.island.IslandImpl;
import normalmanv2.skyblockAPI.island.Island;
import normalmanv2.skyblockAPI.island.IslandManager;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class IslandManagerImpl implements IslandManager {

    private final Map<UUID, IslandImpl> islands;
    private final Skyblock plugin;
    private final IslandFileManager fileUtils;
    private final InviteService inviteService;

    public IslandManagerImpl(Skyblock plugin) {
        this.islands = new ConcurrentHashMap<>();
        this.plugin = plugin;

        this.fileUtils = plugin.getIslandFileManager();
        this.inviteService = plugin.getInviteService();
        loadAllIslands();
    }


    @Override
    public void loadAllIslands() {
        List<IslandImpl> loadedIslands = this.fileUtils.loadAllIslands();
        for (IslandImpl island : loadedIslands) {
            this.islands.put(island.getOwner(), island);
        }
    }

    @Override
    public void saveAllIslands(String fileName) {
        for (IslandImpl island : this.islands.values()) {
            this.fileUtils.saveData(island, fileName);
        }
    }

    @Override
    public Island createIsland(UUID owner) {
        if (islandExists(owner)){
            return null;
        }
        if (getOrThrowIslandByMember(owner) != null){
            return null;
        }

        Island island = new IslandImpl(owner);
        String fileName = owner.toString();
        saveIsland(island, fileName);

        return island;
    }

    @Override
    public boolean deleteIsland(UUID owner) {
        IslandImpl island = this.islands.remove(owner);
        if (island == null) {
            return false;
        }
        this.fileUtils.deleteFileData(owner.toString());
        return true;
    }

    @Override
    public Island getOrThrowIslandByOwner(UUID owner) {
        return this.islands.get(owner);
    }

    @Override
    public Island getOrThrowIslandByMember(UUID member) {
        for (IslandImpl island : this.islands.values()) {
            if (!island.isMember(member)){
                continue;
            }
            return island;
        }
        return null;
    }

    @Override
    public List<Island> getAllIslands() {
        return new ArrayList<>(this.islands.values());
    }

    @Override
    public boolean islandExists(UUID member) {
        for (IslandImpl island : this.islands.values()){
            if (!island.isMember(member)){
                continue;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean setIslandHome(Island island, Location location) {
        IslandImpl islandImpl = (IslandImpl) island;
        if (islandImpl == null){
            return false;
        }
        islandImpl.setIslandHome(location);
        return true;
    }

    @Override
    public void saveIsland(Island island, String fileName) {
        this.fileUtils.saveData((IslandImpl) island, fileName);
    }

    @Override
    public void saveAndUnloadAllIslands() {
        for (IslandImpl island : this.islands.values()) {
            this.fileUtils.saveData(island, island.getOwner().toString());
            this.islands.remove(island.getOwner());
        }
    }

    @Override
    public boolean logVisitor(Island island, UUID visitorId) {
        IslandImpl islandImpl = (IslandImpl) island;
        if (islandImpl == null){
            return false;
        }
        islandImpl.getVisitorLog().logVisitor(visitorId);
        return true;
    }

    @Override
    public void setIslandScore(Island island, double score) {
        IslandImpl islandImpl = (IslandImpl) island;
        if (islandImpl == null){
            return;
        }
        islandImpl.getIslandStats().setScore(score);
    }

    @Override
    public void addIslandScore(Island island, double score) {
        IslandImpl islandImpl = (IslandImpl) island;
        if (islandImpl == null){
            return;
        }
        islandImpl.getIslandStats().addScore(score);
    }

    @Override
    public Set<UUID> getOrThrowIslandMembersByOwner(UUID islandOwner) {
        IslandImpl island = this.islands.get(islandOwner);
        if (island == null){
            throw new RuntimeException("Island does not exist or is not found! " + islandOwner);
        }
        return Collections.unmodifiableSet(island.getIslandMembers());
    }

    @Override
    public Set<UUID> getOrThrowBannedIslandMembersByOwner(UUID islandOwner) {
        IslandImpl island = this.islands.get(islandOwner);
        if (island == null){
            throw new RuntimeException("Island does not exist or is not found! " + islandOwner);
        }
        return Collections.unmodifiableSet(island.getBannedIslandMembers());
    }

    @Override
    public Set<UUID> getOrThrowIslandMembersByMember(UUID islandMember) {
        for (IslandImpl island : this.islands.values()) {
            if (!island.isMember(islandMember)){
                continue;
            }
            return Collections.unmodifiableSet(island.getIslandMembers());
        }
        throw new RuntimeException("Island does not exist or is not found! " + islandMember);
    }

    @Override
    public Set<UUID> getOrThrowBannedIslandMembersByMember(UUID islandMember) {
        for (IslandImpl island : this.islands.values()) {
            if (!island.isMember(islandMember)){
                continue;
            }
            return Collections.unmodifiableSet(island.getBannedIslandMembers());
        }
        throw new RuntimeException("Island does not exist or is not found! " + islandMember);
    }
}
