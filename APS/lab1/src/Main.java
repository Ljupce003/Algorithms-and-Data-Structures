import java.util.Arrays;
import java.util.Scanner;




 class PushZero
{


    static void swapnumbers(int arr[],int i){
        int temp=arr[i];
        arr[i]=arr[i+1];
        arr[i+1]=temp;
    }
     static void pushZerosToBeginning(int arr[], int n)
    {
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n - 1; i++) {
                if (arr[i + 1] == 0) {
                    //System.out.print(arr[i]+" ");
                    swapnumbers(arr,i);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]+" ");
        }


}

    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int n;
        n=input.nextInt();
        int [] array=new int[n];
        for (int i=0;i<n;i++){
            array[i]= input.nextInt();
        }
        System.out.println("Transformiranata niza e:");
        pushZerosToBeginning(array,n);
    }
}



/*
class QuarterlySales {

    private int numOfSales;
    private int [] revenues;
    private int quarterNo;

    public QuarterlySales(int numOfSales, int[] revenues, int quarterNo) {
        this.numOfSales = numOfSales;
        this.revenues = revenues;
        this.quarterNo = quarterNo;
    }

    public int QuarterlySumSales(){
        int sum=0;
       for(int i=0;i<numOfSales;i++) sum+=revenues[i];
       return sum;
    }


}

class SalesPerson {

    private String name;
    private QuarterlySales [] quarters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public QuarterlySales[] getQuarters() {
        return quarters;
    }

    public void setQuarters(QuarterlySales[] quarters) {
        this.quarters = quarters;
    }

    public SalesPerson(String name, QuarterlySales[] quarters) {
        this.name = name;
        this.quarters = quarters;
    }

    public int SumSales(SalesPerson sp){
        int sum = 0;
        for(int i=0;i<4;i++){
            sum+=sp.quarters[i].QuarterlySumSales();
        }
        return sum;
    }

    @Override
    public String toString() {
        return "";
    }

}



public class Main {

    public static SalesPerson salesChampion(SalesPerson [] arr)
    {
        int max=0;
        int maxsalesum=arr[0].SumSales(arr[0]);
        for(int i=0;i< arr.length;i++){
            if(arr[i].SumSales(arr[i])>maxsalesum){max=i;maxsalesum=arr[i].SumSales(arr[i]);}
        }
return arr[max];
    }
    public static void table(SalesPerson [] arr)
    {
        System.out.println("SP   1   2   3   4   Total");
        for(int i=0;i< arr.length;i++){
            System.out.print(arr[i].getName()+"   ");
            for (int j=0;j<4;j++){
                System.out.print(arr[i].getQuarters()[j].QuarterlySumSales()+"   ");
            }
            System.out.print(arr[i].SumSales(arr[i]));
            System.out.println();
        }
        System.out.println();

    }


    public static void main(String[] args) {

        int n;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        SalesPerson [] arr = new SalesPerson[n];
        for(int i=0;i<n;i++)
        {
         String name=input.next();

            QuarterlySales []qs = new QuarterlySales[4];
            for(int j=0;j<4;j++){
                int numsale=input.nextInt();
                int []sales=new int[numsale];
                for(int k=0;k<numsale;k++) {sales[k]=input.nextInt();}
                qs[j]=new QuarterlySales(numsale,sales,j);
            }
//arr constructor
            arr[i]=new SalesPerson(name,qs);
        }

        table(arr);
        System.out.println("SALES CHAMPION: " + salesChampion(arr).getName());

    }
}
*/

/*
class ReverseWord {

    public static void printReversed(String word) {
        int size=word.length();
        for (int i = word.length()-1; i>=0; i--) {
            System.out.print(word.charAt(i));
        }
    }

    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);
        int n=input.nextInt();
        for (int i = 0; i < n; i++) {
            String word= input.next();
            printReversed(word);
        }
    }


}
 */