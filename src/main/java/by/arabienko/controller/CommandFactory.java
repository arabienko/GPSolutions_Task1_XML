package by.arabienko.controller;

import by.arabienko.controller.command.Command;
import by.arabienko.controller.command.impl.CommandDomParserImpl;
import by.arabienko.controller.command.impl.CommandFindByParam;
import by.arabienko.controller.command.impl.CommandSaveToFileXML;

/**
 * A factory for creating a single instance for command.
 */
public class CommandFactory {

    private static final CommandFactory INSTANCE = new CommandFactory();

    private CommandFactory() {
    }

    public static CommandFactory getInstance() {
        return INSTANCE;
    }

    private final Command commandDomParser =
            new CommandDomParserImpl();
    private final Command commandFindByParam =
            new CommandFindByParam();
    private final Command commandSaveToFileXML =
            new CommandSaveToFileXML();

    public Command getCommandDomParser() {
        return commandDomParser;
    }

    public Command getCommandFindByParam() {
        return commandFindByParam;
    }

    public Command getCommandSaveToFileXML() {
        return commandSaveToFileXML;
    }

}
