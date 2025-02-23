package ednodimenzionalni;

import APS_Struct.one_Dimensional_Structures.*;
import APS_Struct.one_Dimensional_Structures.Stack;

import java.util.*;

import static java.lang.Character.isDigit;

public class Ednodimenzionalni_STEK {


    //STACK-STEK
    public static void check_zagradi(String izraz) {
        Stack<Character> stack=new ArrayStack<>(100);
        for (int i = 0; i < izraz.length(); i++) {
            if(izraz.charAt(i)=='(' || izraz.charAt(i)=='[' || izraz.charAt(i)=='{'){
                stack.push(izraz.charAt(i));
            }
            if(izraz.charAt(i)==')'){
                if(stack.peek()=='(')stack.pop();
            }
            if(izraz.charAt(i)==']'){
                if(stack.peek()=='[')stack.pop();
            }
            if(izraz.charAt(i)=='}'){
                if(stack.peek()=='{')stack.pop();
            }

        }

        if (stack.isEmpty()) System.out.println("tocen izraz");
        else System.out.println("NE_tocen izraz");

    }


    private static void eval_postfix(String izraz) {
        Stack<Double> stack=new LinkedStack<>();

        for (int i = 0; i < izraz.length(); i++) {
            char c=izraz.charAt(i);
            if(c==' ')continue;
            if(Character.isDigit(c)){
                stack.push(Double.parseDouble(c+""));
            }
            if(c=='+'){
                double b=stack.pop();
                double a=stack.pop();
                stack.push(a+b);
            }
            if(c=='*'){
                double b=stack.pop();
                double a=stack.pop();
                stack.push(a*b);
            }
            if(c=='-'){
                double b=stack.pop();
                double a=stack.pop();
                stack.push(a-b);
            }
            if(c=='/'){
                double b=stack.pop();
                double a=stack.pop();
                stack.push(a/b);
            }
        }
        System.out.println(stack.peek());
    }


    private static void Quasistack() {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();

        LinkedStack<Integer> stack=new LinkedStack<>();

        for (int i = 0; i < n; i++) {
            int b=sc.nextInt();
            stack.push(b);
        }


        System.out.println("Vrv e:"+stack.peek());
        System.out.println("Dno e:"+stack.Bottom());
        System.out.println(stack);
    }


    private static void Ponisti_topcinja(Scanner sc) {
        String input=sc.nextLine();
        ArrayStack<String> crveni=new ArrayStack<>(100);
        ArrayStack<String> zeleni=new ArrayStack<>(100);
        ArrayStack<String> sini=new ArrayStack<>(100);
        String [] parts=input.split("\\s++");

        int n=0;
        StringBuilder izlez= new StringBuilder();

        for (int i = 0; i < parts.length; i++) {
            String p=parts[i];
            if(p.charAt(0)=='R'){
                if(!crveni.isEmpty()){
                    if(crveni.peek().equals(p))crveni.push(p);
                    else crveni.pop();
                }
                else crveni.push(p);
            }
            if(p.charAt(0)=='G'){
                if(!zeleni.isEmpty()){
                    if(zeleni.peek().equals(p))zeleni.push(p);
                    else zeleni.pop();
                }
                else zeleni.push(p);
            }
            if(p.charAt(0)=='B'){
                if(!sini.isEmpty()){
                    if(sini.peek().equals(p))sini.push(p);
                    else sini.pop();
                }
                else sini.push(p);
            }
        }

        while (!crveni.isEmpty()){
            n++;
            if(crveni.pop().charAt(1)=='+') izlez.append("R-");
            else izlez.append("R+");
        }

        while (!zeleni.isEmpty()){
            n++;
            if(zeleni.pop().charAt(1)=='+') izlez.append("G-");
            else izlez.append("G+");
        }

        while (!sini.isEmpty()){
            n++;
            if(sini.pop().charAt(1)=='+') izlez.append("B-");
            else izlez.append("B+");
        }

        System.out.println(n);
        System.out.println(izlez);





    }


    private static void Molekuli(Scanner sc) {
        String input=sc.nextLine();
        String [] parts=input.split("\\s++");

        ArrayStack<String> hydrogen=new ArrayStack<>(100);
        ArrayStack<String> oxygen=new ArrayStack<>(100);
        int n=0;
        StringBuilder out= new StringBuilder();

        for (int i = 0; i < parts.length; i++) {
            if(parts[i].equals("H"))hydrogen.push(parts[i]);
            if(parts[i].equals("O"))oxygen.push(parts[i]);
        }

        while (!hydrogen.isEmpty()){

            if(hydrogen.size()>=2 && !oxygen.isEmpty()){
                hydrogen.pop();
                hydrogen.pop();
                oxygen.pop();
                n++;
            }
            else {
                while (!hydrogen.isEmpty()) out.append(hydrogen.pop());
                while (!oxygen.isEmpty()) out.append(oxygen.pop());
            }
        }

        System.out.println(n);
        System.out.println(out);
    }


    private static void Inflix_to_Postfix(Scanner sc) {
        String input=sc.nextLine();

        StringBuilder sb=new StringBuilder();

        ArrayStack<Character> operators=new ArrayStack<>(100);

        for (char c : input.toCharArray()) {
            if(Character.isLetterOrDigit(c))sb.append(c);
            else if(c=='(')operators.push(c);
            else if(c==')'){
                while (!operators.isEmpty() && operators.peek()!='(')sb.append(operators.pop());
                operators.pop();
            }
            else {
                while (!operators.isEmpty() && priority(c)<=priority(operators.peek())){
                    sb.append(operators.pop());
                }
                operators.push(c);
            }
        }

        while (!operators.isEmpty()){
            sb.append(operators.pop());
        }

        System.out.println(sb);


    }
    private static int priority(char opp) {
        return switch (opp) {
            case '^' -> 3;
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            default -> 0;
        };
    }


    private static void Double_brackets_test(String input) {
        //String input=sc.nextLine();
        //depth before putting the zagradi at once;
        int depthBefore=-10;
        boolean dupli=false;
        boolean promena=false;
        ArrayStack<Character> stack=new ArrayStack<>(100);
        for (int i = 0; i < input.length(); i++) {
            if(i!=input.length()-1){
                if(input.charAt(i)=='(' && input.charAt(i+1)=='('){
                    promena=false;
                    depthBefore=stack.size();
                    stack.push(input.charAt(i));
                    stack.push(input.charAt(i+1));
                    i++;
                    continue;
                }

                if(input.charAt(i)==')' && input.charAt(i+1)==')'){
                    if(stack.peek()=='('){
                        stack.pop();
                        if(stack.peek()=='('){
                            stack.pop();
                            if(stack.size()==depthBefore && !promena)dupli=true;
                        }
                    }
                    i++;
                    continue;
                }

            }

            if(input.charAt(i)=='('){
                stack.push(input.charAt(i));
                if(depthBefore!=-10 && depthBefore!=stack.size())promena=true;
            }
            if(input.charAt(i)==')'){
                stack.pop();
            }

        }

        if(stack.isEmpty()) System.out.println("Stack is empty");
        else System.out.println("Stack is NOT empty");
        if(dupli) System.out.println("ans-TRUE");
        else System.out.println("ans-FALSE");


    }


    private static void Poisonous_plants(Scanner sc) {
        int n=sc.nextInt();
        ArrayStack<Integer> stack=new ArrayStack<>(n+1);
        for (int i = 0; i < n; i++) {
            int broj=sc.nextInt();
            stack.push(broj);
        }


        ArrayStack<Integer> sec_stack=new ArrayStack<>(n+1);

        int k=0;
        int orig_size=stack.size();
        int last=0;


        while (true){

            while (!stack.isEmpty())sec_stack.push(stack.pop());

            while (!sec_stack.isEmpty()){
                int br=sec_stack.pop();
                if(stack.isEmpty()){stack.push(br);}
                else {
                    if(stack.peek()>=br)stack.push(br);
//                    else {
//                        if(last>br)stack.push(br);
//                        last=br;
//                    }


                }
            }



            if(stack.size()==orig_size)break;

            orig_size=stack.size();
            k++;
        }


        System.out.println(k);


    }


    private static void Ispitna_sesija(Scanner sc) {
        int n=sc.nextInt();
        int m= sc.nextInt();
        String e_book="";

        Stack<String> books =new ArrayStack<>(n+1);
        Stack<String> altbooks =new ArrayStack<>(n+1);
        List<String> exams=new ArrayList<>();

        Map<String,Integer> map=new LinkedHashMap<>();

        System.out.println("What books");
        for (int i = 0; i < n; i++) {
            String book=sc.next();
            books.push(book);
            map.put(book,0);
        }
        System.out.println("What exams");
        for (int i = 0; i < m; i++) {
            String exam=sc.next();
            exams.add(exam);
        }

        for (String exam : exams) {
            while (!books.isEmpty()){
                String book=books.pop();
                altbooks.push(book);
                if(book.equals(exam)){e_book=book;break;}

            }
            while (!altbooks.isEmpty()){
                String book=altbooks.pop();
                map.put(book,map.get(book)+1);
                if(!book.equals(e_book))books.push(book);
            }
            books.push(e_book);
        }
        for (String s : map.keySet()) {
            System.out.println(s+" "+map.get(s));
        }


    }


    private static void dance_pairs(Scanner sc) {
        String line=sc.nextLine();
        String [] parts=line.split("\\s++");
        int n=parts.length;

        List<String> lista=new ArrayList<>(List.of(parts));

        ArrayStack<String> osnovni=new ArrayStack<>(n+1);
        ArrayStack<String> standard=new ArrayStack<>(n+1);
        ArrayStack<String> latino=new ArrayStack<>(n+1);

        StringBuilder lone= new StringBuilder();

        for (String part : parts) {
            if(part.charAt(0)=='O'){
                osnovni.push(part);
            }

            if(part.charAt(0)=='S'){
                standard.push(part);
            }

            if(part.charAt(0)=='L'){
                latino.push(part);
            }
        }

        while (!osnovni.isEmpty()){
            String osn=osnovni.pop();
            char pol=osn.charAt(1);
            if(pol=='Z')if(!lista.remove("OM"))lone.append(osn).append(" ");
            if(pol=='M')if(!lista.remove("OZ"))lone.append(osn).append(" ");
        }

        while (!standard.isEmpty()){
            String stand=standard.pop();
            char pol=stand.charAt(1);
            if(pol=='Z')if(!lista.remove("SM"))lone.append(stand).append(" ");
            if(pol=='M')if(!lista.remove("SZ"))lone.append(stand).append(" ");

        }

        while (!latino.isEmpty()){
            String lat=latino.pop();
            char pol=lat.charAt(1);
            if(pol=='Z')if(!lista.remove("LM"))lone.append(lat).append(" ");
            if(pol=='M')if(!lista.remove("LZ"))lone.append(lat).append(" ");
        }



        String []alones=lone.toString().split("\\s++");
        int a=alones.length;
        StringBuilder sb_out= new StringBuilder();
        for (String alone : alones) {
            if(alone.charAt(1)=='M') sb_out.append(alone.charAt(0)).append("Z").append(" ");
            if(alone.charAt(1)=='Z') sb_out.append(alone.charAt(0)).append("M").append(" ");

        }

        System.out.println(a);
        System.out.println(sb_out);


    }


    private static void topcinja_game(Scanner sc) {
        int n=sc.nextInt();

        ArrayStack<String> box_1=new ArrayStack<>(n);
        for (int i = 0; i < n; i++) {
            String ball=sc.next();
            box_1.push(ball);
        }

        int bomb2=0;
        int bomb=0;
        ArrayStack<String> box_2=new ArrayStack<>(n);
        ArrayStack<String> box=new ArrayStack<>(n);

        while (!box_1.isEmpty()){

            String ball=box_1.pop();

            if(!box.isEmpty()){
                if(ball.equals("R")){
                    bomb++;
                    if(bomb==3){
                        box.pop();
                        box.pop();
                        bomb=0;
                    }
                    else box.push(ball);

                }
                else {
                    box_2.push(ball);
                }


            }
            else {
                if(ball.equals("R"))box.push(ball);
                else box_2.push(ball);

            }






        }


        while (!box_2.isEmpty()){
            String popped=box_2.pop();

            if(popped.equals(box.peek()))box.push(popped);
            else {
                if(box_1.isEmpty() && popped.equals("G"))box.push(popped);
                else box_1.push(popped);
            }

        }

        while (!box_1.isEmpty()){
            box.push(box_1.pop());
        }



        while (!box.isEmpty()){
            box_1.push(box.pop());
        }

        bomb=0;
        while (!box_1.isEmpty()){
            String ball=box_1.pop();
            if(ball.equals("R")){
                bomb++;
                if(bomb==3){
                    box.pop();
                    box.pop();
                    bomb=0;
                }
                else box.push(ball);

            }
            else box.push(ball);


        }




        while (!box.isEmpty()){
            String ball=box.pop();
            System.out.print(ball+" ");
        }

    }


    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        //String izraz=sc.nextLine();

        //check_zagradi(izraz);

        //eval_postfix(izraz);

        //Quasistack();

        //Ponisti_topcinja(sc);

        //Molekuli(sc);

        //Inflix_to_Postfix(sc);

//        List<String> expressions=new ArrayList<>();
//        expressions.add("((a+b))");
//        expressions.add("((c+d))+(e+f)");
//        expressions.add("(a+b)+(c+d)");
//        expressions.add("(((x+y))) + (z+w)");
//        expressions.add("((a+b) + (c+d))");
//        expressions.add("a+b+c");
//        expressions.add("(a) + (b)");
//        expressions.add("((a+b)) + ((c+d)) + ((e+f))");
//        expressions.add("(a+b)+((c+d)");
//        for (String expression : expressions) {
//            System.out.println(expression);
//            Double_brackets_test(expression);
//            System.out.println("\n");
//        }

        //Poisonous_plants(sc);

        //Ispitna_sesija(sc);

        //dance_pairs(sc);

        //topcinja_game(sc);







        sc.close();
    }






}




