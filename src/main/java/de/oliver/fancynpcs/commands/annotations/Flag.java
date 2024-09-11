package de.oliver.fancynpcs.commands.annotations;

import org.jetbrains.annotations.NotNull;

public @interface Flag {

    /**
     * Flag name
     */
    @NotNull String value();

}
