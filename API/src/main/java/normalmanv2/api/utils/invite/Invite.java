package normalmanv2.api.utils.invite;

import java.time.Instant;
import java.util.UUID;

public interface Invite {

    UUID getInviter();
    UUID getInvitee();
    boolean isExpired();
    Instant getExpires();

}
