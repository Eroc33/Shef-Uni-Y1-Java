package uk.ac.sheffield.aca15er;

import javax.swing.*;

/**
 * Created by euan on 11/04/2016.
 */
public class GUIWeatherDataViewer extends JFrame{

    private String reportLocation;

    GUIWeatherDataViewer(){
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        add(new LocationChooser(this));
        pack();
    }


    public static void main(String args[]){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e) {
            //It's not super important if we can't set the look and feel so just ignore it.
        }
        new GUIWeatherDataViewer();
    }

    public void setReportLocation(String reportLocation) {
        this.reportLocation = reportLocation;
    }
}
