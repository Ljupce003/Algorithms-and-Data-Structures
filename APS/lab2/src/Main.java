import java.util.Scanner;
import java.lang.Math;



/*
    @SuppressWarnings("unchecked")
     class Array<E> {
        private E data[]; // declared to be an Object since it would be too
        // complicated with generics
        private int size;

        public Array(int capacity) {
            this.data = (E[]) new Object[capacity];
            this.size = 0;
        }

        public void insertLast(E o) {
            //check if there is enough capacity, and if not - resize
            if(size + 1 > data.length)
                this.resize();
            data[size++] = o;
        }

        public void insert(int position, E o) {
            // before all we check if position is within range
            if (position >= 0 && position <= size) {
                //check if there is enough capacity, and if not - resize
                if(size + 1 > data.length)
                    this.resize();
                //copy the data, before doing the insertion
                for(int i=size;i>position;i--) {
                    data[i] = data[i-1];
                }
                data[position] = o;
                size++;
            } else {
                System.out.println("Ne mozhe da se vmetne element na taa pozicija");
            }
        }

        public void set(int position, E o) {
            if (position >= 0 && position < size)
                data[position] = o;
            else
                System.out.println("Ne moze da se vmetne element na dadenata pozicija");
        }

        public E get(int position) {
            if (position >= 0 && position < size)
                return data[position];
            else
                System.out.println("Ne e validna dadenata pozicija");
            return null;
        }

        public int find(E o) {
            for (int i = 0; i < size; i++) {
                if(o.equals(data[i]))
                    return i;
            }
            return -1;
        }

        public int getSize() {
            return size;
        }

        public void delete(int position) {
            // before all we check if position is within range
            if (position >= 0 && position < size) {
                // first resize the storage array
                E[] newData = (E[]) new Object[size - 1];
                // copy the data prior to the delition
                for (int i = 0; i < position; i++)
                    newData[i] = data[i];
                // move the data after the deletion
                for (int i = position + 1; i < size; i++)
                    newData[i - 1] = data[i];
                // replace the storage with the new array
                data = newData;
                size--;
            }
        }

        public void resize() {
            // first resize the storage array
            E[] newData = (E[]) new Object[size*2];
            // copy the data
            for (int i = 0; i < size; i++)
                newData[i] = data[i];
            // replace the storage with the new array
            this.data = newData;
        }

        @Override
        public String toString() {
            String ret = new String();
            if(size>0) {
                ret = "{";
                ret += data[0];
                for(int i=1;i<size;i++) {
                    ret += "," + data[i];
                }
                ret+="}";
                return ret;
            }
            else {
                ret = "Prazna niza!";
            }
            return ret;
        }

    }

     class ArrayMeanWordLength {

        //TODO: implement function
        public static String wordClosestToAverageLength(Array<String> arr) {
            int sum=0;

            for(int i=0;i<arr.getSize();i++) sum+=arr.get(i).length();
            float avg= (float) sum /arr.getSize();
            String word = null;
            float least_distance=500;


            int avg1=sum /arr.getSize();
            if(!((float)avg1+0.5>avg)){;avg1++;}

            int flag=0;
            for(int i=0;i<arr.getSize();i++){
                if(arr.get(i).length()==avg1) {flag=1;}
            }
            if(flag==1){for(int i=0;i<arr.getSize();i++){
                if(arr.get(i).length()==avg1) {word= arr.get(i);break;}
            }}
            else {
                avg1--;
                for(int i=0;i<arr.getSize();i++){
                    if(arr.get(i).length()==avg1) {word= arr.get(i);break;}
                }
            }

            for(int i=0;i<arr.getSize();i++){
                if(Math.abs(arr.get(i).length()-avg)<least_distance ){least_distance=Math.abs(arr.get(i).length()-avg);
                word=arr.get(i);}
            }

            return word;
        }

        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);

            int N = input.nextInt();
            Array<String> arr;
            arr = new Array<>(N);
            input.nextLine();

            for(int i=0;i<N;i++) {
                arr.insertLast(input.nextLine());
            }

            System.out.println(wordClosestToAverageLength(arr));
        }
    }
*/
import java.util.Scanner;




/*
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
                ret += " " + tmp;
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

 class SpecialSLLDelete<E> {

    //TODO: implement method
    public void specialDelete(SLL<E> list, int m) {
        SLLNode<E> curr=list.getFirst();
        int count=0;
        while (curr!=null){
            count++;
            if(count==m){
                SLLNode<E> tmp;
                tmp=curr.succ;
                list.delete(curr);
                curr=tmp;
                count=0;
            }
            else  curr=curr.succ;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        SLL<Integer> list = new SLL<>();
        for(int i=0;i<n;i++) {
            list.insertLast(input.nextInt());
        }

        int m = input.nextInt();

        SpecialSLLDelete<Integer> tmp = new SpecialSLLDelete<>();

        tmp.specialDelete(list, m);

        System.out.println(list);
    }

}
        */




class Card {
    private int type;
    private int health;
    private int magicPower;

    public Card(int type, int health, int magicPower) {
        this.type = type;
        this.health = health;
        this.magicPower = magicPower;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMagicPower() {
        return magicPower;
    }

    public void setMagicPower(int magicPower) {
        this.magicPower = magicPower;
    }

    public int significance() {
        return health * magicPower;
    }


    @Override
    public String toString() {
        return String.valueOf(type);
    }
}

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
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int size() {
        int ret;
        if (first != null) {
            SLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            SLLNode<E> tmp = first;
            ret += tmp;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += " " + tmp;
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, first);
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
            if (first == before) {
                this.insertFirst(o);
                return;
            }
            while (tmp.succ != before)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                SLLNode<E> ins = new SLLNode<E>(o, before);
                tmp.succ = ins;
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
            SLLNode<E> ins = new SLLNode<E>(o, null);
            tmp.succ = ins;
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
            if (first == node) {
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
            while (tmp.element != o && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element == o) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return first;
    }
}

 class MysticalCardGame {

    //TODO: implement function
    public static void startDuel(SLL<Card> firstSorcererCards, SLL<Card> secondSorcererCards) {
        SLLNode<Card> a=firstSorcererCards.getFirst();
        int max=0;
        while (a!=null) {
            if(max<a.element.getHealth()*a.element.getMagicPower()){
                max=a.element.getHealth()*a.element.getMagicPower();}
            a=a.succ;
        }
        a=firstSorcererCards.getFirst();
        SLLNode<Card> best = null;
        while (a!=null){
            if(a.element.getHealth()*a.element.getMagicPower()==max){best=a;firstSorcererCards.delete(a);}
            a=a.succ;
        }
        SLLNode<Card> b=secondSorcererCards.getFirst();
        int count=0;
        while (b!=null){
            if(count==3)secondSorcererCards.insertAfter(best.element,b);
            b=b.succ;
            count++;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SLL<Card> firstSorcererCards = new SLL<Card>();
        SLL<Card> secondSorcererCards = new SLL<Card>();

        for (int i = 0; i < 8; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            firstSorcererCards.insertLast(new Card(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }

        for (int i = 0; i < 8; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            secondSorcererCards.insertLast(new Card(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }

        startDuel(firstSorcererCards, secondSorcererCards);
        System.out.println(firstSorcererCards);
        System.out.println(secondSorcererCards);
    }
}


