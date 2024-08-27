package normalmanv2.skyblock.island;

import normalmanv2.api.utils.invite.Invite;
import normalmanv2.skyblock.manager.island.IslandManagerImpl;
import normalmanv2.skyblockAPI.island.Island;

import java.util.UUID;

public class IslandInviteHandler {

    private final IslandManagerImpl islandManager;

    public IslandInviteHandler(IslandManagerImpl islandManager) {
        this.islandManager = islandManager;
    }

    public boolean handleInvite(Invite invite) {
        if (invite.isExpired()){
            return false;
        }

        UUID inviter = invite.getInviter();
        UUID invitee = invite.getInvitee();

        Island island = islandManager.getOrThrowIslandByMember(inviter);

        if (island == null) {
            return false;
        }
        if (island.getIslandMembers().contains(invitee)){
            return false;
        }
        return !island.getBannedIslandMembers().contains(invitee);
    }
}
