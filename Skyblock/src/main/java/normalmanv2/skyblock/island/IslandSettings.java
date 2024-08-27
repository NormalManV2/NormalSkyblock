package normalmanv2.skyblock.island;

import normalmanv2.api.utils.settings.Settings;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class IslandSettings implements Settings {

    private final Map<String, Boolean> settings;

    public IslandSettings() {
        this.settings = new HashMap<>();
        initDefaultSettings();
    }

    private void initDefaultSettings() {
        settings.put("allowVisitors", true);
        settings.put("enablePVP", false);
        settings.put("allowFlight", true);
        settings.put("allowFriendlyFire", false);
    }

    @Override
    public void setSetting(String key, Boolean value) {
        this.settings.put(key, value);
    }

    @Override
    public boolean getSetting(String key) {
        return settings.get(key);
    }

    @Override
    public void removeSetting(String key) {
        this.settings.remove(key);
    }

    @Override
    public Map<String, Boolean> getSettings() {
        return Collections.unmodifiableMap(settings);
    }
}
