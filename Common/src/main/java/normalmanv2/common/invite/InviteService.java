package normalmanv2.common.invite;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InviteService {

    private final Map<UUID, Set<InviteImpl>> invites;

    public InviteService() {
        this.invites = new ConcurrentHashMap<>();
    }

    public void createInvite(UUID inviter, UUID invitee, Duration duration) {
        Instant expiryTime = Instant.now().plus(duration);
        InviteImpl inviteImpl = new InviteImpl(inviter, invitee, expiryTime);

        this.invites.computeIfAbsent(invitee, uuid ->
                ConcurrentHashMap.newKeySet()).add(inviteImpl);
    }

    public boolean acceptInvite(UUID invitee) {
        Set<InviteImpl> inviteSet = this.invites.get(invitee);
        if (inviteSet == null) {
            return false;
        }
        Optional<InviteImpl> inviteOpt = inviteSet.stream().findFirst();
        if (inviteOpt.isEmpty() || inviteOpt.get().isExpired()) {
            return false;
        }
        inviteSet.remove(inviteOpt.get());
        if (inviteSet.isEmpty()) {
            this.invites.remove(invitee);
        }
        return true;
    }

    public boolean rejectInvite(UUID invitee) {
        return this.invites.remove(invitee) != null;
    }

    public boolean hasInvite(UUID invitee) {
        Set<InviteImpl> inviteSet = this.invites.get(invitee);
        return inviteSet != null && inviteSet.stream().anyMatch(invite -> !invite.isExpired());
    }

    public void removeExpiredInvites() {
        for (Set<InviteImpl> inviteSet : this.invites.values()) {
            inviteSet.removeIf(InviteImpl::isExpired);
        }
    }

    public void clearInvitesMap(){
        this.invites.clear();
    }

}
