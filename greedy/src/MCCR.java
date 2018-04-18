import java.util.HashSet;
import java.util.Iterator;

public class MCCR {
        public static int MCCR(EdgeWeightedGraph G) {
            HashSet<Integer> inqueue = new HashSet<>();
            IndexPQ indexPQ = new IndexPQ(G.numberOfV());
            Iterator<Integer> vertices = G.vertices.iterator();
            int firstv = vertices.next();
            indexPQ.insert(firstv, 0);
            inqueue.add(firstv);
            for (Iterator<Integer> it = vertices; it.hasNext(); ) {
                int i = it.next();
                indexPQ.insert(i, Integer.MAX_VALUE);
                inqueue.add(firstv);
            }

            while(!indexPQ.isEmpty()){
                int u = indexPQ.delMin();
                s.add(u);
                for(Edge e: G.edges(u)){
                    int vertex = e.other(u);
                    if(inqueue.contains(vertex)){
                        
                    }
                }
            }
            return 0;
        }

    }

