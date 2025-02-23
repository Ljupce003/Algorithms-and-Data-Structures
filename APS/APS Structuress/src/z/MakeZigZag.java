package z;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int size() {
        int listSize = 0;
        SLLNode<E> tmp = first;
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
            SLLNode<E> tmp = first;
            ret += tmp;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += " -> " + tmp;
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, null);
        ins.succ = first;
        //SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }
    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            SLLNode<E> tmp = first;
            if(first==before){
                this.insertFirst(o);
                return;
            }
            //ako first!=before
            while (tmp.succ != before && tmp.succ!=null)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                tmp.succ = new SLLNode<E>(o, before);;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = new SLLNode<E>(o, null);
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if(first == node) {
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }

    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
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

    public void merge (SLL<E> in){
        if (first != null) {
            SLLNode<E> tmp = first;
            while(tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = in.getFirst();
        }
        else{
            first = in.getFirst();
        }
    }

    public void mirror() {
        if (first != null) {
            //m=nextsucc, p=tmp,q=next
            SLLNode<E> tmp = first;
            SLLNode<E> newsucc = null;
            SLLNode<E> next;

            while(tmp != null){
                next = tmp.succ;
                tmp.succ = newsucc;
                newsucc = tmp;
                tmp = next;
            }
            first = newsucc;
        }
    }
}

    public class MakeZigZag {

        //TODO: implement function
        public static void makeZigZag(SLL<Integer> list) {

//            int flag=-1;
//            int insflag=0;
//            int idx=list.size();
//            SLLNode<Integer> curr=list.getFirst();
//            SLLNode<Integer> prev=null;
//            int n=0;
//            while(n!=list.size()){
//                //if(n>list.size()+1)break;
//                if(list.size()==0)break;
//                insflag=0;
//                if(n==1)prev=list.getFirst();
//                if(curr==null)break;
//                if(curr.element>0){
//                    if(flag==1)list.delete(curr);
//                    flag=1;
//                }
//                if(curr.element<0){
//                    if(flag==0){
//                        Integer i=Math.abs(prev.element);
//                        list.insertAfter(i,prev);
//                        insflag=1;
//
//                    }
//                    flag=0;
//                }
//                if(curr.element==0){
//                    SLLNode<Integer> tmp=curr;
//                    list.delete(curr);
//                    curr=tmp.succ;
//                    prev=tmp;
//                }
//                else {
//                    curr = curr.succ;
//                    if (n > 0 &&insflag!=1){if(prev!=null)prev = prev.succ;}
//                    else if (n > 0 && prev.succ!=null)prev=prev.succ.succ;
//                }
//                n++;
//                //if(n==list.size())break;
//            }



            SLLNode<Integer> node = list.getFirst();
            SLLNode<Integer> prev = null;
            while (node != null) {
                if (node.element == 0) {
                    list.delete(node);
                }
                if (prev != null && prev.element > 0 && node.element > 0) {
                    list.delete(node);
                }
                if (prev != null && prev.element < 0 && node.element < 0) {
                    list.insertAfter(Math.abs(prev.element), prev);
                }
                prev = node;
                node = node.succ;

            }

        }

        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);

            int n = input.nextInt();

            SLL<Integer> list = new SLL<>();

            for(int i=0;i<n;i++) {
                list.insertLast(input.nextInt());
            }

            System.out.println(list);

            makeZigZag(list);

            System.out.println(list);
        }
    }


