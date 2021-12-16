import java.util.*;

public class Day12 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Map<String, List<String>> graph = new HashMap<>();
        while (scan.hasNextLine()) {
            String l = scan.nextLine();
            String[] words = l.split("-");
            List<String> adj1 = graph.getOrDefault(words[0], new LinkedList<>());
            adj1.add(words[1]);
            graph.put(words[0], adj1);

            List<String> adj2 = graph.getOrDefault(words[1], new LinkedList<>());
            adj2.add(words[0]);
            graph.put(words[1], adj2);
        }

        System.out.println("Answer : " + countPath2(graph));
    }

    public static int countPath1(Map<String, List<String>> graph) {
        HashSet<String> smallCaveVisited = new HashSet<>();
        int ans = 0;
        for(String neighbour:graph.get("start")) {
            ans += dfs1(graph, neighbour, smallCaveVisited);
        }
        return ans;
    }

    public static int countPath2(Map<String, List<String>> graph) {
        HashMap<String, Integer> smallCaveVisited = new HashMap<>();
        int ans = 0;
        for(String neighbour:graph.get("start")) {
            ans += dfs2(graph, neighbour, smallCaveVisited, false);
        }
        return ans;
    }

    private static int dfs1(Map<String, List<String>> graph, String node, HashSet<String> smallCaveVisited) {
        if (node.equals("end")) {
            return 1;
        }

        if (node.equals("start")) {
            return 0;
        }

        int ret = 0;

        // add to hashset if small cave
        if (isSmallCave(node)) {
            if (smallCaveVisited.contains(node)) {
                return 0;
            } else {
                smallCaveVisited.add(node);
            }
        }

        for (String neighbour : graph.get(node)) {
            ret += dfs1(graph, neighbour, smallCaveVisited);
        }

        // backtrack, remove from hashset if small cave
        if (isSmallCave(node)) {
           smallCaveVisited.remove(node);
        }

        return ret;
    }

    private static int dfs2(Map<String, List<String>> graph, String node, HashMap<String, Integer> smallCaveVisited, boolean smallCaveVisitedTwice) {
        if (node.equals("end")) {
            return 1;
        }

        if (node.equals("start")) {
            return 0;
        }

        int ret = 0;
        boolean val = false;

        // add to hashmap if small cave
        if (isSmallCave(node)) {
            if (smallCaveVisited.containsKey(node)) {
                int visitedCount = smallCaveVisited.get(node);
                if(visitedCount == 1 && smallCaveVisitedTwice == false) {
                    smallCaveVisited.put(node, visitedCount + 1);
                    val = true;
                } else if(visitedCount == 1 && smallCaveVisitedTwice == true) {
                    return 0;
                } else if(visitedCount == 2 && smallCaveVisitedTwice == true) {
                    return 0;
                }
            } else {
                smallCaveVisited.put(node, 1);
            }
        }

        for (String neighbour : graph.get(node)) {
            ret += dfs2(graph, neighbour, smallCaveVisited, val || smallCaveVisitedTwice);
        }

        // backtrack, remove from hashmap if small cave
        if (isSmallCave(node)) {
           if(smallCaveVisited.containsKey(node)) {
               if(smallCaveVisited.get(node) == 1) {
                   smallCaveVisited.remove(node);
               } else {
                   smallCaveVisited.put(node, smallCaveVisited.get(node) - 1);
               }
           }
        }

        return ret;
    }

    private static boolean isSmallCave(String node) {
        return (node!=null &&
                !node.isEmpty() &&
                node.equals(node.toLowerCase()));
    }
}
