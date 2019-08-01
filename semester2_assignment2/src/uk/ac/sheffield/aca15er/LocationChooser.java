package uk.ac.sheffield.aca15er;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.List;

/**
 * Created by euan on 11/04/2016.
 */
public class LocationChooser extends JPanel implements ItemListener {

    private static final List<String> icaoLocations;
    private static final Map<String,String> icaoPrefixToIsoCode;
    static{
        Map<String, String> prefixes = new HashMap<>();
        prefixes.put("EG","gb");
        prefixes.put("EB","be");
        icaoPrefixToIsoCode = Collections.unmodifiableMap(prefixes);

        List<String> locs = new ArrayList<>();
        locs.add("");
        icaoLocations = Collections.unmodifiableList(locs);
    }
    private GUIWeatherDataViewer parent;
    public LocationChooser(GUIWeatherDataViewer parent) {
        this.parent = parent;
        JComboBox<String> selector = new JComboBox<>();
        selector.addItemListener(this);
        selector.addItem("EBAM");
        selector.addItem("EBAR");
        selector.addItem("EGAA");
        selector.addItem("EGAB");
        ListCellRenderer<String> renderer = new LocationRenderer();
        selector.setRenderer(renderer);
        add(selector);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED){
            parent.setReportLocation((String) e.getItem());
        }
    }

    private class LocationRenderer implements ListCellRenderer<String> {
        @Override
        public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = new JLabel(value);
            String isoCode = icaoPrefixToIsoCode.get(value.substring(0,2));
            try {
                if(isoCode != null){
                    label.setIcon(new ImageIcon(new URL("file:D:/euan/Downloads/flag-icon-css-master/flag-icon-css-master/flags/4x3/"+isoCode+".svg.png")));
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            label.setBorder(new EmptyBorder(5,5,5,5));
            return label;
        }
    }
}
