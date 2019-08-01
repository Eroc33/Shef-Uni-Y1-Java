package uk.ac.sheffield.aca15er;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Represents a data point in time, which can be read from a datasource
 */
public class Observation {
    String time;
    float temperature;
    float dewPoint;
    int humidity;
    int pressure;
    float visibility;
    String windDir;
    float windSpeed;
    float gustSpeed;
    //not clear what precipitation should actually be as it is always N/A in this file, but probably an int as fractions of mm are nor usually used
    int precipitation; //if N/A then that is the same as 0
    String events; //also never set?, can only assume this would be a string
    String conditions;
    int windDirDeg;
    Date date;

    /**
     * Formats an observation as a string consisting of a given set of reading types
     */
    public void format(StringBuilder builder,Column[] columns)
    {
        builder.append(time)
                .append(":\t\t");
        for(Column col : columns){
            builder.append(getColumn(col)).append(" ");
        }
    }

    /**
     * Formats a given reading type as a string
     */
    private String getColumn(Column col) {
        switch(col){
            case PRESSURE:
                return Float.toString(pressure);
            case CONDITIONS:
                return conditions;
            case WIND_DIR:
                return windDir;
            case TEMPERATURE:
                return Float.toString(temperature);
            case WIND_SPEED:
                return Float.toString(windSpeed);
            case WIND_DIR_DEG:
                return Integer.toString(windDirDeg);
            case PRECIPITATION:
                return Integer.toString(precipitation);
            default:
                //unreachable
                return null;
        }
    }
}
