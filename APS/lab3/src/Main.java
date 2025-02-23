import java.util.*;
import java.util.Scanner;

/*

class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;
    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }


    public boolean equals(DLLNode <E> node) {
        return node.succ == this.succ && node.element == this.element && node.pred == this.pred;

    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
    }

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(E o) {
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if (after == last) {
            insertLast(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if (before == first) {
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first != null) first.pred = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        } else
            return null;
    }

    public E delete(DLLNode<E> node) {
        if (node == first) {
            return deleteFirst();
        }
        if (node == last) {
            return deleteLast();
        }
        node.pred.succ = node.succ;
        node.succ.pred = node.pred;
        return node.element;

    }

    public DLLNode<E> find(E o) {
        if (first != null) {
            DLLNode<E> tmp = first;
            while (!tmp.element.equals(o) && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element.equals(o)) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int getSize() {
        int listSize = 0;
        DLLNode<E> tmp = first;
        while(tmp != null) {
            listSize++;
            tmp = tmp.succ;
        }
        return listSize;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp.toString();
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += "<->" + tmp.toString();
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public String toStringR() {
        String ret = new String();
        if (last != null) {
            DLLNode<E> tmp = last;
            ret += tmp.toString();
            while (tmp.pred != null) {
                tmp = tmp.pred;
                ret += "<->" + tmp.toString();
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {

        return last;
    }

    public void setFirst(DLLNode<E> node) {
        this.first = node;
    }

    public void setLast(DLLNode<E> node) {
        this.last = node;
    }

    public void mirror() {

        DLLNode<E> tmp = null;
        DLLNode<E> current = first;
        last = first;
        while(current!=null) {
            tmp = current.pred;
            current.pred = current.succ;
            current.succ = tmp;
            current = current.pred;
        }

        if(tmp!=null && tmp.pred!=null) {
            first=tmp.pred;
        }
    }
}

  class DLLBattalion {

    //TODO: implement function
    public static void battalion(DLL<Integer> list, int a, int b) {


//    DLLNode <Integer> t= list.find(a);
//
//        //DLL <Integer> lista=list;
//        DLLNode <Integer> tmp=t;
//        DLLNode <Integer> tmp1=list.find(b);
//        DLLNode <Integer> temp=t;
//        DLLNode <Integer> temp1=tmp1;
//
//        while (!temp.equals(temp1)){
//            if(!tmp1.element.equals(t.element))list.insertBefore(tmp1.element,t);
//            tmp1=tmp1.pred;
//            DLLNode <Integer> temporary=t.succ;
//            list.delete(t);
//            t=temporary;
//            temp=temp.succ;
//        }
//        list.insertBefore(tmp1.element,t);
//        list.delete(t);
//        
//
//        DLLNode <Integer> f=list.find(a);
//        DLLNode <Integer> s=list.find(b);
//        while (f.element!= s.element){
//            int tmp=f.element;
//            f.element=s.element;
//            s.element=tmp;
//            f=f.succ;
//            //if(a==b)break;
//            //b=s.element;
//        }
//
//
//
//                DLLNode <Integer> t= list.find(a);
//        DLL <Integer> lista=new DLL<>();
//        DLLNode <Integer> tmpa=t;
//        DLLNode <Integer> tmp1=list.find(b);
//        DLLNode <Integer> tmpb=tmp1;
//        DLLNode <Integer> temp=t;
//        DLLNode <Integer> temp1=tmp1;
//        while (!temp.equals(temp1)){
//
//            lista.insertFirst(temp.element);
//            temp=temp.succ;
//
//        }
//        lista.insertFirst(temp.element);
//
//        temp=t;
//        while (!temp.equals(temp1)){
//
//            //if(!tmp1.element.equals(t.element))list.insertBefore(tmp1.element,t);
//            //tmp1=tmp1.pred;
//            DLLNode <Integer> temporary=t.succ;
//            list.delete(t);
//            t=temporary;
//            temp=temp.succ;
//        }
//        //list.insertBefore(tmp1.element,t);
//        list.delete(t);
//
//        tmpa.pred.succ=lista.getFirst();
//        lista.getFirst().pred=tmpa.pred;
//        lista.getLast().succ=tmpb.succ;
//        tmpb.succ.pred=lista.getLast();
//
//t.pred.toString();
//System.out.println(t.pred);

        DLL <Integer> lista=new DLL<>();
        DLLNode <Integer> t= list.find(a);
        DLLNode <Integer> tmpa=t;
        //DLLNode <Integer> tmp1=list.find(b);
        DLLNode <Integer> tmpb=list.find(b);
        DLLNode <Integer> temp=t;
        DLLNode <Integer> temp1=list.find(b);;
        while (!temp.equals(temp1)){
            lista.insertFirst(temp.element);
            temp=temp.succ;
        }
        lista.insertFirst(temp.element);

        temp=t;
        while (!temp.equals(temp1)){
            DLLNode <Integer> temporary=t.succ;
            list.delete(t);
            t=temporary;
            temp=temp.succ;
        }
        list.delete(t);

        if(tmpa.pred!=null)tmpa.pred.succ=lista.getFirst();
        else list.setFirst(lista.getFirst());
        lista.getFirst().pred=tmpa.pred;
        if(tmpb.succ!=null){lista.getLast().succ=tmpb.succ;
            tmpb.succ.pred=lista.getLast();}
        else {list.setLast(lista.getLast());}


    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        DLL<Integer> list = new DLL<>();
        for(int i=0;i<n;i++) {
            list.insertLast(input.nextInt());
        }

        int a = input.nextInt();
        int b = input.nextInt();

        battalion(list, a, b);

        System.out.println(list);
        System.out.println(list.toStringR());


    }
}
*/



/*
 class CountWordPairs {

    //TODO: implement function
    public static int countWordPairs(String [] words) {
        int p=0;
        for(int i=0;i<words.length;++i){
            for(int j=i;j<words.length;j++){
                if(words[i].charAt(0)==words[j].charAt(0) && !words[i].equals(words[j]))p++;
            }
        }

        return p;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();

        String words[] = new String[N];

        for(int i=0;i<N;i++) {
            words[i] = input.next();
        }

        System.out.println(countWordPairs(words));

    }
}
*/


 class MiceHoles {

    //TODO: implement function
    public static int minTime(int mice[], int holes[]) {
        int totalmin=0;
        int longest_dist=1000;
        int maxwalk=0;
        int minidx=0;

        for(int i=0;i<mice.length;i++) {
          for(int j=0;j< mice.length;j++){
              if(holes[j]!=15000){
              if(Math.abs(mice[i]-holes[j])<longest_dist){
                  longest_dist=Math.abs(mice[i]-holes[j]);
                  if(maxwalk<longest_dist)maxwalk=longest_dist;
                  minidx=j;
              }}
          }
            holes[minidx]=15000;
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

