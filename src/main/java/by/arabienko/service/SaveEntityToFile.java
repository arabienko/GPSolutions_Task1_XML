package by.arabienko.service;

import by.arabienko.entity.Hotel;
import by.arabienko.exeptions.ExceptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

/**
 * Saving hotel data in an XML file.
 */
public class SaveEntityToFile {
    private static final Logger LOGGER =
            LogManager.getLogger(DomBuilderHotels.class);
    private static final String ROOT = "Lists";
    private static final String NAMES = "Names";
    private static final String PRICES = "Prices";
    private static final String ADDRESSES = "Addresses";
    private static final String NAME = "Name";
    private static final String PRICE = "Price";
    private static final String ADDRESS = "Address";
    private static final String FILE_NAME = "outHotel.xml";

    private DocumentBuilder builder;

    public SaveEntityToFile() throws ExceptionService {
        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.debug(
                    "error ParserConfigurationException: " + e);
            throw new ExceptionService(
                    "error ParserConfigurationException: " + e);
        }
    }

    public Set<Hotel> saveEntity(Set<Hotel> hotels)
            throws ExceptionService {
        LOGGER.debug(
                "Start creation xml file...");
        Document document = builder.newDocument();
        Element rootElement = document.createElement(ROOT);
        document.appendChild(rootElement);
        Element elementNames = document.createElement(NAMES);
        rootElement.appendChild(elementNames);
        Element elementPrices = document.createElement(PRICES);
        rootElement.appendChild(elementPrices);
        Element elementAddresses = document.createElement(ADDRESSES);
        rootElement.appendChild(elementAddresses);
        for (Hotel hotel : hotels) {
            Element elementName = document.createElement(NAME);
            elementName.appendChild(
                    document.createTextNode(hotel.getHotelName()));
            elementNames.appendChild(elementName);
            Element elementPrice = document.createElement(PRICE);
            elementPrice.appendChild(
                    document.createTextNode(hotel.getPrice()));
            elementPrices.appendChild(elementPrice);
            Element elementAddress = document.createElement(ADDRESS);
            elementAddress.appendChild(
                    document.createTextNode(hotel.getHotelAddress().getAddressLine()));
            elementAddresses.appendChild(elementAddress);
        }
        TransformerFactory transformerFactory =
                TransformerFactory.newInstance();
        try {
            Path path = Paths.get("src/main/resources");
            Path fileToCreatePath = path.resolve(FILE_NAME);
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);

            StreamResult streamResult = new StreamResult(
                    new FileWriter(String.valueOf(fileToCreatePath)));
            transformer.transform(source, streamResult);
        } catch (TransformerConfigurationException e) {
            LOGGER.debug(
                    "error TransformerConfigurationException: " + e);
            throw new ExceptionService(
                    "error TransformerConfigurationException: " + e);
        } catch (TransformerException e) {
            LOGGER.debug(
                    "error TransformerException: " + e);
            throw new ExceptionService(
                    "error TransformerException: " + e);
        } catch (IOException e) {
            LOGGER.debug(
                    "error IOException: " + e);
            throw new ExceptionService(
                    "error IOException: " + e);
        }
        return hotels;
    }
}
