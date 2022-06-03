package by.arabienko.controller.command.impl;

import by.arabienko.controller.command.Command;
import by.arabienko.entity.Hotel;
import by.arabienko.exeptions.ExceptionService;
import by.arabienko.service.FindEntityByParam;

import java.util.Set;

public class CommandFindByParam implements Command {
    @Override
    public Set<Hotel> execute(Set<Hotel> hotels) throws ExceptionService {
        FindEntityByParam findEntityByParam = new FindEntityByParam();
        return findEntityByParam.findByParam(hotels);
    }
}
