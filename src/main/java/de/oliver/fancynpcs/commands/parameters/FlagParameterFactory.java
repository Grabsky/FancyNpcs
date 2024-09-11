package de.oliver.fancynpcs.commands.parameters;

import revxrsal.commands.Lamp;
import revxrsal.commands.annotation.list.AnnotationList;
import revxrsal.commands.command.CommandActor;
import revxrsal.commands.node.ExecutionContext;
import revxrsal.commands.parameter.ParameterType;
import revxrsal.commands.util.Classes;

import java.lang.reflect.Type;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum FlagParameterFactory implements ParameterType.Factory<CommandActor> {
    INSTANCE;

    private static final String PREFIX = "--";

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"}) // We can safely suppress these IDE warnings.
    public @Nullable <T> ParameterType<CommandActor, T> create(final @NotNull Type type, final @NotNull AnnotationList annotations, final @NotNull Lamp<CommandActor> lamp) {
        final Class<?> rawType = Classes.getRawType(type);
        // Returning for non-flag parameters.
        if (rawType != Flag.class)
            return null;
        // Getting the @Flag annotation placed on the parameter.
        final de.oliver.fancynpcs.commands.annotations.Flag annotation = annotations.get(de.oliver.fancynpcs.commands.annotations.Flag.class);
        // Returning if no @Flag annotation have been specified for this flag.
        if (annotation == null)
            return null;
        // ...
        final Type delegateType = Classes.getFirstGeneric(type, Object.class);
        final ParameterType<?, ?> delegate = lamp.resolver(delegateType).requireParameterType();
        // ...
        return (input, context) -> {
            final String text = input.source();
            final String flag = PREFIX + annotation.value();
            int flagIndex = text.indexOf(flag);

            // TODO: Throw error if no flag found and flag is not optional
            // TODO: Throw error if no value found
            if (flagIndex == -1 || input.isEmpty()) {
                input.moveBackward();
                return (T) new Flag<>(null);
            }

            input.setPosition(flagIndex);

            input.read(flag.length());
            input.readWhile(Character::isWhitespace);
            System.out.println(input.peekRemaining());

            return (T) new Flag<>(delegate.parse(input, ((ExecutionContext) context)));
        };
    }

}
