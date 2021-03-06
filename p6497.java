import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class p6497 {
    
    static int parent[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stz.nextToken());
        int m = Integer.parseInt(stz.nextToken());
        StringBuilder sb = new StringBuilder();
        
        while(n != 0 || m != 0) {
            int total = 0;
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            for(int i = 0; i < m; i++) {
                stz = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(stz.nextToken());
                int b = Integer.parseInt(stz.nextToken());
                int w = Integer.parseInt(stz.nextToken());
                total += w;
                pq.offer(new Edge(a, b, w));
            }
            parent = new int[n];
            for(int i = 0; i < n; i++)
                parent[i] = i;
    
            int weight = 0;
            while(!pq.isEmpty()) {
                Edge now = pq.poll();
        
                if(find(now.v1) != find(now.v2)) {
                    union(now.v1, now.v2);
                    weight += now.weight;
                }
            }
    
            sb.append((total - weight) + "\n");
            stz = new StringTokenizer(br.readLine());
            n = Integer.parseInt(stz.nextToken());
            m = Integer.parseInt(stz.nextToken());
        }
    
        System.out.println(sb.toString());
    }
    
    public static void union(int n1, int n2) {
        int p1 = find(n1);
        int p2 = find(n2);
        
        if(p1 > p2)
            parent[p1] = p2;
        else
            parent[p2] = p1;
    }
    
    public static int find(int n) {
        if(parent[n] == n)
            return n;
        
        return parent[n] = find(parent[n]);
    }
    
    static class Edge implements Comparable<Edge> {
        int v1, v2, weight;
        
        Edge(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }
        
        public int compareTo(Edge e) {
            return weight - e.weight;
        }
    }
}
