package fr.cfdt.gasel.groupeldap.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * The type Date utils.
 */
public class DateUtils {
    /**
     * The Logger.
     */
    static Logger logger = LoggerFactory.getLogger(DateUtils.class) ;
    /**
     * The constant DATE_PATTERN.
     */
    public static final String DATE_PATTERN = "dd/MM/yyyy" ;
    /**
     * The constant DATE_PATTERN_STRING.
     */
    public static final String DATE_PATTERN_STRING = "yyyy-MM-dd" ;
    /**
     * The constant CMI_DATE_FORMAT.
     */
    public static final String CMI_DATE_FORMAT = "yyyyMMddHHmmss";

    private DateUtils() {
    }


    /**
     * converte date to XMLGregorianCalendar
     *
     * @param date the date
     * @return xml gregorian calendar
     * @throws DatatypeConfigurationException
     */
    public static XMLGregorianCalendar dateToXmlGregorianCalendar(Date date) {
        if (date == null) return null;
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        XMLGregorianCalendar xmlDate = null;
        try {
            xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        } catch (DatatypeConfigurationException e) {
            logger.error(e.getMessage(), e);
        }
        return xmlDate;
    }

    /**
     * converte XMLGregorianCalendar to string
     *
     * @param xmlGregorianCalendar the xml gregorian calendar
     * @return string
     */
    public static String xmlGregorianCalendarToString(XMLGregorianCalendar xmlGregorianCalendar) {
        return xmlGregorianCalendarToString(xmlGregorianCalendar, DATE_PATTERN);
    }

    /**
     * converte XMLGregorianCalendar to string
     *
     * @param xmlGregorianCalendar the xml gregorian calendar
     * @param format               the format
     * @return string
     */
    public static String xmlGregorianCalendarToString(XMLGregorianCalendar xmlGregorianCalendar, String format) {
        if (xmlGregorianCalendar == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(format);
        return df.format(xmlGregorianCalendar.toGregorianCalendar().getTime());
    }

    /**
     * Build gregorian calendar xml gregorian calendar.
     *
     * @param year  the year
     * @param month the month
     * @param day   the day
     * @return the xml gregorian calendar
     */
    public static XMLGregorianCalendar buildGregorianCalendar(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        Date date = calendar.getTime();
        return DateUtils.dateToXmlGregorianCalendar(date);
    }

    /**
     * Gets date.
     *
     * @param year  the year
     * @param month the month
     * @param day   the day
     * @return the date
     */
    public static Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }


    /**
     * converte Date to string
     *
     * @param date   the date
     * @param format the format
     * @return String string
     */
    public static String dateToString(Date date, String format) {
        if(date == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * converte Date to string
     *
     * @param date the date
     * @return String string
     */
    public static String dateToString(Date date) {
        return dateToString(date, DATE_PATTERN);
    }
}
