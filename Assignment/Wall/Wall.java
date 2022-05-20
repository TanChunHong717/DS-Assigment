package Wall;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public final class Wall {
    public static int weakPoint(List<List<Integer>> lists){
        HashMap<Integer, Integer> edges = new HashMap<>();
        for(int i = 0; i < lists.size(); i++)
            for(int j = 0; j < lists.get(i).size(); j++)
                edges.put(lists.get(i).get(j), edges.getOrDefault(lists.get(i).get(j), 0) + 1);

        int max = Integer.MIN_VALUE;
        int position = -1;
        Iterator<Integer> iter = edges.keySet().iterator();
        while(iter.hasNext()){
            int key = iter.next();
            if(edges.get(key) > max){
                position = key;
                max = edges.get(key);
            }
        }

        return position;
    }
}
