package by.arabienko.controller.command.impl;

import by.arabienko.controller.command.Command;
import by.arabienko.entity.Hotel;
import by.arabienko.service.DomBuilderHotels;
import by.arabienko.exeptions.ExceptionService;

import java.util.Set;

public class CommandDomParserImpl implements Command {
    @Override
    public Set<Hotel> execute(Set<Hotel> hotels) throws ExceptionService {
        DomBuilderHotels builder = new DomBuilderHotels();
        builder.buildHotels("Hotels.xml");
        return builder.getHotels();
    }
}
