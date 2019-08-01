package uk.ac.sheffield.aca15er;

/**
 * Created by euan on 27/02/2016.
 * Maps column types to their names in the datafile
 */
public enum Column {
    PRESSURE("Sea Level PressurehPa"),
    CONDITIONS("Conditions"),
    WIND_DIR("Wind Direction"),
    TEMPERATURE("TemperatureC"),
    WIND_SPEED("Wind SpeedKm/h"),
    WIND_DIR_DEG("WindDirDegrees"),
    PRECIPITATION("Precipitationmm");

    public final String columnName;

    Column(String columnName)
    {
        this.columnName = columnName;
    }
}
