//Chunhao Li
//cli79@u.rochester.edu
import java.util.Comparator;

public class Intersection {
     String name;
     Double latitude;
     Double longitude;
     Double shortest;
     Road prve;

    public void setIntersection(String a,double x, double y){
        setName(a);
        setLongitude(x);
        setLatitude(y);
    }

    public void setLongitude(double x){
        this.longitude = x;
    }

    public void setLatitude(double y){
        this.latitude = y;
    }

    public void setName(String a){
        this.name = a;
    }






}

class IntersectionComparator implements Comparator<Intersection>{

    @Override
    public int compare(Intersection o1, Intersection o2) {
        if (o1.shortest < o2.shortest)
            return -1;
            else if (o1.shortest > o2.shortest)
            return 1;
            return 0;
    }
}