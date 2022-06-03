package by.arabienko.controller.command.impl;

import by.arabienko.controller.command.Command;
import by.arabienko.entity.Hotel;
import by.arabienko.exeptions.ExceptionService;
import by.arabienko.service.SaveEntityToFile;

import java.util.Set;

public class CommandSaveToFileXML implements Command {
    @Override
    public Set<Hotel> execute(Set<Hotel> hotels) throws ExceptionService {
        SaveEntityToFile saveEntityToFile = new SaveEntityToFile();
        return saveEntityToFile.saveEntity(hotels);
    }
}
