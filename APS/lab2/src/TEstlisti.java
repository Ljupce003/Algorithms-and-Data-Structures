import java.util.Scanner;

public class TEstlisti {


    public static void filllist(SLL <Integer> li,int a,int b) {
        SLLNode<Integer> curr=li.getFirst();
        for(int i=a;i<=b;i++){
            li.insertLast(i);
        }
    }


    public static void printlist(SLL <Integer> list) {
        SLLNode <Integer> curr=list.getFirst();
        while(curr!=null){
            if(curr.succ==null)System.out.print(curr);
            else System.out.print(curr+" -> ");
            curr=curr.succ;
        }
        System.out.println();
    }


    public static int evennumbers(SLL <Integer> list){
        int even=0;
        SLLNode<Integer> f= new SLLNode<>(list.getFirst().element,list.getFirst());
        for(int i=0;i< list.size();i++){
            if(f.element%2==0)even++;
            f=f.succ;
        }
        return even;
    }



    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int a= input.nextInt();
        int b=input.nextInt();
        SLL <Integer> list=new SLL<>();
        SLLNode<Integer> f=new SLLNode<>(50, list.find(20));
        filllist(list,a,b);
        list.insertAfter(20,list.find(12));
        list.insertLast(35);
        printlist(list);

        System.out.println(evennumbers(list));

    }


}
