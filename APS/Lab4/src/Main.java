import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.lang.Character.isDigit;

/*
class ArithmeticExpression {
    static int presmetaj(char c[], int l, int r) {

        if(l==r)return Character.getNumericValue(c[l]);
        int count=0;
        for(int i=l;i<r;i++){
            if(c[i]=='(')count++;
            if(c[i]==')')count--;
            if(count==1){
                if(c[i]=='+')return presmetaj(c,l+1,i-1)+presmetaj(c,i+1,r-1);
                if(c[i]=='-')return presmetaj(c,l+1,i-1)-presmetaj(c,i+1,r-1);
            }
        }
        return presmetaj(c,l,r);
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = presmetaj(exp, 0, exp.length-1);
        System.out.println(rez);

        br.close();

    }

}
*/



/*
 class ZigZagSequence {

    static int najdiNajdolgaCikCak(int a[]) {
        // Vasiot kod tuka
        int max=0;
        int flag=-1;
        int count=0;
        for(int i=0;i<a.length;i++){
            if(a[i]>0)
            {
                if(flag==1)
                {
                    if(count>max)max=count;
                    count=0;
                }
                flag=1;
                count++;
                if(count>max)max=count;
            }
            if(a[i]<0)
            {
                if(flag==0)
                {
                    if(count>max)max=count;
                    count=0;
                }
                flag=0;
                count++;
                if(count>max)max=count;
            }
            if(a[i]==0)count=0;
        }
        return max;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N];
        for (i=0;i<N;i++)
            a[i] = Integer.parseInt(br.readLine());

        int rez = najdiNajdolgaCikCak(a);
        System.out.println(rez);

        br.close();

    }

}

 */


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
class SumOfAbsoluteDifferences {

    static int solve(int numbers[], int N, int K) {
        // vasiot kod ovde
        // mozete da napisete i drugi funkcii dokolku vi se potrebni


        int[][] matrix = new int[N][K];
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < K; j++) {
                for (int k = 0; k < i; k++) {
                    int abs = Math.abs(numbers[i] - numbers[k]);
                    matrix[i][j] = Math.max(matrix[i][j],abs + matrix[k][j-1]);
                }
                max = Math.max(matrix[i][j],max);
            }
        }
        return max;

        int[] array =new int[N];
        int max=0;
        int sum=0;
        int count=0;
        for (int i=0;i<N;i++){
            max=0;
            for(int j=i;j<N-1;j++){
                if(Math.abs(numbers[j+1]-numbers[j])>max){
                    max=Math.abs(numbers[j+1]-numbers[j]);
                }
            }
            array[i]=max;
        }
        int j=0;
        while (count<K){
            sum+=array[j];
            j++;
            count++;
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int numbers[] = new int[N];

        st = new StringTokenizer(br.readLine());
        for (i=0;i<N;i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int res = solve(numbers, N, K);
        System.out.println(res);

        br.close();

    }

}

*/

