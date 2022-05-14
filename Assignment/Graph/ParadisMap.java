import java.util.*;
import Graph.*;

public class ParadisMap {
    private Graph<Integer> graph;
    public static final String titan = "Titan";
    public static final String tree = "Tree";
    public static final String building = "Building";

    /**
     * Constructor use in priority queue
     */
    private class ArrayListComparator<T> implements Comparator<ArrayList<T>>{
        @Override
        public int compare(ArrayList<T> o1, ArrayList<T> o2) {
            return Integer.compare(o1.size(), o2.size());
        }
    }

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

    public ArrayList<ArrayList<Integer>> BestPathToKillTitan(int TitanPosition, int coordination, int intelligient, int agility){
        return graph.dijkstra(0, TitanPosition, 4, 6, 7);
    }

    public ArrayList<ArrayList<Integer>> BestPathToKillTitan1(int[] TitanPosition, int coordination, int intelligient, int agility){
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        PriorityQueue<ArrayList<Integer>> priorityQueue = new PriorityQueue<>(new ArrayListComparator<>());

        for(int i = 0; i < TitanPosition.length; i++){
            ArrayList<ArrayList<Integer>> list = BestPathToKillTitan(TitanPosition[i], coordination, intelligient, agility);
            for(int j = 0; j < list.size(); j++)
                priorityQueue.add(list.get(j));
        }

        while(!priorityQueue.isEmpty()){
            ArrayList<Integer> path = priorityQueue.poll();
            int time = time(TitanPosition, path);
            if(path.size()-1 <= time)
                res.add(path);
        }

        for(int i = 1; i < res.size(); i++){
            if(res.get(i).size() > res.get(i-1).size())
                res.remove(i--);
            else if(time(TitanPosition, res.get(i)) > time(TitanPosition, res.get(i-1)))
                res.remove(i--);
        }

        return res;
    }

    public LinkedList<Integer> HamiltonianCycle(int start){
        return graph.backtrack(start);
    }

    private int time(int[] array, ArrayList<Integer> path){
        return indexOf(array, path.get(path.size()-1)) + 2;
    }

    private int indexOf(int[] array, int element){
        for(int i = 0; i < array.length; i++)
            if(array[i] == element)
                return i;
        return -1;
    }
}
