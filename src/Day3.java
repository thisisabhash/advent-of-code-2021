import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Day3 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<String> input = new LinkedList<>();

        while(scan.hasNextLine()) {
            input.add(scan.nextLine());
        }

        System.out.println("Answer Part 1: " + calculatePower(input));
        System.out.println("Answer Part 2: " + calculateLifeSupport(input));
    }

    public static int calculatePower(List<String> input) {
        int[] gamma = new int[input.get(0).length()];
        for(int i=0;i<input.size();i++){
            for(int j=0;j<input.get(i).length();j++){
                gamma[j] += Integer.valueOf(input.get(i).charAt(j))- '0';
            }
        }

        int gammaRate = 0;
        int epsilonRate = 0;
        int mul = 1;
        for(int i=gamma.length-1;i>=0;i--){
            gamma[i] = gamma[i] > input.size()/2 ? 1 : 0;
            gammaRate += gamma[i] * mul;
            epsilonRate += (1-gamma[i]) * mul;
            mul *= 2;
        }

        //System.out.println("Gamma rate: " + gammaRate);
        //System.out.println("Epsilon rate: " + epsilonRate);
        return gammaRate*epsilonRate;
    }

    public static int calculateLifeSupport(List<String> input) {
        List<String> o2List = new LinkedList<>(input);
        List<String> co2List = new LinkedList<>(input);

        for(int i=0;i<input.get(0).length();i++){
            if(o2List.size()>1){
                int[] o2CountOnes = new int[o2List.get(0).length()];
                for(int j=0;j<o2List.size();j++){
                    for(int k=0;k<o2List.get(j).length();k++){
                        o2CountOnes[k] += Integer.valueOf(o2List.get(j).charAt(k))- '0';
                    }
                }
                for(int j=0;j<o2CountOnes.length;j++){
                    o2CountOnes[j] = o2CountOnes[j] >= (o2List.size() - o2CountOnes[j]) ? 1 : 0;
                }

                Iterator<String> iter = o2List.iterator();
                while(iter.hasNext()){
                    if(iter.next().charAt(i)!='0'+o2CountOnes[i]) {
                        iter.remove();
                    }
                }
            }

            if(co2List.size()>1){
                int[] co2CountOnes = new int[co2List.get(0).length()];
                for(int j=0;j<co2List.size();j++){
                    for(int k=0;k<co2List.get(j).length();k++){
                        co2CountOnes[k] += Integer.valueOf(co2List.get(j).charAt(k))- '0';
                    }
                }
                for(int j=0;j<co2CountOnes.length;j++){
                    co2CountOnes[j] = co2CountOnes[j] >= (co2List.size() - co2CountOnes[j]) ? 0 : 1;
                }
                Iterator<String> iter = co2List.iterator();
                while(iter.hasNext()){
                    if(iter.next().charAt(i)!='0'+co2CountOnes[i]) {
                        iter.remove();
                    }
                }
            }

            //System.out.println("Iter: " + i);
            //System.out.println("O2 List size: " + o2List.size());
            //System.out.println("CO2 List size: " + co2List.size());
        }

        //System.out.println("O2 : " + o2List.get(0));
        //System.out.println("CO2 : " + co2List.get(0));

        int o2rating = 0;
        int co2rating = 0;
        int mul = 1;
        for(int i=o2List.get(0).length()-1;i>=0;i--){
            o2rating += (o2List.get(0).charAt(i) - '0') * mul;
            co2rating += (co2List.get(0).charAt(i) - '0') * mul;
            mul *= 2;
        }

        //System.out.println("O2 rate: " + o2rating);
        //System.out.println("CO2 rate: " + co2rating);
        return o2rating*co2rating;
    }

}
