package Graph;

class Edge<T>{
    //The vertex edge point to
    T pointTo;
    //Type of pointer node
    String type;

    /**
     * Constructor use when create a weight graph
     * @param pointTo
     * @param weight
     */
    public Edge(T pointTo, String type){
        this.pointTo = pointTo;
        this.type = type;
    }

    /**
     * Determine the weight to move. Set the weight of each directed edge (i, j)
     * in the input graph to the cost of vertex j. Dijkstra’s algorithm now can 
     * work on node weight graph.
     * @param coordination
     * @param intelligient
     * @param agility
     * @return
     */
    public double variaWeight(int coordination, int intelligient, int agility){
        if(type == null)
            return 1.0;
        switch(type){
            case ParadisMap.titan:
                if(intelligient < 5)
                    return 3.0;
                else if(intelligient < 8)
                    return 2.0;
                else
                    return 1.0;
            case ParadisMap.building:
                if(coordination < 5)
                    return 3.0;
                else if(coordination < 8)
                    return 2.0;
                else
                    return 1.0;
            case ParadisMap.tree:
                if(agility < 5)
                    return 3.0;
                else if(agility < 8)
                    return 2.0;
                else
                    return 1.0;
        }
        return 1.0;
    }
}