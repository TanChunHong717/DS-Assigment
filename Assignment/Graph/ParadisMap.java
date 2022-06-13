package Graph;
import java.util.*;

public class ParadisMap {
    //Map of Paradis
    private Graph<Integer> graph;
    //Type for node
    public static final String titan = "Titan";
    public static final String tree = "Tree";
    public static final String building = "Building";

    /**
     * Construct a graph using given graph
     */
    public ParadisMap(){
        graph = new Graph<>();
        graph.addEdge(0, 1, building);
        graph.addEdge(0, new Integer[]{5, 7}, tree);
        graph.addEdge(1, 0, null);
        graph.addEdge(1, 2, titan);
        graph.addEdge(1, 4, tree);
        graph.addEdge(1, 6, building);
        graph.addEdge(2, new Integer[]{1, 3, 11}, building);
        graph.addEdge(3, 2, titan);
        graph.addEdge(3, 10, building);
        graph.addEdge(4, new Integer[]{1, 6, 10}, building);
        graph.addEdge(5, 0, null);
        graph.addEdge(5, 6, building);
        graph.addEdge(5, 7, tree);
        graph.addEdge(5, 12, titan);
        graph.addEdge(6, new Integer[]{1, 8}, building);
        graph.addEdge(6, new Integer[]{4, 5, 15}, tree);
        graph.addEdge(7, 0, null);
        graph.addEdge(7, new Integer[]{5, 9}, tree);
        graph.addEdge(8, new Integer[]{6, 10}, building);
        graph.addEdge(9, new Integer[]{7, 15}, tree);
        graph.addEdge(9, 12, titan);
        graph.addEdge(10, new Integer[]{3, 8}, building);
        graph.addEdge(10, 4, tree);
        graph.addEdge(10, 14, titan);
        graph.addEdge(11, 2, titan);
        graph.addEdge(11, 13, building);
        graph.addEdge(12, new Integer[]{5, 9}, tree);
        graph.addEdge(13, new Integer[]{2, 14}, titan);
        graph.addEdge(14, new Integer[]{10, 13}, building);
        graph.addEdge(14, 15, tree);
        graph.addEdge(15, 6, building);
        graph.addEdge(15, 9, tree);
        graph.addEdge(15, 14, titan);
    }

    /**
     * Find the HamiltonianCycle in graph
     * @param start
     * @return a HamiltonianCycle from vertex "start"
     */
    public LinkedList<Integer> HamiltonianCycle(int start){
        return graph.backtrack(start);
    }

    /**
     * Find the shortest path(s) to kill titan starting from vertex index 0
     * @param TitanPosition
     * @return a arraylist of arraylist that contain a shortest path like this:
     *         [[0, 1, 6], [0, 5, 6]]
     */
    public ArrayList<ArrayList<Integer>> BestPathToKillTitan(int TitanPosition){
        return graph.dijkstra(0, TitanPosition, 10, 10, 10);
    }

    /**
     * Return the length of path
     * @param path
     * @return length of path
     */
    public int timeToReach(ArrayList<Integer> path){
        return path.size()-1;
    }

    /**
     * Find the shortest path(s) to kill a moving titan starting from vertex index 0
     * Shorter path with longer time to kill titan is consider as short.
     * @param TitanPosition
     * @return a arraylist of arraylist that contain a shortest path like this:
     *         [[0, 1, 6], [0, 5, 6]]
     */
    public ArrayList<ArrayList<Integer>> BestPathToKillTitan1(int[] TitanPosition){
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        PriorityQueue<ArrayList<Integer>> priorityQueue = new PriorityQueue<>((o1, o2) -> {return Integer.compare(o1.size(), o2.size());});

        for(int i = 0; i < TitanPosition.length; i++)
            priorityQueue.addAll(BestPathToKillTitan(TitanPosition[i]));

        while(!priorityQueue.isEmpty()){
            ArrayList<Integer> path = priorityQueue.poll();
            if(time(TitanPosition, path) > 0)
                if(res.size() == 0 || res.get(res.size() - 1).size() == path.size())
                    res.add(path);
                else
                    break;
        }

        for(int i = 0; i < res.size()-1; i++){
            if(timeToKillTitan(res.get(i), TitanPosition) > timeToKillTitan(res.get(i+1), TitanPosition))
                res.remove(i--);
            else if(timeToKillTitan(res.get(i), TitanPosition) < timeToKillTitan(res.get(i+1), TitanPosition))
                res.remove(i-- + 1);
        }

        return res;
    }

    /**
     * Return the time to kill titan
     * @param path
     * @param TitanPosition
     * @return the time titan be kill
     */
    public int timeToKillTitan(ArrayList<Integer> path, int[] TitanPosition){
        if(time(TitanPosition, path) > 0)
            return Math.max(timeToReach(path), time(TitanPosition, path)-1);

        return -1;
    }

    /**
     * Return time for titan stay in the end of path
     * @param array
     * @param path
     * @return the time+1 when titan reach the end of path
     */
    private int time(int[] array, ArrayList<Integer> path){
        ArrayList<Integer> list = indexOf(array, path.get(path.size()-1));
        for(int i = 0; i < list.size(); i++){
            if(timeToReach(path) <= list.get(i)*2+1)
                return list.get(i)*2+1;
        }

        return -1;
    }

    private ArrayList<Integer> indexOf(int[] array, int element){
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 0; i < array.length; i++)
            if(array[i] == element)
                res.add(i);
        return res;
    }

    /**
     * Find the shortest path(s) to kill titan starting from vertex index 0 consider the characteristic
     * @param TitanPosition
     * @return a arraylist of arraylist that contain a shortest path like this:
     *         [[0, 1, 6], [0, 5, 6]]
     */
    public ArrayList<ArrayList<Integer>> BestPathToKillTitan2(int TitanPosition, int coordination, int intelligient, int agility){
        return graph.dijkstra(0, TitanPosition, coordination, intelligient, agility);
    }

    /**
     * Find the shortest path(s) to kill titan starting from vertex index 0 consider the characteristic
     * @param TitanPosition
     * @return a arraylist of arraylist that contain a shortest path like this:
     *         [[0, 1, 6], [0, 5, 6]]
     */
    public ArrayList<ArrayList<Integer>> realBattle(int currentPos, int TitanPosition, int coordination, int intelligient, int agility){
        return graph.dijkstra(currentPos, TitanPosition, coordination, intelligient, agility);
    }

    /**
     * Return the time require for character reach the end of path consider characteristic
     * @param path
     * @param coordination
     * @param intelligient
     * @param agility
     * @return time require for character reach the end of path
     */
    public int timeToReach(ArrayList<Integer> path, int coordination, int intelligient, int agility){
        int sum = 1;

        for(int i = 0; i < path.size()-2; i++)
            sum += graph.getEdge(path.get(i), path.get(i+1)).variaWeight(coordination, intelligient, agility);
    
        return sum;
    }
}