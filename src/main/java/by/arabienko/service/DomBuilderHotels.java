package by.arabienko.service;

import by.arabienko.entity.Hotel;
import by.arabienko.exeptions.ExceptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * DOM parser: creating Hotel objects
 * and writing them to the collection.
 */
public class DomBuilderHotels {
    private static final Logger LOGGER =
            LogManager.getLogger(DomBuilderHotels.class);
    private final Set<Hotel> hotelSet;
    private DocumentBuilder documentBuilder;

    public DomBuilderHotels() throws ExceptionService {
        hotelSet = new HashSet<>();
        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.debug(
                    "error ParserConfigurationException: " + e);
            throw new ExceptionService(
                    "error ParserConfigurationException: " + e);
        }
    }

    public Set<Hotel> getHotels() {
        return hotelSet;
    }

    /**
     * Build Entity from XML File
     *
     * @param fileName
     */
    public void buildHotels(String fileName) {
        try {
            if (fileName.length()==0) {
                LOGGER.debug(
                        "Document is not exist. " + fileName);
                throw new ExceptionService(
                        "Document is not exist. " + fileName);
            }
            LOGGER.debug("DOM parser: generating " +
                    "a collection of objects based " +
                    "on parsing an XML document. "+fileName);
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(fileName);
            Document document;
            document = documentBuilder.parse(inputStream);
            Element root = document.getDocumentElement();
            // getting a list of <Hotel> child elements
            NodeList hotelList =
                    root.getElementsByTagName("Hotel");
            for (int i = 0; i < hotelList.getLength(); i++) {
                Element hotelElement =
                        (Element) hotelList.item(i);
                Hotel hotel =
                        buildHotel(hotelElement);
                hotelSet.add(hotel);
            }
        } catch (SAXException e) {
            LOGGER.debug("error DOM parser:{}. ",
                    fileName + e.getMessage());
        } catch (IOException e) {
            LOGGER.debug("error I/О (DOM): "
                    + e.getMessage());
        } catch (ExceptionService exceptionService) {
            LOGGER.debug(
                    "Document is not exist (DOM). " + fileName);
        }
    }

    private Hotel buildHotel(
            Element hotelElement) {
        Hotel hotel = new Hotel();
        try {
            if (hotelElement==null) {
                throw new ExceptionService("Element is empty. ");
            }
            String price = hotelElement.getAttribute("Price");
            hotel.setPrice(price);
            String name = getElementTextContent(hotelElement, "Name");
            hotel.setHotelName(name);
            NodeList addressList =
                    hotelElement.
                            getElementsByTagName("Address");
            Element addressNode =
                    (Element) addressList.item(0);
            Hotel.Address address = buildAddress(addressNode);
            hotel.setHotelAddress(address);
        } catch (ExceptionService exceptionService) {
            LOGGER.error("Element hotelElement is empty. ");
        }
        return hotel;
    }

    private Hotel.Address buildAddress(
            Element addressElement) {
        Hotel.Address address = new Hotel().new Address();
        try {
            if (addressElement==null) {
                throw new ExceptionService("Element is empty. ");
            }
            String addressLine = getElementTextContent(addressElement, "AddressLine");
            String city = getElementTextContent(addressElement, "City");
            String country = getElementTextContent(addressElement, "Country");
            String state = getElementTextContent(addressElement, "State");
            address.setAddressLine(addressLine);
            address.setCity(city);
            address.setCountry(country);
            address.setState(state);
        } catch (ExceptionService e) {
            LOGGER.error("Element Address is empty. ");
        }
        return address;
    }

    // get the text content of the tag
    private static String getElementTextContent(
            Element element, String elementName) {
        NodeList nList = null;
        try {
            if (element==null ||
                    elementName.length()==0) {
                throw new ExceptionService(
                        "Element or element name is empty. ");
            }
            nList = element.
                    getElementsByTagName(elementName);
        } catch (ExceptionService e) {
            LOGGER.debug("Element or element name is empty. ");
        }
        if (nList!=null) {
            return nList.item(0).getTextContent();
        }
        return null;
    }
}
