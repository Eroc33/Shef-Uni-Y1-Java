package uk.ac.sheffield.aca15er;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Reads a csv style datasource of the expected weatherdata format from an inputstream
 */
public class FileDataSource implements DataSource {
    private List<Observation> data;
    public FileDataSource(InputStream stream) throws ParseException {
        data = new ArrayList<>();
        Scanner scanner = new Scanner(stream);
        String columns = scanner.nextLine();
        String[] columnNames = columns.split(",");
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(!line.isEmpty()) {
                data.add(parseObservation(columnNames, line));
            }
        }
    }

    Observation parseObservation(String[] columnNames, String s) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Observation point = new Observation();
        Scanner scanner = new Scanner(s);
        //remove & split at `,`, or `<br/>`
        scanner.useDelimiter(",|(<br \\/>)");
        for(int  i=0;i<columnNames.length && scanner.hasNext();i++) {
            switch(columnNames[i]){
                case "TimeBST":
                    point.time = scanner.next().trim();
                    break;
                case "TemperatureC":
                    point.temperature = scanner.nextFloat();
                    break;
                case "Dew PointC":
                    point.dewPoint = scanner.nextFloat();
                    break;
                case "Humidity":
                    point.humidity = scanner.nextInt();
                    break;
                case "Sea Level PressurehPa":
                    point.pressure = scanner.nextInt();
                    break;
                case "VisibilityKm":
                    point.visibility = scanner.nextFloat();
                    break;
                case "Wind Direction":
                    point.windDir = scanner.next().trim();
                    break;
                case "Wind SpeedKm/h":
                    if(scanner.hasNextFloat()) {
                        point.windSpeed = scanner.nextFloat();
                    }else{
                        String other = scanner.next().trim();
                        if(other.equalsIgnoreCase("calm")){
                            //presumably calm == 0
                            point.gustSpeed = 0;
                        }else{
                            unexpectedValue(other,columnNames[i],scanner);
                        }
                    }
                    break;
                case "Gust SpeedKm/h":
                    if(scanner.hasNextFloat()){
                        point.gustSpeed = scanner.nextFloat();
                    }else{
                        String other = scanner.next().trim();
                        if(other.equals("-")){
                            //presumably this means it's the same as windspeed
                            point.gustSpeed = point.windSpeed;
                        }else{
                            unexpectedValue(other,columnNames[i],scanner);
                        }
                    }
                    break;
                case "Precipitationmm":
                    if(scanner.hasNextInt()) {
                        point.precipitation = scanner.nextInt();
                    }else {
                        String other = scanner.next().trim();
                        if(other.equalsIgnoreCase("n/a")){
                            //presumably n/a == no rainfall == 0mm precipitation
                            point.precipitation = 0;
                        }else{
                            unexpectedValue(other,columnNames[i],scanner);
                        }
                    }
                    break;
                case "Events":
                    point.events = scanner.next().trim();
                    break;
                case "Conditions":
                    point.conditions = scanner.next().trim();
                    break;
                case "WindDirDegrees":
                    point.windDirDeg = scanner.nextInt();
                    break;
                case "DateUTC<br />":
                    point.date = dateFormat.parse(scanner.next().trim());
                    break;
                default:
                    System.out.print("[WARN]: unknown field: "+columnNames[i]);
            }
        }
        return point;
    }

    private void unexpectedValue(String value,String column,Scanner scanner) throws ParseException {
        throw new ParseException("Unexpected value '"+value+"' in column '"+column+"'",scanner.match().start());
    }

    public List<Observation> getData(){
        return data;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(Observation point : data){
            builder.append(point.toString());
            builder.append("\r\n");
        }
        return builder.toString();
    }
}
