import java.util.*;

public class Day13 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<String> l = new LinkedList<>();

        HashSet<Pair> set = new HashSet<>();
        while (scan.hasNextLine()) {
            String s = scan.nextLine();
            if (!s.isEmpty()) {
                String[] coordinates = s.split(",");
                int x = Integer.valueOf(coordinates[0]);
                int y = Integer.valueOf(coordinates[1]);
                set.add(new Pair(x, y));
            } else {
                break;
            }
        }

        List<String> instructions = new LinkedList<>();
        while (scan.hasNextLine()) {
            instructions.add(scan.nextLine());
        }

        System.out.println("Answer Part 1: " + fold(set, instructions));
    }

    public static int fold(HashSet<Pair> map, List<String> instructions) {
        for (String instruction : instructions) {
            String[] split = instruction.split("\\s+");
            String[] directions = split[2].split("=");
            String dir = directions[0];
            int dist = Integer.valueOf(directions[1]);

            Iterator<Pair> iter = map.iterator();
            List<Pair> toAdd = new LinkedList<>();
            while (iter.hasNext()) {
                Pair p = iter.next();
                if (dir.equals("y")) {
                    if (p.y > dist) {
                        iter.remove();
                        int dy = p.y - dist;
                        int newy = dist - dy;
                        toAdd.add(new Pair(p.x, newy));
                    }
                } else {
                    if (p.x > dist) {
                        iter.remove();
                        int dx = p.x - dist;
                        int newx = dist - dx;
                        toAdd.add(new Pair(newx, p.y));
                    }
                }
            }
            map.addAll(toAdd);
        }
        printMap(map);
        return map.size();
    }

    public static void printMap(HashSet<Pair> map) {
        int xsize = Integer.MIN_VALUE;
        int ysize = Integer.MIN_VALUE;
        Iterator<Pair> iter = map.iterator();
        while(iter.hasNext()) {
            Pair p = iter.next();
            xsize = Math.max(xsize, p.x);
            ysize = Math.max(ysize, p.y);
        }

        xsize++;
        ysize++;
        char[][] prettyprint = new char[ysize][xsize];
        for(int i=0;i<ysize;i++){
            for(int j=0;j<xsize;j++){
                prettyprint[i][j] = '.';
            }
        }
        iter = map.iterator();
        while(iter.hasNext()) {
            Pair p = iter.next();
            prettyprint[p.y][p.x] = '#';
        }

        for(int i=0;i<ysize;i++){
            System.out.println(Arrays.toString(prettyprint[i]));
        }
    }

}
