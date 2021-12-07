import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Result {
    boolean boardComplete;
    int score;

    Result(boolean a, int b){
        this.boardComplete = a;
        this.score = b;
    }
}

class Board {
    int[][] grid;
    int markedRow[];
    int markedColumn[];
    boolean[][] marked;
    boolean complete;

    Board(List<Integer> input) {
        markedRow = new int[5];
        markedColumn = new int[5];
        marked = new boolean[5][5];
        grid = new int[5][5];
        complete = false;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                grid[i][j] = input.get(i*5 + j);
            }
        }
    }

    public Result mark(int num) {
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(grid[i][j] == num) {
                    marked[i][j] = true;
                    markedRow[i]++;
                    markedColumn[j]++;
                    if(markedRow[i]==5 || markedColumn[j]==5){
                        complete = true;
                        return new Result(true, calculateScore(num));
                    }
                }
            }
        }

        return new Result(false, 0);
    }

    private int calculateScore(int num){
        int sum = 0;
        for(int i=0;i<5;i++) {
            for (int j = 0; j < 5; j++) {
                if(marked[i][j]==false) {
                    sum+=grid[i][j];
                }
            }
        }
        return sum*num;
    }
}

public class Day4 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] input = null;

        if(scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] words = line.split(",");
            input = new int[words.length];
            for(int i=0;i<input.length;i++){
                input[i] = Integer.valueOf(words[i]);
            }
        }

        if(scan.hasNextLine()) {
            scan.nextLine();
        }

        List<Board> boards = new LinkedList<>();
        while(scan.hasNextLine()) {
            List<Integer> list = new LinkedList<>();
            for(int i=0;i<25;i++){
                list.add(scan.nextInt());
            }
            boards.add(new Board(list));
        }

        System.out.println("Answer Part 1: " + findScore(input, boards));
    }

    public static int findScore(int[] input, List<Board> boards) {
//        for(int i=0;i<input.length;i++){
//            for(int j=0;j<boards.size();j++){
//                Result r = boards.get(j).mark(input[i]);
//                if(r.boardComplete) {
//                    return r.score;
//                }
//            }
//        }

        int boardsWon = 0;
        int indexSave = -1;
        for(int i=0;i<input.length;i++){
            if(boardsWon == boards.size()-1){
                indexSave = i;
                break;
            }
            for(int j=0;j<boards.size();j++){
                if(!boards.get(j).complete) {
                    Result r = boards.get(j).mark(input[i]);
                    if(r.boardComplete)
                        boardsWon++;
                }
            }
        }

        int boardNotCompleteIndex = -1;
        for(int i=0;i<input.length;i++){
            if(boards.get(i).complete == false) {
                boardNotCompleteIndex = i;
                break;
            }
        }

        for(int i=indexSave;i<input.length;i++){
            Result r = boards.get(boardNotCompleteIndex).mark(input[i]);
            if(r.boardComplete){
                return r.score;
            }
        }

        return 0;
    }
}
