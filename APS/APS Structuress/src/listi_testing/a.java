package listi_testing;

import java.util.Scanner;

public class a {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Etest-1");
        float etest1=sc.nextFloat();
        System.out.println("Etest-2");
        float etest2=sc.nextFloat();
        System.out.println("Theory-1");
        float theory1=sc.nextFloat();
        System.out.println("Theory-2");
        float theory2=sc.nextFloat();
        System.out.println("Exercises-1");
        float exer1=sc.nextFloat();
        System.out.println("Exercises-2");
        float exer2=sc.nextFloat();

        float sum=0;
        sum+=(30*(etest1))/100;
        System.out.println(sum+" after Etest1");
        sum+=(30*(etest2))/100;
        System.out.println(sum+" after Etest1,Etest2");
        sum+=(60*(theory1))/100;
        System.out.println(sum+" after Etest1,Etest2,theory1");
        sum+=(60*(theory2))/100;
        System.out.println(sum+" after Etest1,Etest2,theory1,theory2");
        sum+=(40*(exer1))/100;
        System.out.println(sum+" after Etest1,Etest2,theory1,theory2,exer1");
        sum+=(40*(exer2))/100;
        System.out.println(sum+" after Etest1,Etest2,theory1,theory2,exer1,exer2");
        sum+=15;
        System.out.println(sum+" after Etest1,Etest2,theory1,,theory2,exer1,exer2,project");
        sum+=40;
        System.out.println(sum+" after Etest1,Etest2,theory1,,theory2,exer1,exer2,project");

        sc.close();
    }
}
