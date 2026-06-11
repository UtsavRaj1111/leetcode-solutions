class Solution {
    public int assignEdgeWeights(int[][] edges) {
        // build adj graph using edges
        Map<Integer, List<Integer>> adj = new HashMap<>();
        
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            adj.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        // calculate depth
        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[adj.size()+1];
        
        q.offer(1);
        vis[1] = true;
        int depth = 0;

        while(!q.isEmpty()) {
            int N = q.size();

            while(N > 0) {
                int u = q.poll();

                for(int v : adj.computeIfAbsent(u, k -> new ArrayList<>())) {
                    if(!vis[v]) {
                        vis[v] = true;
                        q.offer(v);
                    }
                }
                N--;
            }
            // Not increasing Depth for leaf nodes
            if(q.size() > 0) {
                depth++;
            }
        }
        
        // say depth is 3
        // we have 3 spots to put 1 or 2 such that the sum of spots is odd
        // calculate number of such permutations
        int MOD = 1000000007;
        int result = (int) modPow(2, depth-1, MOD);
        return result;
    }

    // Custom Pow function which would use binary exponentiation
    // And mods the intermediate results
    long modPow(int a, int b, int mod) {
        if(b == 0) {
            return 1;
        }
        long half = modPow(a, b/2, mod);
        long result = (half * half) % mod;

        if(b % 2 == 1) {
            result = (result * a) % mod;
        }
        return result;
    }
}