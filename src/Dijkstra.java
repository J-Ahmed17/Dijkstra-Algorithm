import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {
    static class Edge {
        int src, dst, weight;

        public Edge(int src, int dst, int weight) {
            this.src = src;
            this.dst = dst;
            this.weight = weight;
        }
    }

    public static void dijkstras(ArrayList<Edge>[] graph, int startVertex, int destVertex) {
        int numVertices = graph.length;
        int[] dist = new int[numVertices];

        int[] prev = new int[numVertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[startVertex] = 0;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> dist[a] - dist[b]);
        minHeap.offer(startVertex);

        while (!minHeap.isEmpty()) {
            int currentVertex = minHeap.poll();

            for (Edge edge : graph[currentVertex]) {
                int neighbor = edge.dst;
                int newDist = dist[currentVertex] + edge.weight;

                if (newDist < dist[neighbor]) {
                    dist[neighbor] = newDist;
                    prev[neighbor] = currentVertex;
                    minHeap.offer(neighbor);
                }
            }
        }

        ArrayList<Integer> path = new ArrayList<>();
        int current = destVertex;
        while (current != -1) {
            path.add(current);
            current = prev[current];
        }
        int pathSize = path.size();

        System.out.println("Shortest path from vertex " + startVertex + " to vertex " + destVertex + ":");
        for (int i = pathSize - 1; i >= 0; i--) {
            System.out.print(path.get(i));
            if (i > 0) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        boolean flag = false; // Change the type to boolean
        System.out.println("Create Your Graph : ");
        System.out.println("Enter number of vertices: ");
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        ArrayList<Edge>[] graph = new ArrayList[v];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        // Prompt the user to enter the number of edges
        System.out.print("Enter the number of edges: ");
        int numOfEdges = sc.nextInt();

        // Prompt the user to enter the edges and weights
        System.out.println("Enter the edges in the format 'source destination weight':");
        for (int i = 0; i < numOfEdges; i++) {
            int src = sc.nextInt();
            int dst = sc.nextInt();
            int weight = sc.nextInt();
            if (weight < 0) {
                flag = true; // Set the flag to true if there is a negative edge
                System.out.println("There is a negative edge ");
                break;
            }
            graph[src].add(new Edge(src, dst, weight));
        }

        // Prompt the user to enter the source and destination vertices
        System.out.print("Enter the source vertex: ");
        int sourceVertex = sc.nextInt();
        System.out.print("Enter the destination vertex: ");
        int destVertex = sc.nextInt();

        if (!flag) { // Check if there is no negative edge
            dijkstras(graph, sourceVertex, destVertex);
        }
    }
}