package ednodimenzionalni;

import APS_Struct.one_Dimensional_Structures.ArrayQueue;
import APS_Struct.one_Dimensional_Structures.ArrayStack;
import APS_Struct.one_Dimensional_Structures.LinkedQueue;
import APS_Struct.one_Dimensional_Structures.Queue;

import java.util.*;

public class Ednodimenzionalni_REDICA {


    static class Proces implements Comparable<Proces>{
        String process_name;
        int execution_time;
        int arrival_time;

        public Proces(String process_name, int execution_time, int arrival_time) {
            this.process_name = process_name;
            this.execution_time = execution_time;
            this.arrival_time = arrival_time;
        }

        public String getProcess_name() {
            return process_name;
        }

        public int getExecution_time() {
            return execution_time;
        }

        public int getArrival_time() {
            return arrival_time;
        }

        public void updateTime(int kvant_t){
            if(this.execution_time<kvant_t){
                this.execution_time=0;
            }
            else this.execution_time-=kvant_t;
        }



        @Override
        public String toString() {
            return process_name;
        }

        @Override
        public int compareTo(Proces o) {
            if(this.arrival_time==o.arrival_time){
                return Integer.compare(o.execution_time,this.execution_time);
            }
            else return Integer.compare(this.arrival_time,o.arrival_time);
        }
    }
    private static void RoundRobin(Scanner sc) {
        int n=Integer.parseInt(sc.nextLine());
        List<Proces> p_list=new ArrayList<>();
        LinkedQueue<Proces> p_que=new LinkedQueue<>();
        for (int i = 0; i < n; i++) {
            String line= sc.nextLine();;
            String [] parts=line.split("\\s++");
            String p_name=parts[0];
            int exec_time=Integer.parseInt(parts[1]);
            int arrival_t=Integer.parseInt(parts[2]);
            Proces proces=new Proces(p_name,exec_time,arrival_t);
            p_list.add(proces);
        }

        int kvantum_t=sc.nextInt();

        p_list.sort(Comparator.naturalOrder());

        for (int i = 0; i < n; i++) {
            p_que.enqueue(p_list.get(i));
        }

        while (!p_que.isEmpty()){
            Proces p=p_que.dequeue();
            p.updateTime(kvantum_t);
            if(p.execution_time!=0)p_que.enqueue(p);
            System.out.print(p+" ");
        }




        //System.out.println(p_list);


    }


    static class QuasiQue<E extends Comparable<E>> implements Queue<E>{

        E[] elements;
        int lenght,front,rear,maxlenght;

        @SuppressWarnings("unchecked")
        public QuasiQue(int maxlenght) {
            elements=(E[]) new Comparable[maxlenght];
            this.maxlenght = maxlenght;
            clear();
        }

        @Override
        public boolean isEmpty() {
            return lenght==0;
        }

        @Override
        public int size() {
            return lenght;
        }

        @Override
        public E peek() {
            return elements[0];

        }

        public E peekFirst() {
            if(lenght>0) return elements[front];
            else throw new NoSuchElementException();

        }

        public E peekLast() {
            if(lenght>0) return elements[rear-1];
            else throw new NoSuchElementException();

        }

        @Override
        public void clear() {
            lenght=0;
            front=rear=0;
        }

        @Override
        public void enqueue(E x) {
            if(rear==maxlenght)elements[0]=x;
            else {
                elements[rear++]=x;
                lenght++;
            }
        }

        @Override
        public E dequeue() {
            if(lenght>0){
                E frontmost=elements[front];
                E rearmost=elements[rear-1];
                if(front==rear){
                    front=0;
                    rear=0;
                    elements[front]=null;
                    lenght--;
                    return frontmost;
                }
                if(frontmost.compareTo(rearmost) < 0 || frontmost.compareTo(rearmost)==0){
                    elements[front++]=null;
                    lenght--;
                    return frontmost;
                }
                else {
                    elements[rear-1]=null;
                    rear--;
                    lenght--;
                    return rearmost;
                }
            }
            else throw new NoSuchElementException();
        }
    }


    private static void TrainStation(Scanner sc) {
        int n=Integer.parseInt(sc.nextLine());
        ArrayStack<Integer> old_wagons=new ArrayStack<>(n);
        for (int i = 0; i < n; i++) {
            old_wagons.push(sc.nextInt());
        }
        ArrayQueue<Integer> new_wagons=new ArrayQueue<>(n);

        ArrayQueue<Integer> helper=new ArrayQueue<>(n);
        int max=0;
        do {
            while (!old_wagons.isEmpty()) {
                int wagon = old_wagons.pop();
                if (wagon == 0 || wagon == max) continue;
                new_wagons.enqueue(wagon);
            }

            max = 0;


            while (!new_wagons.isEmpty()) {
                int wag = new_wagons.dequeue();
                max = Math.max(max, wag);
                old_wagons.push(wag);
            }

            if (max != 0) helper.enqueue(max);


        } while (!old_wagons.isEmpty() || !new_wagons.isEmpty());


        StringBuilder s= new StringBuilder();
        while (!helper.isEmpty()){
            int wagon = helper.dequeue();
            s.insert(0, wagon + " ");
        }

        System.out.println(s);








    }


    private static void Kolokvium(Scanner sc) {

        int termin_cap=Integer.parseInt(sc.nextLine());

        int br_mat=Integer.parseInt(sc.nextLine());
        ArrayQueue<String> polagat_mat=new ArrayQueue<>(br_mat);
        for (int i = 0; i < br_mat; i++) {
            String student=sc.nextLine();
            polagat_mat.enqueue(student);
        }

        int br_ostanati=Integer.parseInt(sc.nextLine());
        ArrayQueue<String> ostanati=new ArrayQueue<>(br_ostanati);
        for (int i = 0; i < br_ostanati; i++) {
            String student=sc.nextLine();
            ostanati.enqueue(student);
        }

        int vist_mat=Integer.parseInt(sc.nextLine());
        ArrayQueue<String> real_mat=new ArrayQueue<>(vist_mat);
        for (int i = 0; i < vist_mat; i++) {
            String student = sc.nextLine();
            real_mat.enqueue(student);
        }


        ArrayQueue<String> kazeni=new ArrayQueue<>(br_mat);

        ArrayQueue<String> raspored=new ArrayQueue<>(br_mat+br_ostanati+vist_mat);

        while (!polagat_mat.isEmpty()){
            String student=polagat_mat.dequeue();
            if(checktruth(student,real_mat))raspored.enqueue(student);
            else kazeni.enqueue(student);
        }

        while (!ostanati.isEmpty()){
            String student= ostanati.dequeue();
            raspored.enqueue(student);
        }


        while (!kazeni.isEmpty()){
            String student= kazeni.dequeue();
            raspored.enqueue(student);
        }


        int i=1;
        boolean p_prost=false;
        int brojac=0;
        while (!raspored.isEmpty()){
            if(!p_prost){System.out.println(i);p_prost=true;}
            System.out.println(raspored.dequeue());
            brojac++;
            if(brojac==termin_cap){
                brojac=0;
                i++;
                p_prost=false;
            }
        }



    }
    private static boolean checktruth(String student, ArrayQueue<String> realMat) {
        boolean contains=false;
        int ciklus=0;
        int lim=realMat.size();
        while (ciklus!=lim){
            String s=realMat.dequeue();
            if(s.equals(student))contains=true;
            realMat.enqueue(s);
            ciklus++;
        }

        return contains;




    }


    private static void Konsultacii(Scanner sc) {
        int br_za_APS=Integer.parseInt(sc.nextLine());
        ArrayQueue<String> APS_kon=new ArrayQueue<>(br_za_APS);
        for (int i = 0; i < br_za_APS; i++) {
            String student=sc.nextLine();
            APS_kon.enqueue(student);
        }
        int br_za_MMS=Integer.parseInt(sc.nextLine());
        ArrayQueue<String> MMS_kon=new ArrayQueue<>(br_za_MMS);
        for (int i = 0; i < br_za_MMS; i++) {
            String student=sc.nextLine();
            MMS_kon.enqueue(student);
        }

        ArrayQueue<String> rasp_kons=new ArrayQueue<>(br_za_MMS+br_za_APS);
        ArrayQueue<String> kraj_na_red=new ArrayQueue<>(br_za_APS);

        String last_question="";
        while (!APS_kon.isEmpty()){
            String []parts= APS_kon.dequeue().split("\\s++");
            String student=parts[0];
            String question=parts[1];

            if(rasp_kons.isEmpty()){rasp_kons.enqueue(student);last_question=question;}
            else {
                if(last_question.equals(question))
                {
                    kraj_na_red.enqueue(student);
                    if(!MMS_kon.isEmpty())rasp_kons.enqueue(MMS_kon.dequeue());
                }
                else {rasp_kons.enqueue(student);last_question=question;}
            }

        }

        while (!MMS_kon.isEmpty()){
            String student= MMS_kon.dequeue();
            rasp_kons.enqueue(student);
        }

        while (!kraj_na_red.isEmpty()){
            String student= kraj_na_red.dequeue();
            rasp_kons.enqueue(student);
        }

        while (!rasp_kons.isEmpty()){
            System.out.println(rasp_kons.dequeue());

        }



    }


    private static void Organizacija_ispit(Scanner sc) {
        int br_etest=Integer.parseInt(sc.nextLine());
        ArrayQueue<String> etest=new ArrayQueue<>(br_etest);
        for (int i = 0; i < br_etest; i++) {
            String student = sc.nextLine();
            etest.enqueue(student);
        }
        int br_zadaci=Integer.parseInt(sc.nextLine());
        LinkedQueue<String> zadaci=new LinkedQueue<>();
        for (int i = 0; i < br_zadaci; i++) {
            String student = sc.nextLine();
            zadaci.enqueue(student);
        }
        int br_both=Integer.parseInt(sc.nextLine());
        ArrayQueue<String> both=new ArrayQueue<>(br_both);
        for (int i = 0; i < br_both; i++) {
            String student = sc.nextLine();
            both.enqueue(student);
        }

        ArrayQueue<String> raspredelba=new ArrayQueue<>(20);

        int i=0;
        while (!etest.isEmpty()){
            String student= etest.dequeue();
            raspredelba.enqueue(student);
            i++;
        }

        while (i!=20 && !both.isEmpty()){
            String student= both.dequeue();
            raspredelba.enqueue(student);
            i++;
        }
        System.out.println("Resavanje teorija\ntermin 1");
        System.out.println(raspredelba);
        i=0;
        while (i!=br_etest){
            raspredelba.dequeue();
            i++;
        }



        while (!raspredelba.isEmpty()){
            String student= raspredelba.dequeue();
            zadaci.enqueue(student);
        }



        while (!zadaci.isEmpty()) {
            String student= zadaci.dequeue();
            raspredelba.enqueue(student);
        }

        System.out.println("Resavanje zadaci\ntermin 1");
        System.out.println(raspredelba);




    }


    private static void Kolokviumska_nedela(Scanner sc) {
        int br_prof=Integer.parseInt(sc.nextLine());
        ArrayQueue<String> proff=new ArrayQueue<>(br_prof);
        for (int i = 0; i < br_prof; i++) {
            String profesor= sc.nextLine();
            proff.enqueue(profesor);
        }
        int br_pred=Integer.parseInt(sc.nextLine());
        ArrayQueue<String> predmeti=new ArrayQueue<>(br_pred);
        for (int i = 0; i < br_pred; i++) {
            String predmet= sc.nextLine();
            predmeti.enqueue(predmet);
        }
        int br_otsutni=Integer.parseInt(sc.nextLine());
        ArrayQueue<String> otsutni_proff=new ArrayQueue<>(br_otsutni);
        for (int i = 0; i < br_otsutni; i++) {
            String ots_profesor= sc.nextLine();
            otsutni_proff.enqueue(ots_profesor);
        }

        while (!predmeti.isEmpty()){
            String []parts= predmeti.dequeue().split("\\s++");
            String predmet=parts[0];
            int br_potrebni=Integer.parseInt(parts[1]);
            System.out.println(predmet);
            System.out.println(br_potrebni);
            int i=0;
            while (i!=br_potrebni){
                String profesor="";
                while (true){
                    profesor=proff.dequeue();
                    proff.enqueue(profesor);
                    if(prisuten(profesor,otsutni_proff))break;
                }
                System.out.println(profesor);
                i++;
            }
        }

    }
    private static boolean prisuten(String profesor, ArrayQueue<String> otsutniProff) {
        boolean prisuten=true;
        int cycle= otsutniProff.size();
        int i=0;
        while (i!=cycle){
            String pro=otsutniProff.dequeue();
            otsutniProff.enqueue(pro);
            if(pro.equals(profesor))prisuten=false;
            i++;
        }




        return prisuten;


    }


    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        
        //RoundRobin(sc);

        //TrainStation(sc);

        //Kolokvium(sc);

        //Konsultacii(sc);

        //Organizacija_ispit(sc);

        //Kolokviumska_nedela(sc);


        sc.close();
    }



}
