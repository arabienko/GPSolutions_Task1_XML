package by.arabienko.controller.command;

import by.arabienko.entity.Hotel;
import by.arabienko.exeptions.ExceptionService;

import java.util.Set;

/**
 * Command interface
 */
public interface Command {
    Set<Hotel> execute(Set<Hotel> hotels) throws ExceptionService;
}
