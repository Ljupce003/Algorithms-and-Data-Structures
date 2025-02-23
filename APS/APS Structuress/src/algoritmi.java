import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class algoritmi {




    //BRUUTE
    public static boolean can_attack(int i1, int j1, int i2, int j2) {
        return (i1 == i2) || (j1 == j2) || (Math.abs(i1-i2) == Math.abs(j1-j2));
    }

    public static int number_of_combinations(int n) {
        int rez = 0;

        for(int i1=0;i1<n;i1++) {
            for(int j1=0;j1<n;j1++) {
                for(int i2=0;i2<n;i2++) {
                    for(int j2=0;j2<n;j2++) {
                        if(!can_attack(i1, j1, i2, j2)) {
                            rez++;
                        }
                    }
                }
            }
        }

        return rez;
    }





    //GREEEDYY


    public static void sortProfitsAndWeights(int p[], int w[]) {
        for(int i=0;i<p.length;i++) {
            for(int j=i+1;j<p.length;j++) {
                if((p[i]/(float) w[i]) < (p[j]/(float) w[j])) {
                    int tmpP = p[i];
                    int tmpW = w[i];
                    p[i] = p[j];
                    w[i] = w[j];
                    p[j] = tmpP;
                    w[j] = tmpW;
                }
            }
        }
    }

    public static float getFractKnpMaxProfit(int p[], int w[], int C) {
        sortProfitsAndWeights(p,w);
        float profit = 0;
        for(int i=0;i<p.length;i++) {
            if(C > w[i]) {
                C -= w[i];
                profit += p[i];
            } else {
                float x = C / (float) w[i];
                profit += x*p[i];
                C = 0;
                break;
            }
        }
        return profit;
    }



    //GREEEDYY 2


    public static ArrayList<ArrayList<Integer>> findTanksAndTaps(int houses[][]) {
        ArrayList<ArrayList<Integer>> rez = new ArrayList<>();

        for(int i=0;i<houses.length;i++) {
            if(houses[i][3] == 0 && houses[i][2] == -1) {
                int pom = i;
                int min_d = houses[i][1];
                int next = houses[i][0];
                while (next != -1) {
                    if(houses[pom][1] < min_d) {
                        min_d = houses[pom][1];
                    }
                    houses[pom][3] = 1;
                    pom = next;
                    next = houses[next][0];
                }
                if(pom != i) {
                    houses[pom][3] = 1;
                    ArrayList<Integer> tmp = new ArrayList<>();
                    tmp.add(i);
                    tmp.add(pom);
                    tmp.add(min_d);
                    rez.add(tmp);
                }
            }
        }

        return rez;
    }



    //DUNAMIC PROGRAMMING

    static class DP1 {

        int binomial_coefficient(int n, int m) {
            int i, j;

            int bc[][] = new int[n + 1][n + 1]; // tabela so binomni koeficienti

            for (i = 0; i <= n; i++) {
                bc[i][0] = 1;
            }

            for (j = 0; j <= n; j++) {
                bc[j][j] = 1;
            }

            for (i = 1; i <= n; i++) {
                for (j = 1; j < i; j++) {
                    bc[i][j] = bc[i - 1][j - 1] + bc[i - 1][j];
                }
            }

            return bc[n][m];
        }
    }




    //DUNAMIC PROGRAMMING 2
    static class DP2 {


        int a[][] = new int[100][100];
        int best[][] = new int[100][100];

        void maksimalen_zbir(int m, int n) {
            int i, j;


            // inicijalizacija na trivijalni reshenija
            best[0][0] = a[0][0];

            for (i = 1; i < m; i++) {
                best[i][0] = best[i - 1][0] + a[i][0]; // prva kolona
            }
            for (i = 1; i < n; i++) {
                best[0][i] = best[0][i - 1] + a[0][i]; // prva redica
            }
            for (i = 1; i < m; i++) {
                for (j = 1; j < n; j++) {
                    best[i][j] = Math.max(best[i - 1][j], best[i][j - 1]) + a[i][j];
                }
            }

        }
    }


    //DUNAMIC PROGRAMMING 3
    class DP3 {

        static int cost[][];
        static int tax[], best[];
        static int n;
        static int INFINITY = 1000000;

        static int min(int x, int y) {
            if (x < y)
                return x;
            return y;
        }
    }


    //DUNAMIC PROGRAMMING 4
    static class DP4 {

        int max(int a, int b) {
            if (a > b) {
                return a;
            }
            return b;
        }

        int DPKnapsack(int t[], int p[], int C) {
            int i, j;
            int n = t.length;
            int D[][] = new int[n + 1][C + 1];

            for (j = 0; j <= C; j++) {
                D[0][j] = 0;
            }

            for (i = 1; i <= n; i++) {
                D[i][0] = 0;
            }

            for (i = 1; i <= n; i++) {
                for (j = 1; j <= C; j++) {
                    if (t[i - 1] <= j) {
                        D[i][j] = max(p[i - 1] + D[i - 1][j - t[i - 1]], D[i - 1][j]);
                    } else {
                        D[i][j] = D[i - 1][j];
                    }
                }
            }

            return D[n][C];
        }
    }




    //DUNAMIC PROGRAMMING 5
    static class DP5 {

        int max(int a, int b) {
            if (a > b) {
                return a;
            }
            return b;
        }

        int najdolgaZaednickaPodsekvencaDolzina(String x, String y) {
            int i, j;
            int N = x.length();
            int M = y.length();

            int NZP[][] = new int[N + 1][M + 1];

            for (i = 0; i < N; i++) {
                NZP[i][0] = 0;
            }

            for (j = 0; j < M; j++) {
                NZP[0][j] = 0;
            }

            for (i = 1; i <= N; i++) {
                for (j = 1; j <= M; j++) {
                    if (x.charAt(i - 1) == y.charAt(j - 1)) {
                        NZP[i][j] = NZP[i - 1][j - 1] + 1;
                    } else {
                        NZP[i][j] = max(NZP[i - 1][j], NZP[i][j - 1]);
                    }
                }
            }


            return NZP[N][M];
        }

        String najdolgaZaednickaPodsekvencaString(String x, String y) {
            int i, j;
            int N = x.length();
            int M = y.length();

            int NZP[][] = new int[N + 1][M + 1];

            for (i = 0; i < N; i++) {
                NZP[i][0] = 0;
            }

            for (j = 0; j < M; j++) {
                NZP[0][j] = 0;
            }

            for (i = 1; i <= N; i++) {
                for (j = 1; j <= M; j++) {
                    if (x.charAt(i - 1) == y.charAt(j - 1)) {
                        NZP[i][j] = NZP[i - 1][j - 1] + 1;
                    } else {
                        NZP[i][j] = max(NZP[i - 1][j], NZP[i][j - 1]);
                    }
                }
            }

            char rez1[] = new char[max(N, M)];
            int L = 0;
            i = N;
            j = M;

            while ((i != 0) && (j != 0)) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    rez1[L] = x.charAt(i - 1);
                    L++;
                    i--;
                    j--;
                } else {
                    if (NZP[i][j] == NZP[i - 1][j]) {
                        i--;
                    } else {
                        j--;
                    }
                }
            }

            String rez2 = "";
            for (i = 0; i < L; i++) {
                rez2 += rez1[L - 1 - i];
            }

            return rez2;
        }
    }





    //MERGESORTT

    static class MergeSort {

        int INF = 1000000;

        //spojuvanje na dve sortirani nizi [l, mid], [mid+1, r]
        //rezultatot e nova sortirana niza
        void merge(int a[], int l, int mid, int r) {
            int numel = r - l + 1;
            int temp[] = new int[100]; //nova niza za privremeno cuvanje na sortiranite elementi
            int i, j, k = 0;

            i = l;
            j = mid + 1;

            while ((i <= mid) && (j <= r)) {
                if (a[i] < a[j]) {
                    temp[k] = a[i];
                    i++;
                } else {
                    temp[k] = a[j];
                    j++;
                }
                k++;
            }

            while (i <= mid) {
                temp[k] = a[i];
                i++;
                k++;
            }

            while (j <= r) {
                temp[k] = a[j];
                j++;
                k++;
            }

            for (k = 0; k < numel; k++) {
                a[l + k] = temp[k];
            }
        }

        void mergesort(int a[], int l, int r) {
            if (l == r) {
                return;
            }

            int mid = (l + r) / 2;
            mergesort(a, l, mid);
            mergesort(a, mid + 1, r);
            merge(a, l, mid, r);
        }
    }




    //DIVIDE AND CONQUER
    static class DivideAndConquer {

        int INF = 1000000;

        int pow(int x, int n) {
            int r;

            if (n == 0) {
                return 1;
            } else if (n % 2 == 0) {
                r = pow(x, (n / 2));
                return r * r;
            } else {
                r = pow(x, (n / 2));
                return x * r * r;
            }

        }
    }


    public static void main(String [] args) throws IOException {

        //BRUUTE
//        Scanner input = new Scanner(System.in);
//        System.out.println("Vnesete ja goleminata na shahovskata tabla: ");
//        int n = input.nextInt();
//
//        int rez = number_of_combinations(n);
//        System.out.println("Brojot na nachini za postavuvanje e:" + rez);



        //GREEDY
//        Scanner input = new Scanner(System.in);
//        int n = input.nextInt();
//        int p[] = new int[n];
//        int w[] = new int[n];
//        for(int i=0;i<n;i++) {
//            p[i] = input.nextInt();
//            w[i] = input.nextInt();
//        }
//        System.out.println("Potrosnja");
//        int C = input.nextInt();
//
//        System.out.println(getFractKnpMaxProfit(p, w, C));



        //GREEDY 2
//        Scanner input = new Scanner(System.in);
//
//        int n = input.nextInt();
//        int p = input.nextInt();
//
//        int houses[][] = new int[n][4];
//        for(int i=0;i<n;i++) {
//            houses[i][0] = -1; // kon koja kukja ima izlezna cevka
//            houses[i][1] = 0; // dijametarot na izleznata cevka
//            houses[i][2] = -1; //od koja kukja ima vlezna cevka
//            houses[i][3] = 0; // dali e vekje povrzana kukjata
//        }
//
//        for(int i=0;i<p;i++) {
//            int h1 = input.nextInt();
//            int h2 = input.nextInt();
//            houses[h1][0] = h2;
//            houses[h1][1] = input.nextInt();
//            houses[h2][2] = h1;
//        }
//
//        ArrayList<ArrayList<Integer>> tanksAndTaps = findTanksAndTaps(houses);
//
//        System.out.println(tanksAndTaps.size());
//
//        for(int i=0;i<tanksAndTaps.size();i++) {
//            System.out.println(tanksAndTaps.get(i).get(0) + " " + tanksAndTaps.get(i).get(1) + " " + tanksAndTaps.get(i).get(2));
//        }



        //DUNAMIC PROGRAMMING
//        int i, j;
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        DP1 dp = new DP1();
//
//        System.out.println(dp.binomial_coefficient(8, 2));




        //DUNAMIC PROGRAMMING 2
//        int i, j;
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        DP2 dp = new DP2();
//
//        System.out.println("Vnesi broj na redici: ");
//        int m = Integer.parseInt(br.readLine());
//        System.out.println("Vnesi broj na koloni: ");
//        int n = Integer.parseInt(br.readLine());
//
//        for (i = 0; i < m; i++) {       // vnesuvanje na broj na kamenja vo sekoe pole
//            System.out.println("Vnesi ja " +(i+1)+ " redica: ");
//            for (j = 0; j < n; j++) {
//                dp.a[i][j] = Integer.parseInt(br.readLine());
//            }
//        }
//
//        dp.maksimalen_zbir(m, n);
//
//        System.out.println("Maksimalniot zbir e " + dp.best[m - 1][n - 1]);





        //DUNAMIC PROGRAMMING 3
//        int i, j;
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        System.out.println("Vnesi broj na gradovi:");
//        DP3.n = Integer.parseInt(br.readLine());
//
//
//        DP3.tax = new int[DP3.n];
//        DP3.best = new int[DP3.n];
//        DP3.cost = new int[DP3.n][DP3.n];
//
//        for(i = 0; i < DP3.n; i++) {
//            System.out.println("Vnesi aerodromska taksa za gradot " +(i+1)+ " : ");
//            DP3.tax[i] = Integer.parseInt(br.readLine());
//
//            for(j = i + 1; j < DP3.n; j++) {
//                System.out.println("Cena na bilet od " +(i+1)+ " do "+(j+1) + " : ");
//                DP3.cost[i][j] = Integer.parseInt(br.readLine());
//            }
//        }
//
//        DP3.best[0] = DP3.tax[0]; // za prviot grad se plakja samo taksa
//
//        for(i = 1; i < DP3.n; i++) {
//            DP3.best[i] = DP3.INFINITY; // inicijalizacija
//
//            // go barame optimalniot grad j, od koj bi patuvale do gradot i
//            for(j = 0; j < i; j++)
//                DP3.best[i] = DP3.min(DP3.best[i], DP3.best[j] + DP3.cost[j][i] + DP3.tax[i]);
//        }
//
//        System.out.println("Najmala cena e "+DP3.best[DP3.n-1]);




        //DUNAMIC PROGRAMMING 4
//        DP4 dp = new DP4();
//
//        int n = 3;
//        int C = 50;
//        int p[] = new int[]{60, 100, 120};
//        int t[] = new int[]{10, 20, 30};
//
//        System.out.println(dp.DPKnapsack(t, p, C));




        //DUNAMIC PROGRAMMING 5
//        DP5 dp = new DP5();
//
//        String x = "ggcaccacg";
//        String y = "acggcggatacg";
//
//        System.out.println(dp.najdolgaZaednickaPodsekvencaDolzina(x, y));
//        System.out.println(dp.najdolgaZaednickaPodsekvencaString(x, y));



        //MERGE SORT
//        int i;
//
//        MergeSort mer = new MergeSort();
//
//        int a[] = new int[]{9, 2, 4, 6, 0, 8, 7, 3, 1, 5};
//
//        mer.mergesort(a, 0, 9);
//
//        for (i = 0; i < 10; i++) {
//            System.out.print(a[i] + " ");
//        }
//        System.out.println();




        //DIVIDE AND CONQUER
//        DivideAndConquer dac = new DivideAndConquer();
//
//        System.out.println("pow: " + dac.pow(2, 10));



    }
}
