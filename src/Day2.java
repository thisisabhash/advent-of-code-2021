import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

enum MoveType {
    FORWARD,
    DOWN,
    UP
}

class Move {
    MoveType type;
    int value;
    public static String FORWARD = "forward";
    public static String DOWN = "down";
    public static String UP = "up";

    public Move(String line) {
        String[] words = line.split("\\s+");
        if(FORWARD.equals(words[0])) {
            this.type = MoveType.FORWARD;
        } else if (DOWN.equals(words[0])) {
            this.type = MoveType.DOWN;
        } else {
            this.type = MoveType.UP;
        }
        this.value = Integer.valueOf(words[1]);
    }
}

public class Day2 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Move> input = new LinkedList<>();

        while(scan.hasNextLine()) {
            input.add(new Move(scan.nextLine()));
        }

        System.out.println("Answer Part 1: " + calculatePosition(input));
        System.out.println("Answer Part 2: " + calculatePosition2(input));
    }

    public static int calculatePosition(List<Move> input) {
        int x = 0;
        int y = 0;
        for(int i=0;i<input.size();i++){
            switch (input.get(i).type){
                case UP :
                    y -= input.get(i).value;
                    break;
                case DOWN :
                    y += input.get(i).value;
                    break;
                case FORWARD :
                    x += input.get(i).value;
                    break;
            }
        }

        return x*y;
    }

    public static int calculatePosition2(List<Move> input) {
        int x = 0;
        int y = 0;
        int aim = 0;
        for(int i=0;i<input.size();i++){
            switch (input.get(i).type){
                case UP :
                    aim -= input.get(i).value;
                    break;
                case DOWN :
                    aim += input.get(i).value;
                    break;
                case FORWARD :
                    x += input.get(i).value;
                    y += aim *input.get(i).value;
                    break;
            }
        }

        return x*y;
    }

}
