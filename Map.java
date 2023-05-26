//Chunhao Li
//cli79@u.rochester.edu

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Map extends JComponent {

    ArrayList<Road> roads = new ArrayList<>();
    ArrayList<Double> bounds = new ArrayList<>();
    ArrayList<Road> shortest = new ArrayList<>();




    public void paint (Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        double x1;
        double y1;
        double x2;
        double y2;

        int coefficientX=getHeight();
        int coefficientY=getWidth();

        g2.translate(0,coefficientY);
        g2.scale(1,-1);
        g2.setStroke(new BasicStroke(2f));







        for (Road road: roads) {

            if (road.color.compareTo("red")==0){
                g2.setColor(Color.RED);
                g2.setStroke(new BasicStroke(2f));

            }

            else {
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(1f));
            }

            x1 = road.intersection1.longitude;
            y1 = road.intersection1.latitude;
            x2 = road.intersection2.longitude;
            y2 = road.intersection2.latitude;

            x1 = (x1 - bounds.get(0))*(coefficientX/(bounds.get(2)-bounds.get(0)));
            y1 = (y1 - bounds.get(1))*(coefficientY/(bounds.get(3)-bounds.get(1)));
            x2 = ( x2 - bounds.get(0))*(coefficientX/(bounds.get(2)-bounds.get(0)));
            y2 = (y2 - bounds.get(1))*(coefficientY/(bounds.get(3)-bounds.get(1)));


            g2.draw(new Line2D.Double(x1,y1,x2,y2));
        }



    }



}
