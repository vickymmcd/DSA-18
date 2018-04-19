import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class MCCR {
        public static int MCCR(EdgeWeightedGraph G) {
            int total = 0;
            HashSet<Integer> inqueue = new HashSet<>();
            ArrayList<Integer> s = new ArrayList<>();
            HashMap<Integer, Integer> parents = new HashMap<>();
            IndexPQ indexPQ = new IndexPQ(G.numberOfV());
            Iterator<Integer> vertices = G.vertices.iterator();
            int firstv = vertices.next();
            indexPQ.insert(firstv, 0);
            parents.put(firstv, -1);
            inqueue.add(firstv);
            for (Iterator<Integer> it = vertices; it.hasNext(); ) {
                int i = it.next();
                indexPQ.insert(i, Integer.MAX_VALUE);
                inqueue.add(i);
            }

            while(!indexPQ.isEmpty()){
                int u = indexPQ.delMin();
                inqueue.remove(u);
                s.add(u);
                for(Edge e: G.edges(u)){
                    int vertex = e.other(u);
                    if(inqueue.contains(vertex) && e.weight() < (int)indexPQ.keys[vertex]){
                        parents.put(vertex, u);
                        indexPQ.changeKey(vertex, e.weight());
                    }
                }
            }
            for (int parent:parents.keySet()) {
                for(Edge e: G.edges(parent)){
                    if(e.other(parent) == parents.get(parent)){
                        total = total + e.weight();
                    }
                }
            }

            return total;
        }

    }

