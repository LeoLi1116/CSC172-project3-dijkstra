Chunhao Li
cli79@u.rochester.edu

My program works perfectly fine for all situations besides running -meridianmap for nys.txt, the file is
too large for the program to hadle it in reasonable time. 

Arguments list for my program: [-show] [-merdianmap] [-directions inter1 inter2]

For arguments -show should always be the second argument, 
for example "map.txt -meridanmap" and "map.txt -show -meridianmap" works, 
but "map.txt -meridianmap -show" or "map.txt -directions i1 i2 -show "does not work. 
For -directions, the program will the path from last to first.


I got total 5 files, the main file is Project_3.java.
Map.java is for the gui part.
Road.java is the class file for edges.
Intersection.java is the class file for vertex. 
Graph.java is the class file for graph. 

for graph class, I store intersections with its connected road in hash table, with intersections as the key,
and a array list of roads as the value. 

the running time for my Dijkstra algorithm is O(V^2) because for each interscetion it need run a loop for all road
it's connect, for the worst case, it need to run V time because it connect to all other vertex. 


the time complexity for my Kruskal's minimum spanning tree algorithm is O(E^(V!)), because for each intersection,
it needs to go through the Arraylist of all intersection, but the size of the Arraylist for all intersection will
reduce by one each time. 

the time complexity for ploting the map is O(n) because it just go through the ArrayList of roads, and determine
the color of the road by checking a string value named color in the road class.


