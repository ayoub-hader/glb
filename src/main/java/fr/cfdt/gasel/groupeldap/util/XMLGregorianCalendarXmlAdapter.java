package fr.cfdt.gasel.groupeldap.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * The type Xml gregorian calendar xml adapter.
 */
public class XMLGregorianCalendarXmlAdapter {

    /**
     * The Logger.
     */
    static final Logger logger = LoggerFactory.getLogger(XMLGregorianCalendarXmlAdapter.class);

    private XMLGregorianCalendarXmlAdapter() {
    }

    /**
     * Parse method xml gregorian calendar.
     *
     * @param v the v
     * @return the xml gregorian calendar
     */
    public static XMLGregorianCalendar parseMethod(String v) {
        if (v == null || v.trim().isEmpty()) {
            return null;
        }
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(v);
        } catch (DatatypeConfigurationException e) {
            logger.error(e.getMessage());
        }
        return null;
    }


    /**
     * Print method string.
     *
     * @param v the v
     * @return the string
     */
    public static String printMethod(XMLGregorianCalendar v) {
        return v == null ? null : DateUtils.xmlGregorianCalendarToString(v, DateUtils.DATE_PATTERN_STRING);
    }

}