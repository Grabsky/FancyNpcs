package de.oliver.fancynpcs.commands.parameters;

import org.bukkit.Location;
import revxrsal.commands.autocomplete.SuggestionProvider;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;
import revxrsal.commands.bukkit.exception.SenderNotPlayerException;
import revxrsal.commands.node.ExecutionContext;
import revxrsal.commands.parameter.ParameterType;
import revxrsal.commands.stream.MutableStringStream;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.jetbrains.annotations.NotNull;

public enum LocationParameter implements ParameterType<BukkitCommandActor, Location>, SuggestionProvider<BukkitCommandActor> {
    INSTANCE;

    @Override
    public Location parse(final @NotNull MutableStringStream input, final @NotNull ExecutionContext<BukkitCommandActor> context) {
        final double x = input.readDouble();
        final double y = input.readDouble();
        final double z = input.readDouble();
        // ...
        if (context.actor().isPlayer() == false)
            throw new SenderNotPlayerException();
        // ...
        return new Location(context.actor().asPlayer().getWorld(), x, y, z);
    }

    @Override
    public @NotNull Collection<String> getSuggestions(@NotNull final ExecutionContext<BukkitCommandActor> context) {
        return context.actor().isPlayer() ? List.of("~ ~ ~") : Collections.emptyList();
    }

}