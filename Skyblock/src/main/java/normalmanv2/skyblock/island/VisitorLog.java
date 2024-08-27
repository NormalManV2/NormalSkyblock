package normalmanv2.skyblock.island;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class VisitorLog {
    private final List<UUID> visitorLog;

    public VisitorLog() {
        this.visitorLog = new ArrayList<>();
    }

    public void logVisitor(UUID visitorId) {
        visitorLog.add(visitorId);
    }

    public List<UUID> getVisitorLog() {
        return Collections.unmodifiableList(visitorLog);
    }

    public void clearVisitorLog() {
        visitorLog.clear();
    }
}
