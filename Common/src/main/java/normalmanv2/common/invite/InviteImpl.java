package normalmanv2.common.invite;

import normalmanv2.api.utils.invite.Invite;

import java.time.Instant;
import java.util.UUID;

public record InviteImpl(UUID inviter, UUID invitee, Instant expiryTime) implements Invite {

    @Override
    public UUID getInviter() {
        return inviter;
    }

    @Override
    public UUID getInvitee() {
        return invitee;
    }

    public boolean isExpired() {
        return Instant.now().isAfter(expiryTime);
    }

    @Override
    public Instant getExpires() {
        return expiryTime;
    }
}
