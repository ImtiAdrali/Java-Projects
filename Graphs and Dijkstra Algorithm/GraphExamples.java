package Assignment7;

import java.util.*;
import java.util.List;


/**
 * This class provides a utility to build a graph from a list of edges.
 *

 */
public class GraphExamples {
  public static TreeSet<String> labels;

  /**
   * Constructs a graph from an array of array strings.
   * <p>
   * An edge can be specified as { "SFO", "LAX" }, in which case edge is created
   * with default edge value of 1, or as { "SFO", "LAX", "337" }, in which case
   * labels.add(edge[0]);
   * the third entry should be a string that will be converted to an integral value.
   */
  public static Graph<String, Integer> graphFromEdgelist(String[][] edges, boolean directed) {
    Graph<String, Integer> g = new AdjacencyMapGraph<>(directed);

    // first pass to get sorted set of vertex labels
    labels = new TreeSet<>();
    for (String[] edge : edges) {
      labels.add(edge[0]);
      labels.add(edge[1]);
    }

    // now create vertices (in alphabetical order)
    HashMap<String, Vertex<String>> verts = new HashMap<>();
    for (String label : labels)
      verts.put(label, g.insertVertex(label));

    // now add edges to the graph
    for (String[] edge : edges) {
      Integer cost = (edge.length == 2 ? 1 : Integer.parseInt(edge[2]));
      g.insertEdge(verts.get(edge[0]), verts.get(edge[1]), cost);
    }

    return g;
  }

  /**
   * Returns the unweighted, directed graph from Figure 14.12 of DSAJ6.
   */
  public static Graph<String, Integer> Graph0() {
    String[][] edges = {
            {"A", "C"}, {"A", "D"}, {"B", "D"}, {"B", "F"}, {"C", "D"}, {"C", "E"},
            {"D", "F"}, {"E", "G"}, {"F", "G"}, {"F", "H"}, {"G", "H"}
    };
    return graphFromEdgelist(edges, true);
  }


  public static Graph<String, Integer> Graph1() {
    String[][] edges = {
            {"A", "B"}, {"A", "E"},
            {"A", "F"}, {"E", "F"},
            {"E", "I"}, {"I", "M"},
            {"M", "N"}, {"I", "N"},
            {"I", "J"}, {"I", "F"},
            {"B", "C"}, {"B", "F"},
            {"C", "D"}, {"C", "G"},
            {"D", "G"}, {"D", "H"},
            {"G", "J"}, {"G", "L"},
            {"G", "K"}, {"H", "L"},
            {"L", "P"}, {"J", "K"},
            {"N", "K"}, {"K", "O"}
    };
    return graphFromEdgelist(edges, false);
  }

  public static Graph<String, Integer> Graph2() {
    String[][] edges = {
            {"V0", "V1", "2"}, {"V1", "V4", "10"},
            {"V1", "V3", "3"}, {"V0", "V3", "1"},
            {"V2", "V0", "4"}, {"V3", "V4", "2"},
            {"V3", "V6", "4"}, {"V4", "V6", "6"},
            {"V3", "V2", "2"}, {"V2", "V5", "5"},
            {"V3", "V5", "8"}, {"V5", "V6", "1"},
    };
    return graphFromEdgelist(edges, true);
  }

  public static <V, E> void main(String[] args) {
        System.out.println("Graph 0");
        System.out.println(Graph0());

        System.out.println("Graph 1");
        System.out.println(Graph1());

        System.out.println("Graph 2");
        System.out.println(Graph2());

        System.out.print("Part 2: \nDFS Traversal: ");
        DFS1(Graph1());
        System.out.println();
        System.out.println();;

        System.out.println("Part 3:");
        System.out.println("DIJKSTRA'S SP ALGORITHM: ");
        System.out.println("Vertices\t\tDistance");
        shortestPathLengths1(Graph2());
  }


    public static <V> void shortestPathLengths1(Graph<V,Integer> g) {
        Iterator<Vertex<V>> it = g.vertices().iterator();
        Vertex<V> src = it.next();
        // d.get(v) is upper bound on distance from src to v
        Map<Vertex<V>, Integer> d = new ProbeHashMap<>( );
        // map reachable v to its d value
        Map<Vertex<V>, Integer> cloud = new ProbeHashMap<>( );
        // pq will have vertices as elements, with d.get(v) as key
        SortedPriorityQueue<Integer, Vertex<V>> pq;
        pq = new SortedPriorityQueue<>();
        // maps from vertex to its pq locator
        Map<Vertex<V>, Entry<Integer,Vertex<V>>> pqTokens;
        pqTokens = new ProbeHashMap<>( );

        // for each vertex v of the graph, add an entry to the priority queue, with
        // the source having distance 0 and all others having infinite distance
        for (Vertex<V> v : g.vertices( )) {
            if (v == src)
                d.put(v,0);
            else
                d.put(v, Integer.MAX_VALUE);
            pqTokens.put(v, pq.insert(d.get(v), v)); // save entry for future updates
        }
        // now begin adding reachable vertices to the cloud
        while (!pq.isEmpty( )) {
            Entry<Integer, Vertex<V>> entry = pq.removeMin( );
            int key = entry.getKey( );
            Vertex<V> u = entry.getValue( );
            cloud.put(u, key); // this is actual distance to u
            pqTokens.remove(u); // u is no longer in pq
            for (Edge<Integer> e : g.outgoingEdges(u)) {
                Vertex<V> v = g.opposite(u,e);
                if (cloud.get(v) == null) {
                    // perform relaxation step on edge (u,v)
                    int wgt = e.getElement( );
                    if (d.get(u) + wgt < d.get(v)) { // better path to v?
                        d.put(v, d.get(u) + wgt); // update the distance
                        pq.replaceKey(pqTokens.get(v), d.get(v)); // update the pq entry
                    }
                }
            }
        }


        Iterator<Vertex<V>> itk = cloud.keySet().iterator();
        Iterator<Integer> itv = cloud.values().iterator();
        while (itk.hasNext() && itv.hasNext()) {

            System.out.println("\t" + itk.next().getElement() + "\t\t\t\t" + itv.next());
        }


    }






  public static <V, E> void DFS1(Graph<String, Integer> g) {
    List<Vertex<String>> visited = new ArrayList<>();
    Stack<Vertex<String>> stack = new Stack<>();
    Iterator<Vertex<String>> it = g.vertices().iterator();
    stack.add(it.next());
    //visited.add(it.next());
    while (!stack.isEmpty()) {
      Vertex<String> element = stack.pop();
      if (!visited.contains(element)) {
        System.out.print(element.getElement() + " ");
        visited.add(element);
      }
      if ("O".equals(element.getElement())) {
        break;
      }

      Iterator<Edge<Integer>> it2 = g.outgoingEdges(element).iterator();
      while (it2.hasNext()) {
        Vertex<String> n = g.opposite(element, it2.next());
        if (n != null && !visited.contains(n)) {
          stack.add(n);
        }
      }
    }

  }


  public static <V, E> void bfs(Graph<String, Integer> g) {

    Queue<Vertex<String>> myQ = new LinkedList<>();
    List<Vertex<String>> visited = new ArrayList<>();
    Iterator<Vertex<String>> it = g.vertices().iterator();
    myQ.add(it.next());
    while (myQ.size() != 0) {
      Vertex<String> first = myQ.remove();
      System.out.println(first.getElement());
      Iterator<Edge<Integer>> it2 = g.outgoingEdges(first).iterator();
      while (it2.hasNext()) {
        Vertex<String> element = g.opposite(first, it2.next());
        if (!visited.contains(element)) {
          myQ.add(element);
          visited.add(element);
        }
      }
    }
  }




    }