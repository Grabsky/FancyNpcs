package de.oliver.fancynpcs.api;

import java.util.List;
import java.util.Map;

public interface FancyNpcsConfig {

    boolean isSkipInvisibleNpcs();

    boolean isInteractionCooldownMessageDisabled();

    boolean isMuteVersionNotification();

    boolean isEnableAutoSave();

    int getAutoSaveInterval();

    int getNpcUpdateInterval();

    int getTurnToPlayerDistance();

    int getVisibilityDistance();

    int getRemoveNpcsFromPlayerlistDelay();

    List<String> getBlockedCommands();

    Map<String, Integer> getMaxNpcsPerPermission();

}
