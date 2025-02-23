// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.LinkedBlockingQueue;

import static java.lang.Character.isDigit;

/*
interface Stack<E> {

    // Elementi na stekot se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    // Vrakja true ako i samo ako stekot e prazen.

    public E peek ();
    // Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:

    public void clear ();
    // Go prazni stekot.

    public void push (E x);
    // Go dodava x na vrvot na stekot.

    public E pop ();
    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}

class ArrayStack<E> implements Stack<E> {
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack (int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }


    public boolean isEmpty () {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }


    public E peek () {
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth-1];
    }


    public void clear () {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++)  elems[i] = null;
        depth = 0;
    }


    public void push (E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }


    public E pop () {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}
*/


interface Queue<E> {

    // Elementi na redicata se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    // Vrakja true ako i samo ako redicata e prazena.

    public int size ();
    // Ja vrakja dolzinata na redicata.

    public E peek ();
    // Go vrakja elementot na vrvot t.e. pocetokot od redicata.

    // Metodi za transformacija:

    public void clear ();
    // Ja prazni redicata.

    public void enqueue (E x);
    // Go dodava x na kraj od redicata.

    public E dequeue ();
    // Go otstranuva i vrakja pochetniot element na redicata.
}

class ArrayQueue<E> implements Queue<E> {

    // Redicata e pretstavena na sledniot nacin:
    // length go sodrzi brojot na elementi.
    // Ako length > 0, togash elementite na redicata se zachuvani vo elems[front...rear-1]
    // Ako rear > front, togash vo  elems[front...maxlength-1] i elems[0...rear-1]
    E[] elems;
    int length, front, rear;

    // Konstruktor ...

    @SuppressWarnings("unchecked")
    public ArrayQueue(int maxlength) {
        elems = (E[]) new Object[maxlength];
        clear();
    }

    public boolean isEmpty() {
        // Vrakja true ako i samo ako redicata e prazena.
        return (length == 0);
    }

    public int size() {
        // Ja vrakja dolzinata na redicata.
        return length;
    }

    public E peek() {
        // Go vrakja elementot na vrvot t.e. pocetokot od redicata.
        if (length > 0)
            return elems[front];
        else
            throw new NoSuchElementException();
    }

    public void clear() {
        // Ja prazni redicata.
        length = 0;
        front = rear = 0;  // arbitrary
    }

    public void enqueue(E x) {
        // Go dodava x na kraj od redicata.
        elems[rear++] = x;
        if (rear == elems.length) rear = 0;
        length++;
    }

    public E dequeue() {
        // Go otstranuva i vrakja pochetniot element na redicata.
        if (length > 0) {
            E frontmost = elems[front];
            elems[front++] = null;
            if (front == elems.length) front = 0;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }
}


class Gragjanin{
    private String ime_prezime;
    private int lKarta,pasos,vozacka;


    public Gragjanin() {
        lKarta=-1;
        pasos=-1;
        vozacka=-1;
    }

    public Gragjanin(String ime_prezime, int lKarta, int pasos, int vozacka) {
        this.ime_prezime = ime_prezime;
        this.lKarta = lKarta;
        this.pasos = pasos;
        this.vozacka = vozacka;
    }


    public String getIme_prezime() {
        return ime_prezime;
    }

    public void setIme_prezime(String ime_prezime) {
        this.ime_prezime = ime_prezime;
    }

    public int getlKarta() {
        return lKarta;
    }

    public void setlKarta(int lKarta) {
        this.lKarta = lKarta;
    }

    public int getPasos() {
        return pasos;
    }

    public void setPasos(int pasos) {
        this.pasos = pasos;
    }

    public int getVozacka() {
        return vozacka;
    }

    public void setVozacka(int vozacka) {
        this.vozacka = vozacka;
    }
}

class MVR {

    public static void main(String[] args) {

        Scanner br = new Scanner(System.in);



        int N = Integer.parseInt(br.nextLine());


        ArrayQueue <Gragjanin> Licnaredica=new ArrayQueue<Gragjanin>(N);
        ArrayQueue <Gragjanin> Pasosredica=new ArrayQueue<Gragjanin>(N);
        ArrayQueue <Gragjanin> Vozackaredica=new ArrayQueue<Gragjanin>(N);

        for(int i=1;i<=N;i++){
            String imePrezime = br.nextLine();
            int lKarta = Integer.parseInt(br.nextLine());
            int pasos = Integer.parseInt(br.nextLine());
            int vozacka = Integer.parseInt(br.nextLine());
            Gragjanin covek = new Gragjanin(imePrezime,lKarta,pasos,vozacka);

            if(covek.getlKarta()==1){
                Licnaredica.enqueue(covek);
            }
            if(covek.getlKarta()==0 && covek.getPasos()==1){
                Pasosredica.enqueue(covek);
            }
            if(covek.getlKarta()==0 && covek.getPasos()==0 && covek.getVozacka()==1){
                Vozackaredica.enqueue(covek);
            }
            if(Licnaredica.peek().getPasos()==0 && Licnaredica.peek().getVozacka()==0){
                System.out.println(Licnaredica.dequeue().getIme_prezime());
            }

            if(Licnaredica.peek().getPasos()==1){
                Gragjanin gragjanin=Licnaredica.dequeue();
                Pasosredica.enqueue(gragjanin);
            }
            if(Licnaredica.peek().getVozacka()==1 && Licnaredica.peek().getPasos()==0){
                Gragjanin gragjanin=Licnaredica.dequeue();
                Pasosredica.enqueue(gragjanin);
            }
            if(Pasosredica.peek().getVozacka()==0){
                Gragjanin gragjanin=Pasosredica.dequeue();
                System.out.println(gragjanin.getIme_prezime());
            }
            if(Pasosredica.peek().getVozacka()==1){
                Gragjanin gragjanin=Pasosredica.dequeue();
                Vozackaredica.enqueue(gragjanin);
            }

            Gragjanin gr=Vozackaredica.dequeue();
            System.out.println(gr.getIme_prezime());

        }



}}