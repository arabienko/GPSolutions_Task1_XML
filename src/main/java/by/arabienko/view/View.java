package by.arabienko.view;

import by.arabienko.controller.CommandFactory;
import by.arabienko.entity.Hotel;
import by.arabienko.exeptions.ExceptionService;
import by.arabienko.service.DomBuilderHotels;
import by.arabienko.service.SaveEntityToFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;

public class View {
    private static final Logger LOGGER =
            LogManager.getLogger(DomBuilderHotels.class);

    public static void clientView() {
        LOGGER.info("Getting started on the client side.");
        CommandFactory commandFactory = CommandFactory.getInstance();
        try {
            commandFactory.getCommandSaveToFileXML().execute(
                    commandFactory.getCommandFindByParam().execute(
                            commandFactory.getCommandDomParser().execute(new HashSet<>())));
        } catch (ExceptionService e) {
            LOGGER.info("Service error: " + e);
            e.printStackTrace();
        }
    }
}
