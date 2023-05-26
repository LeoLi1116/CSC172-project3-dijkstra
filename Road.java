//Chunhao Li
//cli79@u.rochester.edu

import java.util.Comparator;

public class Road {
    String roadID;
    Intersection intersection1;
    Intersection intersection2;
    double distance;
    String color = "black";


    public void setRoad(String a, Intersection inter1, Intersection inter2, double distance){
        setRoadID(a);
        setIntersection1(inter1);
        setIntersection2(inter2);
        setDistance(distance);

    }

    public void setRoadID(String a){this.roadID = a;}

    public void setIntersection1 (Intersection inter1){this.intersection1 = inter1;}

    public void setIntersection2 (Intersection inter2){this.intersection2 = inter2;}

    public void setDistance(double distance){this.distance = distance;}



}

class RoadComparator implements Comparator<Road> {

@Override
public int compare(Road o1, Road o2) {
        if (o1.distance < o2.distance)
        return -1;
        else if (o1.distance > o2.distance)
        return 1;
        return 0;
        }
}