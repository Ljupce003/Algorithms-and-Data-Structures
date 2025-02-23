import java.util.Arrays;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

     class MiceHoles {

        //TODO: implement function
        public static int minTime(int mice[], int holes[]) {
            int totalmin=0;
            int longest_dist=0;
            int maxwalk=0;
            int minidx=0;
            Arrays.sort(mice);
            Arrays.sort(holes);
            for (int i=0;i<mice.length;i++){
                longest_dist=Math.abs(mice[i]-holes[i]);
                if(maxwalk<longest_dist)maxwalk=longest_dist;
            }
            totalmin=maxwalk;
            return totalmin;
        }

        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);

            String line = input.nextLine();
            String parts[] = line.split(" ");
            int mice[] = new int[parts.length];
            for(int i=0;i<parts.length;i++) {
                mice[i] = Integer.parseInt(parts[i]);
            }

            line = input.nextLine();
            parts = line.split(" ");
            int holes[] = new int[parts.length];
            for(int i=0;i<parts.length;i++) {
                holes[i] = Integer.parseInt(parts[i]);
            }

            System.out.println(minTime(mice, holes));
        }
    }
