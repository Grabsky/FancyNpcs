package de.oliver.fancynpcs.commands;

import de.oliver.fancynpcs.FancyNpcs;
import de.oliver.fancynpcs.commands.npc.CreateCMD;
import de.oliver.fancynpcs.commands.parameters.FlagParameterFactory;
import de.oliver.fancynpcs.commands.parameters.FlagSuggestionsFactory;
import revxrsal.commands.Lamp;
import revxrsal.commands.bukkit.BukkitLamp;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;

import org.jetbrains.annotations.NotNull;

public final class LampCommandManager {

    private final @NotNull FancyNpcs plugin;

    private Lamp<BukkitCommandActor> lamp;

    public LampCommandManager(final @NotNull FancyNpcs plugin) {
        this.plugin = plugin;
    }

    public LampCommandManager initialize() {
        // Building an instance of Lamp.
        this.lamp = BukkitLamp.builder(plugin)
                // Registering parameters.
                .parameterTypes(it -> {
                    it.addParameterTypeFactory(FlagParameterFactory.INSTANCE);
                })
                // Registering suggestions.
                .suggestionProviders(it -> {
                    it.addProviderFactory(FlagSuggestionsFactory.INSTANCE);
                })
                .build();
        // ...
        return this;
    }

    public LampCommandManager registerCommands() {
        lamp.register(CreateCMD.INSTANCE);
        // ...
        return this;
    }

    public Lamp<BukkitCommandActor> getLamp() {
        // Throwing exception if Lamp has not been initialized yet.
        if (lamp == null)
            throw new IllegalStateException("NOT_INITIALIZED");
        // ...
        return lamp;
    }

}
