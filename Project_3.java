//Chunhao Li
//cli79@u.rochester.edu

import javax.swing.*;
import java.io.File;
import java.util.*;

public class Project_3{
    static double minX = Double.POSITIVE_INFINITY;
    static double minY = Double.POSITIVE_INFINITY;
    static double maxX = Double.NEGATIVE_INFINITY;
    static double maxY = Double.NEGATIVE_INFINITY;


    public static double getDistance(Intersection a, Intersection b){
        double x1 = a.longitude;
        double y1 = a.latitude;
        double x2 = b.longitude;
        double y2 = b.latitude;
        double diffX = (x1 - x2) *53.06;
        double diffY = (y1 - y2) *68.99;
        return  Math.sqrt(Math.pow((diffX),2)+Math.pow((diffY),2));


    }


    public static void readLine(String line, Hashtable <String,Intersection> intersections, ArrayList<Road> roads){



        Scanner scan = new Scanner(line);
        String type = scan.next();
        if (type.compareTo("i")==0){
            String name = scan.next();
            double latitude = scan.nextDouble();
            double longitude = scan.nextDouble();

            Intersection intersection = new Intersection();
            intersection.setIntersection(name,longitude,latitude);
            intersections.put(name,intersection);

            if (intersection.longitude < minX){
                minX = intersection.longitude;
            }
            if (intersection.latitude < minY){
                minY = intersection.latitude;
            }
            if (intersection.longitude > maxX){
                maxX = intersection.longitude;
            }
            if (intersection.latitude > maxY){
                maxY = intersection.latitude;
            }

        }
        else if (type.compareTo("r")==0){
              String name = scan.next();
              String inter1name = scan.next();
              String inter2name = scan.next();
              Intersection inter1 = intersections.get(inter1name);
              Intersection inter2 = intersections.get(inter2name);
              Road road = new Road();
              road.setRoad(name,inter1,inter2,getDistance(inter1,inter2));
              roads.add(road);
        }




    }

    public static void drawMap(ArrayList<Road> roads, ArrayList<Double> bounds, ArrayList<Road> shortest){
        

        JFrame map  = new JFrame();
        map.setSize(1000,1000);
        map.setTitle("Map");
        map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Map thisMap = new Map();
        thisMap.roads = roads;
        thisMap.bounds = bounds;
        thisMap.shortest = shortest;


        map.getContentPane().add(thisMap);
        map.setVisible(true);

    }



    public static void main(String[] args) {
        String fileName = args[0];
        File infile = new File(fileName);


        Graph map1 = new Graph();
        Hashtable<String, Intersection> intersections = new Hashtable<> ();
        ArrayList<Road> roads = new ArrayList<>();
        
        ArrayList<Double> bounds = new ArrayList<>();


        try {
            Scanner mapReader = new Scanner(infile);
            while (mapReader.hasNextLine()) {
                String line  = mapReader.nextLine();
                readLine(line,intersections,roads);
            }
            bounds.add(minX);
            bounds.add(minY);
            bounds.add(maxX);
            bounds.add(maxY);
        }
        catch (Exception a){
            a.printStackTrace();
        }






        map1.setGraph(roads);

        ArrayList<Road> shortestPath = new ArrayList<>();

        if (Arrays.asList(args).contains("-directions")) {
            Intersection start = intersections.get(args[args.length-2]);
            Intersection end = intersections.get(args[args.length-1]);
            shortestPath = map1.shortestPath(start, end);
            System.out.println("Total distance: " + end.shortest + " miles");
        }


        if (Arrays.asList(args).contains("-meridianmap")) {
           shortestPath = map1.MST(roads);
        }

        if (!shortestPath.isEmpty()) {
            for (Road road : shortestPath) {
                System.out.print(road.roadID  + " ");
            }
            System.out.println();
        }
        else {
            System.out.println("No path found");
        }


        if (Arrays.asList(args).contains("-show")) {
            drawMap(roads, bounds, shortestPath);
        }








    }
}
