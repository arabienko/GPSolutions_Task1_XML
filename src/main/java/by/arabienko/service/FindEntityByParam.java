package by.arabienko.service;

import by.arabienko.entity.Hotel;
import by.arabienko.exeptions.ExceptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

/**
 * Search for a list of Hotels by parameters:
 * whose name contains "Hilton"
 * and are located in the state of New York
 */
public class FindEntityByParam {
    private static final Logger LOGGER =
            LogManager.getLogger(FindEntityByParam.class);

    public Set<Hotel> findByParam(Set<Hotel> hotels) throws ExceptionService {
        Set<Hotel> hotelSet = new HashSet<>();
        if (hotels.isEmpty()) {
            LOGGER.debug(
                    "Set is empty.");
            throw new ExceptionService(
                    "Set is empty.");
        }
        for (Hotel hotel : hotels) {
            if (hotel.getHotelName().contains("Hilton")
                    && (hotel.getHotelAddress().getState().contains("NY")
                    || hotel.getHotelAddress().getState().contains("New York")
                    || hotel.getHotelAddress().getState().contains("NEW YORK"))) {
                hotelSet.add(hotel);
            }
        }
        LOGGER.debug(hotelSet.size()
                + " objects were found for the given parameter.");
        return hotelSet;
    }
}
