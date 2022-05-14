package Graph;
import java.util.*;

public class Graph<T> {
    //HashMap consist all the vertices in graph
    private HashMap<T, Vertex<T>> vertices = new HashMap<>();
    private LinkedList<T> HamiltonianCycle = null;

    /**
     * Add an vertex in graph. No two vertex can have same name
     * @param name
     */
    public void addVertex(T name){
        if(!vertices.containsKey(name))
            vertices.put(name, new Vertex<T>());
    }

    /**
     * Create a directed edge between vertices. If the vertex not exist,
     * automatically create the vertexThis method do not create undirected 
     * edge to avoid accidentally creating of parallel edge. To create 
     * undirected edge, you need to call this method twice while changing 
     * the from and pointTo.
     * @param from
     * @param pointTo
     * @param weight
     */
    public void addEdge(T from, T pointTo, String type){
        if(!vertices.containsKey(from))
            addVertex(from);
        if(!vertices.containsKey(pointTo))
            addVertex(pointTo);

        vertices.get(from).edges.add(new Edge<T>(pointTo, type));
    }

    /**
     * Create directed edges between vertex from to all the vertices in pointTo.
     * @param from
     * @param pointTo
     * @param type
     */
    public void addEdge(T from, T[] pointTo, String type){
        for(int i = 0; i < pointTo.length; i++)
            addEdge(from, pointTo[i], type);
    }

    /**
     * Return the vertex with the given name
     * @param name
     * @return the vertex with given name
     */
    public Vertex<T> getVertex(T name){
        return vertices.get(name);
    }
    
    /**
     * Dijkstra Algorithm to find all shIterator<T> iter = vertices.keySet().iterator();
        while(iter.hasNext()){
            T temp = iter.next();
            found.put(temp, false);
            distance.put(temp, Double.MAX_VALUE);
        }
        distance.put(start, 0.0);
        ArrayList<T> temp = new ArrayList<>();
        temp.add(start);
        res.add(temp);ortest path from vertex start to vertex end
     * @param start
     * @param end
     * @return a arraylist of arraylist that contain a shortest path like this:
     *         [[0, 1, 6], [0, 5, 6]]
     * @throws NoSuchElementException
     */
    public ArrayList<ArrayList<T>> dijkstra(T start, T end, int coordination, int intelligient, int agility) throws NoSuchElementException{
        if(!vertices.containsKey(start) || !vertices.containsKey(end))
            throw new NoSuchElementException();
        
        ArrayList<ArrayList<T>> res = new ArrayList<>();
        HashMap<T, Boolean> found = new HashMap<>();
        HashMap<T, Double> distance = new HashMap<>();
        Iterator<T> iter = vertices.keySet().iterator();
        while(iter.hasNext()){
            T temp = iter.next();
            found.put(temp, false);
            distance.put(temp, Double.MAX_VALUE);
        }
        distance.put(start, 0.0);
        ArrayList<T> temp = new ArrayList<>();
        temp.add(start);
        res.add(temp);
        T last = start;

        while(!last.equals(end)){
            found.put(last, true);
            LinkedList<Edge<T>> edges = vertices.get(last).edges; 
            ArrayList<T> list = null;
            for(int j = 0 ; j < res.size(); j++){
                list = res.get(j);
                if(list.get(list.size()-1) == last){
                    break;
                }
            }

            for(int i = 0; i < edges.size(); i++)
                if(distance.get(last) + edges.get(i).variaWeight(coordination, intelligient, agility) <= distance.get(edges.get(i).pointTo)){
                    ArrayList<T> newList = new ArrayList<>(list);
                    newList.add(edges.get(i).pointTo);
                    res.add(newList);          
                    distance.put(edges.get(i).pointTo, distance.get(last) + edges.get(i).variaWeight(coordination, intelligient, agility));
                }

            double min = Double.MAX_VALUE;
            iter = vertices.keySet().iterator();
            while(iter.hasNext()){
                T tempory = iter.next();
                if(!found.get(tempory) && distance.get(tempory) < min){
                    min = distance.get(tempory);
                    last = tempory;
                }
            }
        }

        for(int i = 0; i < res.size(); i++)
            if(res.get(i).get(res.get(i).size()-1) != end)
                res.remove(res.get(i--));

        return res;
    }

    /**
     * Initialize and preparing the backTracking algorithm, call the algorithm 
     * and return a Hamiltonian cycle if exist.
     * @param start
     * @return Hamiltonian cycle if exist, otherwise return null
     */
    public LinkedList<T> backtrack(T start) throws NoSuchElementException{
        if(!vertices.containsKey(start))
            throw new NoSuchElementException();
        HamiltonianCycle = null;
        LinkedList<T> cycle = new LinkedList<>();
        HashMap<T, Boolean> found = new HashMap<>();
        Iterator<T> iter = vertices.keySet().iterator();
        while(iter.hasNext())
            found.put(iter.next(), false);
        cycle.add(start);
        found.put(start, true);
        backtrackHelpher(start, cycle, found);
        return HamiltonianCycle;
    }

    /**
     * Backtracking algorithm, try to construct a hamiltonian path 
     * by adding one vertex in list at once.
     * @param start
     * @param cycle
     * @param found
     */
    private void backtrackHelpher(T start, LinkedList<T> cycle, HashMap<T, Boolean> found){
        if(HamiltonianCycle != null)
            return;
        else if(cycle.getLast() == start && cycle.size() == vertices.size()+1)
            HamiltonianCycle = new LinkedList<>(cycle);

        LinkedList<Edge<T>> edges = vertices.get(cycle.getLast()).edges;
        for(int i = 0; i < edges.size(); i++){
            if(HamiltonianCycle != null)
                break;
            if(!found.get(edges.get(i).pointTo) || (edges.get(i).pointTo == start && cycle.size() == vertices.size())){
                cycle.add(edges.get(i).pointTo);
                found.put(edges.get(i).pointTo, true);
                backtrackHelpher(start, cycle, found);
                cycle.removeLast();
                found.put(edges.get(i).pointTo, false);
            }
        }
    }
}
