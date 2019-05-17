

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Graph {

  private int vertices;
  private LinkedList<Edge>[] adjacencylist;
  private Node[] distance;

  Graph(int vertices) {
    this.vertices = vertices;
    adjacencylist = new LinkedList[vertices + 1];
    // initialize adjacency lists for all the vertices
    for (int i = 1; i < vertices + 1; i++) {
      adjacencylist[i] = new LinkedList<>();
    }
    
    // initialize distance array
    distance = new Node[vertices + 1];
  }

  public void addEdge(int source, int destination, int weight) {
    Edge edge = new Edge(source, destination, weight);
    adjacencylist[source].add(edge);      // adding only 1 edge since this graph is directed
  }

  public void dijkstra(int sourceVertex) {
    boolean[] visited = new boolean[vertices+1];

    //create heap node for all the vertices
    for (int i = 0; i < visited.length; i++) {
      // initial distance to each vertex = infinity
      distance[i] = new Node(i, Integer.MAX_VALUE);
    }
    
    distance[sourceVertex].setDistance(0); // distance of source vertex = 0

    //add all the vertices to the MinHeap
    MinHeap minHeap = new MinHeap(distance);

    //while minHeap is not empty
    while (!minHeap.isEmpty()) {
      //extract the min
      Node u = minHeap.delete_min();

      //extracted vertex
      visited[u.getVertex()] = true;
      
      if (u.getVertex() == 0) {
        // our vertices start from 1, so we ignore 0
        continue;
      }

      // now visit all adjacent vertices
      LinkedList<Edge> edges = adjacencylist[u.getVertex()];
      for (Edge e: edges) {
        int destination = e.getDestination();
        if (visited[destination] == false) {
          int currDist = distance[destination].getDistance();
          int newDist  = distance[u.getVertex()].getDistance() + e.getWeight();
          if (currDist > newDist) {
            // we found a shorter path
            distance[destination].setDistance(newDist);
            minHeap.decrease_key(destination, distance[destination].getDistance());
          }
        }
      }
    }
    
    printDijkstra(distance, sourceVertex);
  }
  
  public void printDijkstra(Node[] resultSet, int sourceVertex) {
    System.out.println("Dijkstra Algorithm: (Adjacency List + Min minHeap)");
    for (int i = 1; i < vertices + 1; i++) {
      System.out.println(
              "Source Vertex: " + sourceVertex + " to vertex " + i + " distance: " + resultSet[i].getDistance());
    }
  }

  public void printAdjacencyMatrix() {
    for (int i = 1; i < adjacencylist.length; i++) {
      LinkedList<Edge> edges = adjacencylist[i];
      System.out.print("vertex " + i + "=>");
      for (Edge e : edges) {
        System.out.print(e + " ");
      }
      System.out.println("");
    }
  }

  public static void main(String[] args) {
    // Finds file, change path here to change
    File inputGraph = new File("sssp_graph1.txt");
    try {
      Scanner input = new Scanner(inputGraph);
      int numOfVertices = input.nextInt();
      input.nextLine();
      Graph graph = new Graph(numOfVertices);
      while (input.hasNextLine()) {
        int first = input.nextInt();
        int sec = input.nextInt();
        int weight = input.nextInt();
        input.nextLine();
        graph.addEdge(first, sec, weight);
      }
      input.close();
      
      System.out.println("*** input graph is: *****");
      graph.printAdjacencyMatrix();
      System.out.println("*************************");
      
      System.out.println("Doing Dijkstra from source = 1");
      // Vertex 1 is considered as the source
      graph.dijkstra(1);
      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.out.println("File not available");
    }
    System.out.println("Done");
  }
}
