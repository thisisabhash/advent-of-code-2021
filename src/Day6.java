import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Day6 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\\D");
        List<Integer> input = new LinkedList<>();

        while(scan.hasNextInt()) {
            input.add(scan.nextInt());
        }

        System.out.println("Answer Part 1: " + simulate(input, 256));
    }

    public static long simulate(List<Integer> input, int days) {
        HashMap<Integer, Long> map = new HashMap<>();
        for(int i=0;i<input.size();i++){
            if(!map.containsKey(input.get(i))){
                map.put(input.get(i), 1L);
            } else {
                map.put(input.get(i), map.get(input.get(i))+1);
            }
        }

        for(int i=0;i<days;i++){
            long newFish = 0;
            for(int j=0;j<=8;j++){
                if(j==0) {
                    newFish = map.getOrDefault(j, 0L);
                } else {
                    map.put(j-1, map.getOrDefault(j,0L));
                }
                map.put(j, 0L);
            }
            if(newFish>0){
                map.put(6, map.getOrDefault(6,0L)+newFish);
                map.put(8, map.getOrDefault(8, 0L)+newFish);
            }
        }

        long answer = 0;
        for(int j=0;j<=8;j++){
            answer += map.get(j);
        }

        return answer;
    }

}
