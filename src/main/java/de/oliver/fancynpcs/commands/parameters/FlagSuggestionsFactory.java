package de.oliver.fancynpcs.commands.parameters;

import revxrsal.commands.Lamp;
import revxrsal.commands.annotation.list.AnnotationList;
import revxrsal.commands.autocomplete.SuggestionProvider;
import revxrsal.commands.command.CommandActor;

import java.lang.reflect.Type;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum FlagSuggestionsFactory implements SuggestionProvider.Factory<CommandActor> {
    INSTANCE;

    private static final String PREFIX = "--";

    @Override
    public @Nullable SuggestionProvider<CommandActor> create(final @NotNull Type type, final @NotNull AnnotationList annotations, final @NotNull Lamp lamp) {
        // TO-DO: Implementation.
        return SuggestionProvider.empty();
    }

}
