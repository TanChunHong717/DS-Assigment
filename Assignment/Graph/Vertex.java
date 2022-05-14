package Graph;
import java.util.LinkedList;

class Vertex<T>{
    /**
     * No need to maintain the name of vertex since you must 
     * got the name before when you got a vertex instance.
     */

    //The edges point to different vertex
    LinkedList<Edge<T>> edges = new LinkedList<>(); 
}