//Chunhao Li
//cli79@u.rochester.edu


import java.util.*;

public class Graph {
    Hashtable<Intersection,ArrayList<Road>> graph = new Hashtable<>();



    public void setGraph(ArrayList<Road> roads){

        for (Road road : roads) {
            //if graph already has value in it

            if (graph.isEmpty()) {
                ArrayList<Road> inter1 = new ArrayList<>();
                ArrayList<Road> inter2 = new ArrayList<>();
                inter1.add(road);
                inter2.add(road);
                graph.put(road.intersection1, inter1);
                graph.put(road.intersection2,inter2);
            }
            //if graph is empty
            else {
                //if intersection1 already connects to other intersections
                if (graph.containsKey(road.intersection1)) {
                    ArrayList<Road> update = graph.get(road.intersection1);
                    update.add(road);
                    graph.replace(road.intersection1, update);
                }
                //if intersection1 does not connect to other intersections
                else {
                    ArrayList<Road> firstValue = new ArrayList<>();
                    firstValue.add(road);
                    graph.put(road.intersection1, firstValue);
                }
                //if intersection2 already connects to other intersections
                if (graph.containsKey(road.intersection2)) {
                    ArrayList<Road> update = graph.get(road.intersection2);
                    update.add(road);
                    graph.replace(road.intersection2, update);
                }
                //if intersection2 does not connect to other intersections
                else {
                    ArrayList<Road> firstValue = new ArrayList<>();
                    firstValue.add(road);
                    graph.put(road.intersection2, firstValue);
                }
            }
        }
    }

    public ArrayList<Road> shortestPath(Intersection start, Intersection end){
        ArrayList<Road> answer = new ArrayList<>();



        Road nullRoad = new Road();
        nullRoad.setRoadID("\0");


        PriorityQueue <Intersection> unvisited = new PriorityQueue<>(new IntersectionComparator());

        Set<Intersection> setOfIntersections = graph.keySet();
        for (Intersection key: setOfIntersections) {
            key.shortest = Double.POSITIVE_INFINITY;
            key.prve = nullRoad;
        }

        start.shortest = 0.0;

        unvisited.addAll(setOfIntersections);


        shortest(unvisited);


        Intersection tempInter = end;
        Road tempRoad = tempInter.prve;





        while (tempRoad != nullRoad){
            tempRoad.color = "red";
            answer.add(tempRoad);
            if (tempInter == tempRoad.intersection1){
                tempInter = tempRoad.intersection2;
            }
            else {
                tempInter = tempRoad.intersection1;
            }
            tempRoad = tempInter.prve;
        }




        return answer;


    }

    public void shortest(PriorityQueue<Intersection> unvisited){


        while (!unvisited.isEmpty()){

            Intersection current = unvisited.poll();

            for (Road road: graph.get(current)) {
                double dist = current.shortest + road.distance;
                Intersection next;


                if (road.intersection1 == current){
                    next = road.intersection2;
                }
                else {
                    next = road.intersection1;
                }


                if (dist < next.shortest){
                    next.shortest = dist;
                    next.prve = road;
                    unvisited.remove(next);
                    unvisited.add(next);
                }


            }





        }


    }

    public ArrayList<Road> MST (ArrayList<Road> paths){
        ArrayList<Road> answer = new ArrayList<>();

        PriorityQueue<Road> roads = new PriorityQueue<>(new RoadComparator());

        roads.addAll(paths);

        Set<Intersection> setOfIntersections = graph.keySet();

       ArrayList<ArrayList<Intersection>> vertex = new ArrayList<>();

        for (Intersection key: setOfIntersections){
            ArrayList<Intersection> set = new ArrayList<>();
            set.add(key);
            vertex.add(set);
        }



        while (vertex.size() > 1 && roads.size() > 0){
            Road shortest = roads.poll();


            Intersection inter1 = shortest.intersection1;
            Intersection inter2 = shortest.intersection2;

            ArrayList<Intersection> set1 = new ArrayList<>();
            ArrayList<Intersection> set2 = new ArrayList<>();

            for (ArrayList<Intersection> set: vertex){
                if (set.contains(inter1)){
                    set1 = set;
                }
                if (set.contains(inter2)){
                    set2 = set;
                }
            }

           if (set1 != set2){
               shortest.color="red";
               answer.add(shortest);
               vertex.remove(set1);
               vertex.remove(set2);
               set1.addAll(set2);
               vertex.add(set1);
           }

        }


        return answer;
    }

}
