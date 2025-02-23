package APS_Struct.hash_tables;

import java.io.IOException;
import java.sql.SQLOutput;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
//52 after pred obht
public class TESTING_HASH {

    private static void BirthDays(Scanner sc) {
        int n=Integer.parseInt(sc.nextLine());
        /*
        CBHT<Integer,Integer>cbht=new CBHT<>((int)(n*1.33333));
        for (int i = 0; i < n; i++) {
            String s=sc.nextLine();
            String[] parts=s.split("\\.");
            int month=Integer.parseInt(parts[1]);
            if(cbht.search(month)==null){
                cbht.insert(month,1);
            }
            else {
                MapEntry<Integer,Integer> entry=cbht.search(month).element;
                cbht.insert(month, entry.value+1);
            }
        }


         */


        CBHT<Integer,ArrayList<String>>cbht=new CBHT<>((int)(n*1.33333));
        for (int i = 0; i < n; i++) {
            String [] parts=sc.nextLine().split("\\s+");
            String name=parts[0];
            int month=Integer.parseInt(parts[1].split("\\.")[1]);

            if(cbht.search(month)==null){
                ArrayList<String> arr=new ArrayList<>();
                arr.add(name);
                cbht.insert(month,arr);
            }
            else {
                if(!cbht.search(month).element.value.contains(name))cbht.search(month).element.value.add(name);
            }

        }
        int desired_month=sc.nextInt();

        //
        SLLNode<MapEntry<Integer,ArrayList<String>>> node=cbht.getFirst(desired_month);
        if(node==null) System.out.println("empty");
        else {
            while (node!=null){
                for (String s : node.element.value) {
                    System.out.print(s+" ");
                }
                System.out.println();
                node=node.succ;
            }
        }

        //System.out.println("Birthdays in "+desired_month+" total are: "+cbht.search(desired_month).element.value);

    }


    private static void BestOffer(Scanner sc) {
        int n=Integer.parseInt(sc.nextLine());
        CBHT<String, ArrayList<MapEntry<String,Integer>>>cbht=new CBHT<>(n);
        //27/01/2016 14:00 NewYork 6000
        for (int i = 0; i < n; i++) {
            String []line_parts=sc.nextLine().split("\\s++");
            String date=line_parts[0];
            String info=line_parts[1]+" "+line_parts[2]+" "+line_parts[3];
            int price=Integer.parseInt(line_parts[3]);
            if(cbht.search(date)==null){
                ArrayList<MapEntry<String,Integer>> arr=new ArrayList<>();
                MapEntry<String,Integer> entry=new MapEntry<>(info,price);
                arr.add(entry);
                cbht.insert(date,arr);
            }
            else {
                MapEntry<String,Integer> entry=new MapEntry<>(info,price);
                cbht.search(date).element.value.add(entry);
            }

        }

        String desired_date=sc.nextLine();
        ArrayList<MapEntry<String,Integer>> events=cbht.search(desired_date).element.value;
        events.sort(Comparator.comparing(MapEntry::getValue,Comparator.reverseOrder()));
        System.out.println(events.getFirst().key);
    }


    private static void Anagrams(Scanner sc) {
        int n=sc.nextInt();
        CBHT<String,Integer>cbht=new CBHT<>((int)(n*1.333333));
        for (int i = 0; i < n; i++) {
            String s=sc.next();
            char[] chars=s.toCharArray();
            Arrays.sort(chars);
            String anagram=new String(chars);

            if(cbht.search(anagram)==null){
                cbht.insert(anagram,1);
            }
            else {
                int res=cbht.search(anagram).element.value;
                cbht.insert(anagram,res+1);
            }
        }


        String des_anagram=sc.next();
        char[] chars=des_anagram.toCharArray();
        Arrays.sort(chars);
        String desired_anagram=new String(chars);
        System.out.println("Anagrams count of "+des_anagram+" is : "+cbht.search(desired_anagram).element.value);
    }


    private static void Epidemic(Scanner sc) {
        int n=Integer.parseInt(sc.nextLine());
        CBHT<String,Integer> positive=new CBHT<>((int)(n*1.2));
        CBHT<String,Integer> negative=new CBHT<>((int)(n*1.2));
        for (int i = 0; i < n; i++) {
            String []parts=sc.nextLine().split("\\s++");
            String opstina=parts[0];
            String rezultat=parts[2];

            if(rezultat.equals("positive")){
                if(positive.search(opstina)==null){
                    positive.insert(opstina,1);
                }
                else {
                    int res=positive.search(opstina).element.value;
                    positive.insert(opstina,res+1);
                }
            }
            else {
                if(negative.search(opstina)==null){
                    negative.insert(opstina,1);
                }
                else {
                    int res=negative.search(opstina).element.value;
                    negative.insert(opstina,res+1);
                }
            }
        }

        String barana_opstina=sc.nextLine();
        float pos=positive.search(barana_opstina).element.value;
        int neg=negative.search(barana_opstina).element.value;
        float risk_factor=  pos /(pos+neg);
        System.out.printf("%.2f",risk_factor);

    }


    static class Name implements Comparable<Name>{
        String name;

        public Name(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(Name o) {
            return name.compareTo(o.name);
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Name name1 = (Name) o;
            return Objects.equals(name, name1.name);
        }

        @Override
        public int hashCode() {
            return (100* name.charAt(0) + name.charAt(1));
        }
    }
    private static void names_Occurrence(Scanner sc) {
        CBHT<Name,Integer> Male_list=new CBHT<>(9091);
        CBHT<Name,Integer> Female_list=new CBHT<>(9091);
        int n=Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            String []parts=sc.nextLine().split("\\s++");
            Name name=new Name(parts[0].toUpperCase());
            String pol=parts[1];
            if(pol.equals("M")){
                if(Male_list.search(name)==null){
                    Male_list.insert(name,1);
                }
                else {
                    int res=Male_list.search(name).element.value;
                    Male_list.insert(name,res+1);
                }
            }
            else if(pol.equals("F")){
                if(Female_list.search(name)==null){Female_list.insert(name,1);}
                else {
                    int res=Female_list.search(name).element.value;
                    Female_list.insert(name,res+1);
                }
            }
        }

        String pol= sc.nextLine();
        String name_cyc=sc.nextLine().toUpperCase();
        while (!name_cyc.equals("END")){
            if(pol.equals("M")){
                SLLNode<MapEntry<Name,Integer>> node=Male_list.getFirst(new Name(name_cyc));
                while (node!=null){
                    System.out.println(node.element.key.getName());
                    node=node.succ;
                }

                if(Male_list.search(new Name(name_cyc))==null) {
                    System.out.println("No such name");
                }
                else {
                    MapEntry<Name,Integer> entry=Male_list.search(new Name(name_cyc)).element;
                    System.out.println(entry.key+" "+entry.value);
                }
                name_cyc= sc.nextLine().toUpperCase();
            }
            else if(pol.equals("F")){
                SLLNode<MapEntry<Name,Integer>> node=Female_list.getFirst(new Name(name_cyc));
                while (node!=null){
                    System.out.println(node.element.key.getName());
                    node=node.succ;
                }
                System.out.println("These upper start with the same 2 letters ^^^^^^");
                if(Female_list.search(new Name(name_cyc))==null) {
                    System.out.println("No such name");
                }
                else {
                    MapEntry<Name,Integer> entry=Female_list.search(new Name(name_cyc)).element;
                    System.out.println(entry.key+" "+entry.value);
                }
                name_cyc= sc.nextLine().toUpperCase();

            }

        }
    }


    static class Drug {
        String name;
        int price;
        int positivity;
        int quantity;

        public Drug(String name, int price, int positivity, int quantity) {
            this.name = name.toUpperCase();
            this.price = price;
            this.positivity = positivity;
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            if(positivity==1) return name+" "+"POS"+" "+price+" "+quantity;
            else return name+" "+"NEG"+" "+price+" "+quantity;
        }
    }
    static class Drug_Name implements Comparable<Drug_Name>{
        String name;

        public Drug_Name(String name) {
            this.name = name.toUpperCase();
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Drug_Name drugName = (Drug_Name) o;
            return Objects.equals(name, drugName.name);
        }

        @Override
        public int hashCode() {
            return (100*(100*( name.charAt(3))+name.charAt(2))+name.charAt(1));
        }

        @Override
        public int compareTo(Drug_Name o) {
            return this.name.compareTo(o.name);
        }
    }
    private static void Pharmacy(Scanner sc) {
        int n=Integer.parseInt(sc.nextLine());
        CBHT<Drug_Name,Drug> drug_table=new CBHT<>(656565);
        for (int i = 0; i < n; i++) {
            String[] parts=sc.nextLine().split("\\s+");
            Drug_Name d_name=new Drug_Name(parts[0]);
            int positifity=Integer.parseInt(parts[1]);
            int price=Integer.parseInt(parts[2]);
            int quantity=Integer.parseInt(parts[3]);
            Drug new_drug=new Drug(parts[0],price,positifity,quantity);
            drug_table.insert(d_name,new_drug);
        }

        System.out.println("Vnesi naracki");

        String order=sc.nextLine().toUpperCase();

        while (!order.equals("END")){
            int quantity=Integer.parseInt(sc.nextLine());

            SLLNode<MapEntry<Drug_Name,Drug>> node =drug_table.search(new Drug_Name(order));
            if(node==null){
                System.out.println("No such drug");
                order = sc.nextLine().toUpperCase();
            }
            else if(node.element.value.name.equals(order)){
                System.out.println(node.element.value);
                if(node.element.value.quantity<quantity){
                    System.out.println("No drugs available");
                }
                else {
                    int res_quantity=node.element.value.quantity;
                    res_quantity-=quantity;
                    node.element.value.quantity=res_quantity;
                    drug_table.insert(new Drug_Name(order),node.element.value);
                    System.out.println("Order made");
                }
                order = sc.nextLine().toUpperCase();

            }
            else order=sc.nextLine().toUpperCase();
        }

    }


    private static void Number_contacts(Scanner sc) {
        int n=Integer.parseInt(sc.nextLine());
        CBHT<String,String> contacts=new CBHT<>((int)(n*1.33333));
        for (int i = 0; i < n; i++) {
            //070111222 IvanIvanoski
            String []parts=sc.nextLine().split("\\s+");
            String number_=parts[0].substring(1);
            String contact_name=parts[1];
            contacts.insert(number_,contact_name);
        }

        System.out.println("vnesi calling number");

        String calling_number=sc.nextLine();
        if(calling_number.contains("+389")){
            calling_number=calling_number.substring(4);
        }
        else calling_number=calling_number.substring(1);

        if(contacts.search(calling_number)==null) System.out.println("Number Doesn't exist");
        else System.out.println(contacts.search(calling_number).element.value);
    }


    static class Temp implements Comparable<Temp>{
        String period;
        float temperatura;

        public Temp(String period, float temperatura) {
            this.period = period;
            this.temperatura = temperatura;
        }

        @Override
        public String toString() {
            return period + " " + temperatura;
        }

        @Override
        public int compareTo(Temp o) {
            return Float.compare(this.temperatura,o.temperatura);
        }
    }
    private static void Temperature(Scanner sc) {
        int n=Integer.parseInt(sc.nextLine());
        CBHT<String,ArrayList<Temp>> temp_table=new CBHT<>((int)(n*1.33333));
        for (int i = 0; i < n; i++) {
            //Ohrid,Macedonia 10:00 12:00 23.1
            String []parts=sc.nextLine().split("\\s+");
            String location=parts[0];
            String period=parts[1]+" "+parts[2];
            float temperature=Float.parseFloat(parts[3]);
            Temp tempp=new Temp(period,temperature);

            if(temp_table.search(location)==null){
                ArrayList<Temp> arr=new ArrayList<>();
                arr.add(tempp);
                temp_table.insert(location,arr);
            }
            else {
                temp_table.search(location).element.value.add(tempp);
            }
        }

        String loakicja=sc.nextLine();
        if(temp_table.search(loakicja)==null) System.out.println(loakicja+": does not exist");
        else {
            ArrayList<Temp> array=temp_table.search(loakicja).element.value;


            /*
            array.sort(Comparator.reverseOrder());
            System.out.println(array.getFirst());
             @ Voa e istata zadaca ama prethodno se barase najdobrata a sega site se printat;
             */

            array.sort(Comparator.naturalOrder());
            for (Temp temp : array) {
                System.out.println(temp);
            }

        }
    }


    private static void Border_Crossing(Scanner sc) {
        int n=Integer.parseInt(sc.nextLine());
        CBHT<String,String> passport_map=new CBHT<>(n*2);
        for (int i = 0; i < n; i++) {
            String []parts=sc.nextLine().split("\\s++");
            String pass_id=parts[0];
            String pass_name=parts[1];
            passport_map.insert(pass_id,pass_name);
        }
        int m=Integer.parseInt(sc.nextLine());
        CBHT<String,String> changed_name_map=new CBHT<>(m*2);
        for (int i = 0; i < m; i++) {
            String []parts=sc.nextLine().split("\\s++");
            String old_name=parts[0];
            String new_name=parts[1];
            changed_name_map.insert(old_name,new_name);
        }

        String searched_password=sc.nextLine();
        if (passport_map.search(searched_password)==null) {
            System.out.println("Password doesn't exist");
        }
        else {
            String pass_name=passport_map.search(searched_password).element.value;
            if(changed_name_map.search(pass_name)==null) System.out.println("Allowed");
            else System.out.println("Not Allowed");

        }



    }


    static class Lek implements Comparable<Lek>{
        //Analgin@Headache@80
        String name;
        int price;

        public Lek(String name, int price) {
            this.name = name;
            this.price = price;
        }


        @Override
        public String toString() {
            return name;
        }

        @Override
        public int compareTo(Lek o) {
            return Integer.compare(this.price,o.price);
        }
    }
    private static void Magazine(Scanner sc) {
        int n=Integer.parseInt(sc.nextLine());
        CBHT<String,ArrayList<Lek>> lekovi=new CBHT<>(n*2);
        for (int i = 0; i < n; i++) {
            String []parts=sc.nextLine().split("@");
            //Analgin@Headache@80
            String name=parts[0];
            String purpose=parts[1];
            int price=Integer.parseInt(parts[2]);
            if(lekovi.search(purpose)==null){
                ArrayList<Lek> arr=new ArrayList<>();
                arr.add(new Lek(name,price));
                lekovi.insert(purpose,arr);
            }
            else {
                lekovi.search(purpose).element.value.add(new Lek(name,price));
            }
        }

        String potreba=sc.nextLine();
        if(lekovi.search(potreba)==null) System.out.println("Nemame takvi lekovi");
        else {
            ArrayList<Lek> drugs_arr=lekovi.search(potreba).element.value;
            drugs_arr.sort(Comparator.naturalOrder());
            System.out.println(drugs_arr.getFirst());
        }

    }


    static class File_OBHT implements Comparable<File_OBHT>{
        String name;
        String content;

        public File_OBHT(String name, String content) {
            this.name = name;
            this.content = content;
        }

        @Override
        public String toString() {
            return name +" "+ content;
        }

        @Override
        public int compareTo(File_OBHT o) {
            return this.name.compareTo(o.name);
        }
    }
    private static void Data_files(Scanner sc) {
        int n=Integer.parseInt(sc.nextLine());
        CBHT<String,ArrayList<File_OBHT>> files=new CBHT<>(n*2);
        for (int i = 0; i < n; i++) {
            String []parts=sc.nextLine().split("\\s++");
            String path=parts[0];
            String file_name=parts[1];
            String content=parts[2].substring(1, parts[2].length()-1);
            File_OBHT file=new File_OBHT(file_name,content);
            if(files.search(path)==null) {
                ArrayList<File_OBHT> arr = new ArrayList<>();
                arr.add(file);
                files.insert(path, arr);
            }
            else {
                files.search(path).element.value.add(file);
            }
        }
        int m=Integer.parseInt(sc.nextLine());
        for (int i = 0; i < m; i++) {

            String []parts= sc.nextLine().split("\\s++");
            String command=parts[0];
            String path=parts[1];
            String file_name=parts[2];
            String content=parts[3].substring(1,parts[3].length()-1);
            if(command.equals("add")){
                File_OBHT file=new File_OBHT(file_name,content);
                if(files.search(path)==null) {
                    ArrayList<File_OBHT> arr = new ArrayList<>();
                    arr.add(file);
                    files.insert(path, arr);
                }
                else files.search(path).element.value.add(file);
            }
            if(command.equals("delete")){
                File_OBHT file=new File_OBHT(file_name,content);
                for (File_OBHT file1 : files.search(path).element.value) {
                    if(file1.name.equals(file.name) && file1.content.equals(file.content)){files.search(path).element.value.remove(file1);break;}
                }
            }
            if(command.equals("find")){
                File_OBHT file=new File_OBHT(file_name,content);
                if(files.search(path)==null) System.out.println("false");
                else {
                    ArrayList<File_OBHT> arr=files.search(path).element.value;
                    boolean flag=false;
                    for (File_OBHT file1 : arr) {
                        if(file1.name.equals(file.name) && file1.content.equals(file.content)){
                            System.out.println("true");
                            flag=true;
                            break;
                        }
                    }
                    if(!flag) System.out.println("false");

                }
            }
        }

        String baran_content=sc.nextLine();

        for (SLLNode<MapEntry<String, ArrayList<File_OBHT>>> bucket : files.getBuckets()) {
            if(bucket!=null){
                for (File_OBHT file : bucket.element.value) {
                    if(file.content.equals(baran_content)) System.out.println(bucket.element.key+" "+file.name);
                }
            }

        }

    }


    ///////////////////     Problems with OBHT      ///////////////////

    private static void Red_Cross(Scanner sc) {
        int n=Integer.parseInt(sc.nextLine());
        OBHT<String,Integer> blood_types=new OBHT<>(n*2);
        for (int i = 0; i < n; i++) {
            String []parts=sc.nextLine().split("\\s++");
            //String name=parts[0];
            String b_type=parts[1].charAt(0)+""+parts[1].charAt(parts[1].length()-1);
            if(blood_types.search(b_type)==-1){
                blood_types.insert(b_type,1);
            }
            else {
                int idx=blood_types.search(b_type);
                blood_types.getBucket(idx).value++;
            }
        }
        System.out.println(blood_types);
    }


    static class Air_measure implements Comparable<Air_measure>{
        String location;
        Float concentration;

        public Air_measure(String location, Float concentration) {
            this.location = location;
            this.concentration = concentration;
        }

        @Override
        public int compareTo(Air_measure o) {
            return Float.compare(this.concentration,o.concentration);
        }
    }
    private static void Air_quality(Scanner sc) {
        int n=Integer.parseInt(sc.nextLine());
        OBHT<String,ArrayList<Air_measure>> measures=new OBHT<>(n*2);
        for (int i = 0; i < n; i++) {
            //Centar 319.61
            String []parts=sc.nextLine().split("\\s++");
            String location_=parts[0];
            Float concentration_=Float.parseFloat(parts[1]);
            Air_measure measure_=new Air_measure(location_,concentration_);
            if(measures.search(location_)==-1){
                ArrayList<Air_measure> arr=new ArrayList<>();
                arr.add(measure_);
                measures.insert(location_,arr);
            }
            else {
                int idx=measures.search(location_);
                measures.getBucket(idx).value.add(measure_);
            }
        }

        String barana_loc=sc.nextLine();

        if(measures.search(barana_loc)==-1){
            System.out.println("No info");
        }
        else {
            float sum=0;
            int idx=measures.search(barana_loc);
            ArrayList<Air_measure> arr=measures.getBucket(idx).value;
            int num=arr.size();
            for (Air_measure measure : arr) {
                sum+=measure.concentration;
            }
            sum=sum/num;
            System.out.printf("%.2f",sum);
        }
    }


    static class People{

        String name;
        int Birth_year;

        public People(String name, int birth_year) {
            this.name = name;
            Birth_year = birth_year;
        }

    }
    private static void BirthDays_OBHT(Scanner sc) {
        int n=Integer.parseInt(sc.nextLine());
        OBHT<String,ArrayList<People>> birthdays=new OBHT<>(n*2);
        for (int i = 0; i < n; i++) {
            //Ivana Ivanovska 15/05/1982
            String []parts=sc.nextLine().split("\\s++");
            String name=parts[0]+" "+parts[1];
            String []dates=parts[2].split("/");
            String date=dates[0]+"/"+dates[1];
            int b_year=Integer.parseInt(dates[2]);

            People people=new People(name,b_year);

            if(birthdays.search(date)==-1){
                ArrayList<People> arr=new ArrayList<>();
                arr.add(people);
                birthdays.insert(date,arr);
            }
            else {
                int idx=birthdays.search(date);
                birthdays.getBucket(idx).value.add(people);
            }
        }

        String inputed_date=sc.nextLine();
        String []dates=inputed_date.split("/");
        String i_date=dates[0]+"/"+dates[1];
        int i_year=Integer.parseInt(dates[2]);
        if(birthdays.search(i_date)==-1) System.out.println("empty");
        else {
            int idx=birthdays.search(i_date);
            ArrayList<People>arr=birthdays.getBucket(idx).value;
            for (People people : arr) {
                int age=i_year-people.Birth_year;
                System.out.println(people.name+" "+age);
            }
        }
    }


    static class Word implements Comparable<Word>{
        String word;

        public Word(String word) {
            this.word = word;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Word word1 = (Word) o;
            return Objects.equals(word, word1.word);
        }

        @Override
        public int hashCode() {
            int hash=0;
            for (int i = 0; i < word.length(); i++) {
                hash += word.charAt(i); //Збир на ASCII вредностите на сите карактери
            }
            hash+=word.length(); //Должина на зборот

            return hash;
            //return Objects.hash(word);
        }

        @Override
        public String toString() {
            return word;
        }

        @Override
        public int compareTo(Word o) {
            return word.compareTo(o.word);
        }
    }
    private static void Spellcheck(Scanner sc) {
        int n=Integer.parseInt(sc.nextLine());
        OBHT<String,Word> dictionary=new OBHT<>(n*2);
        for (int i = 0; i < n; i++) {
            String word=sc.nextLine();
            word=word.replaceAll("\\.","").replaceAll(",","").replaceAll("\\?","").replaceAll("!","").toLowerCase();
            Word w=new Word(word);
            if(dictionary.search(word)==-1){
                dictionary.insert(word,w);
            }
        }

        String input_t= sc.nextLine();
        String []parts=input_t.split("\\s++");
        int count=0;
        for (String part : parts) {
            part=part.replaceAll("\\.","").replaceAll(",","").replaceAll("\\?","").replaceAll("!","");
            String word=part.toLowerCase();

            if(dictionary.search(word)==-1) System.out.println(part);
            else {
                count++;
            }
        }
        if(count== parts.length) System.out.println("Bravo site zborovi se pogodeni");

    }


    private static void College_applications(Scanner sc) {
        int n=Integer.parseInt(sc.nextLine());
        OBHT<String,Float> upisi=new OBHT<>(n*2);
        for (int i = 0; i < n; i++) {
            String []parts=sc.nextLine().split("\\s++");
            String EMBG=parts[0];
            Float prosek=Float.parseFloat(parts[1]);
            upisi.insert(EMBG,prosek);
        }
        int m=Integer.parseInt(sc.nextLine());
        OBHT<String,Float> e_denvnik=new OBHT<>(m*2);
        for (int i = 0; i < m; i++) {
            String []parts=sc.nextLine().split("\\s++");
            String EMBG=parts[0];
            Float prosek=Float.parseFloat(parts[1]);
            e_denvnik.insert(EMBG,prosek);
        }
        String baraniot=sc.nextLine();
        if(upisi.search(baraniot)==-1) System.out.println("Covekot go nema na spisok za upis bre");
        else {
            int idx=upisi.search(baraniot);
            float u_prosek=upisi.getBucket(idx).value;
            if(e_denvnik.search(baraniot)==-1) System.out.println("Empty");
            else {
                int idx1=e_denvnik.search(baraniot);
                float real_prosek=e_denvnik.getBucket(idx1).value;
                if(real_prosek==u_prosek) System.out.println("OK");
                else System.out.println("Error");
            }
        }

    }


    static class Driver implements Comparable<Driver>{
        String driver_name;
        Date time;

        public Driver(String driver_name, Date time) {
            this.driver_name = driver_name;
            this.time = time;
        }

        @Override
        public int compareTo(Driver o) {
            return time.compareTo(o.time);
        }

        @Override
        public String toString() {
            return driver_name;
        }
    }
    private static void Traffic_lights(Scanner sc) throws ParseException {
        int n= Integer.parseInt(sc.nextLine());
        OBHT<String,String> tablicki=new OBHT<>(n*2);
        for (int i = 0; i < n; i++) {
            //SK1234AA Anita Angelovska
            String [] parts=sc.nextLine().split("\\s++");
            String tablica=parts[0];
            String name=parts[1];
            String surname=parts[2];
            tablicki.insert(tablica,name+" "+surname);
        }
        ArrayList<Driver> drivers=new ArrayList<>();
        SimpleDateFormat formatter=new SimpleDateFormat("HH:mm:ss");
        int speed=Integer.parseInt(sc.nextLine());
        //SK8888KD 48 14:00:00 | ST0000AB 55 12:00:02 | ST0989OO 60 08:10:00 | SK1234AA 65 20:00:10 | OH1212BE 50 22:00:21
        String []trafic=sc.nextLine().split("\\s++");
        for (int i = 0; i < trafic.length-2; i+=3) {
            String tablica=trafic[i];
            int brzina=Integer.parseInt(trafic[i+1]);
            String time=trafic[i+2];
            if(brzina>speed){
                Date d=formatter.parse(time);
                String driver_name=tablicki.getBucket(tablicki.search(tablica)).value;
                Driver driver=new Driver(driver_name,d);
                drivers.add(driver);
            }

        }

        drivers.sort(Comparator.naturalOrder());
        for (Driver driver : drivers) {
            System.out.println(driver);
        }
    }


    private static void Calling_num(Scanner sc) {
        int n=Integer.parseInt(sc.nextLine());
        OBHT<Integer,String> povikuvacki_broevi=new OBHT<>(n*2);
        for (int i = 0; i < n; i++) {
            String []parts=sc.nextLine().split("\\s++");
            int broj=Integer.parseInt(parts[0]);
            String drzava=parts[1];
            povikuvacki_broevi.insert(broj,drzava);
        }
        String dzvoni=sc.nextLine().substring(1);
        if(dzvoni.charAt(0)=='1'){
            int pov_b=Integer.parseInt(dzvoni.substring(0,1));
            System.out.println(povikuvacki_broevi.getBucket(povikuvacki_broevi.search(pov_b)).value);
        }
        if(dzvoni.charAt(0)=='2'){
            //String sub1=dzvoni.substring(0,1);
            String sub2=dzvoni.substring(0,2);
            //String sub3=dzvoni.substring(0,3);
            int pov_b=Integer.parseInt(sub2);
            System.out.println(povikuvacki_broevi.getBucket(povikuvacki_broevi.search(pov_b)).value);
        }
        if(dzvoni.charAt(0)=='3'){
            int pov_b=Integer.parseInt(dzvoni.substring(0,3));
            System.out.println(povikuvacki_broevi.getBucket(povikuvacki_broevi.search(pov_b)).value);
        }

    }


    private static void Criminals(Scanner sc) {
        int n=Integer.parseInt(sc.nextLine());
        OBHT<String,String> otpecatoci=new OBHT<>(29);
        for (int i = 0; i < n; i++) {
            String name=sc.nextLine();
            String o1=sc.nextLine();
            String o2=sc.nextLine();
            String otpecatok=o1+o2;
            otpecatoci.insert(otpecatok,name);
        }

        String o1= sc.nextLine();
        String o2=sc.nextLine();
        String baran=o1+o2;
        if(otpecatoci.search(baran)==-1) System.out.println("Unknown");
        else {
            System.out.println(otpecatoci.getBucket(otpecatoci.search(baran)).value);
        }
    }


    private static void Permutations(Scanner sc) {
        int n=Integer.parseInt(sc.nextLine());
        OBHT<String,Integer> permutaciii=new OBHT<>(n*2);
        for (int i = 0; i < n; i++) {
            String per=sc.nextLine();
            char[]chars=per.toCharArray();
            Arrays.sort(chars);
            String permm=new String(chars);

            if(permutaciii.search(permm)==-1){
                permutaciii.insert(permm,1);
            }
            else {
                permutaciii.getBucket(permutaciii.search(permm)).value++;
            }
        }

        String i_per=sc.nextLine();
        char[]chars=i_per.toCharArray();
        Arrays.sort(chars);
        String i_permm=new String(chars);

        if(permutaciii.search(i_permm)==-1) System.out.println("Njamaaa");
        else {
            System.out.println(permutaciii.getBucket(permutaciii.search(i_permm)).value);
        }
    }


    static class Event implements Comparable<Event>{

        String info;
        Date time;

        public Event(String info, Date time) {
            this.info = info;
            this.time = time;
        }

        @Override
        public int compareTo(Event o) {
            return this.time.compareTo(o.time);
        }

        public String getTime() {
            String _time;
            Time tr=new Time(time.getTime());
            LocalTime t=tr.toLocalTime();
            _time=String.format("%02d:%02d",t.getHour(),t.getMinute());
            return _time;
        }

        @Override
        public String toString() {
            return getTime() +" "+ info;
        }
    }
    private static void Exams_s(Scanner sc) throws ParseException {
        int n=Integer.parseInt(sc.nextLine());
        OBHT<String,ArrayList<Event>> ispiti=new OBHT<>(n*2);
        for (int i = 0; i < n; i++) {
            //27/01/2016 14:00 Rooms Kalkulus 1/Matematika 1
            String []parts=sc.nextLine().split("\\s++");
            String date=parts[0];
            String time=parts[1];
            StringBuilder info= new StringBuilder();
            for (int j = 2; j < parts.length ; j++) {
                info.append(parts[j]).append(" ");
            }
            SimpleDateFormat format=new SimpleDateFormat("hh:mm");
            Event event=new Event(info.toString(),format.parse(time));
            if(ispiti.search(date)==-1){
                ArrayList<Event>arr=new ArrayList<>();
                arr.add(event);
                ispiti.insert(date,arr);
            }
            else {ispiti.getBucket(ispiti.search(date)).value.add(event);}
        }

        String i_date=sc.nextLine();
        if(ispiti.search(i_date)==-1) System.out.println("Nema ispiti na taa data");
        else {
            ArrayList<Event> arrayList=ispiti.getBucket(ispiti.search(i_date)).value;
            arrayList.sort(Comparator.naturalOrder());
            for (Event event : arrayList) {
                System.out.println(event);
            }

        }
    }


    private static void Company(Scanner sc) {
        int n=Integer.parseInt(sc.nextLine());
        OBHT<String,ArrayList<String>> employees=new OBHT<>(n);
        for (int i = 0; i < n; i++) {
            String [] parts=sc.nextLine().split(",");
            String Vraboten=parts[0];
            String manager=parts[1];
            if(employees.search(manager)==-1){
                ArrayList<String> arr=new ArrayList<>();
                arr.add(Vraboten);
                employees.insert(manager,arr);
            }
            else employees.getBucket(employees.search(manager)).value.add(Vraboten);
        }
        System.out.println(employees.occupiedspce());

        for (int i = 0; i < n; i++) {
            if(employees.getBucket(i)!=null){
                //System.out.println(employees.getBucket(i).key+":"+employees.getBucket(i).value.size());
                int sum=0;
                for (String s : employees.getBucket(i).value) {
                    if(!s.equals(employees.getBucket(i).key))sum++;
                }
                //sum+=employees.getBucket(i).value.size();
                for (String s : employees.getBucket(i).value) {
                    if(employees.search(s)!=-1 && !s.equals(employees.getBucket(i).key)){
                        int employee_emplyees=employees.getBucket(employees.search(s)).value.size();
                        sum+=employee_emplyees;
                    }
                }
                System.out.println(employees.getBucket(i).key+":"+sum);
            }
        }
    }


    static class Addressing {
        String adresa;
        String a_broj;

        public Addressing(String adresa, String a_broj) {
            this.adresa = adresa;
            this.a_broj = a_broj;
        }
    }
    private static void Gifts_Santa(Scanner sc) {
        int n=Integer.parseInt(sc.nextLine());
        OBHT<String, Addressing> deca=new OBHT<>(n*2);
        for (int i = 0; i < n; i++) {

            String [] parts=sc.nextLine().split("\\s++");
            String name=parts[0];
            String address_=parts[1];
            String adr_b=parts[2];
            Addressing address=new Addressing(address_,adr_b);
            deca.insert(name,address);
        }
        int m=Integer.parseInt(sc.nextLine());
        OBHT<String,String> change_addr_map=new OBHT<>(m*2);
        for (int i = 0; i < m; i++) {
            String [] parts=sc.nextLine().split("\\s++");
            String original=parts[0];
            String changed=parts[1];
            change_addr_map.insert(original,changed);
        }
        String child=sc.nextLine();
        String address=deca.getBucket(deca.search(child)).value.adresa;
        String address_num=deca.getBucket(deca.search(child)).value.a_broj;
        if(change_addr_map.search(address)==-1) System.out.println(address+" "+address_num);
        else {
            address=change_addr_map.getBucket(change_addr_map.search(address)).value;
            System.out.println(address+" "+address_num);
        }
    }


    private static void Web_domains(Scanner sc) {
        int n=Integer.parseInt(sc.nextLine());
        OBHT<String,Integer> domains=new OBHT<>(n*8);
        for (int i = 0; i < n; i++) {
            //5043 courses.finki.ukim.mk
            String []parts=sc.nextLine().split("\\s+");
            int port=Integer.parseInt(parts[0]);
            ArrayList<String> array=new ArrayList<>();
            String []dom=parts[1].split("\\.");

            for (int j = 0; j < dom.length; j++) {
                StringBuilder s= new StringBuilder();
                for (int k = j; k < dom.length; k++) {
                    s.append(dom[k]).append(".");
                }
                if(s.charAt(s.length()-1)=='.') s = new StringBuilder(s.substring(0, s.length() - 1));
                array.add(s.toString());
            }

            for (String string : array) {
                if(domains.search(string)==-1)domains.insert(string,port);
                else domains.getBucket(domains.search(string)).value+=port;
            }
        }
        int m=Integer.parseInt(sc.nextLine());
        ArrayList<String> searched=new ArrayList<>();
        for (int i = 0; i < m; i++) {
            String domain=sc.nextLine();
            searched.add(domain);
        }

        for (String domain_searched : searched) {
            if(domains.search(domain_searched)==-1) System.out.println("Doesnt exist");
            else System.out.println(domains.getBucket(domains.search(domain_searched)).value);
        }


    }



    static class Zbor implements Comparable<Zbor>{
        String key;

        public Zbor(String key) {
            this.key = key;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Zbor hash = (Zbor) o;
            return Objects.equals(key, hash.key);
        }

        @Override
        public int hashCode() {
            return (100 * key.charAt(0) + key.charAt(1));
        }

        @Override
        public int compareTo(Zbor other) {
            return  key.compareTo(other.key);
        }

        @Override
        public String toString() {
            return key;
        }
    }
    public static void main(String[] args) throws ParseException, IOException {
        Scanner sc=new Scanner(System.in);

        CBHT<Zbor, Integer> male=new CBHT<>(9021);
        CBHT<Zbor, Integer> female=new CBHT<>(9021);

        int n=Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            String line= sc.nextLine();
            String [] parts=line.split("\\s++");
            Zbor key=new Zbor(parts[0].toUpperCase());
            if(parts[1].equals("F")){
                if(female.search(key)==null){
                    female.insert(key, 1);
                }
                else {
                    female.search(key).element.value++;
                }
            }
            else {
                if(male.search(key)==null){
                    male.insert(key, 1);
                }
                else {
                    male.search(key).element.value++;
                }
            }
        }

        //System.out.println(male);
        System.out.println(female);

        //F
        //MARIJA
        //Ivana
        //Kristina
        //Anastasija
        //END

        String pol=sc.nextLine();
        String word=sc.nextLine().toUpperCase();

        while (!word.equals("END")){
            Zbor z=new Zbor(word);
            int flag=0;
            SLLNode<MapEntry<Zbor, Integer>> ime=null;

            if(pol.equals("F")){
                SLLNode<MapEntry<Zbor, Integer>> tmp=female.getFirst(z);
                if(tmp!=null){
                    while (tmp!=null){
                        System.out.println(tmp.element.key);
                        if(tmp.element.key.equals(z)) {
                            flag=1;
                            ime=tmp;
                        }
                        tmp=tmp.succ;
                    }
                    if(flag==1){
                        System.out.println(pol +" "+ ime.element.key + " " + ime.element.value);
                    }
                    else {
                        System.out.println("No such name");
                    }
                }
                else{
                    System.out.println("No such name");
                }
            }


            word=sc.nextLine().toUpperCase();


        }









        /*
        BirthDays(sc);
        
        BestOffer(sc);

        Anagrams(sc);

        Epidemic(sc);




        names_Occurrence(sc);

        Pharmacy(sc);

        Number_contacts(sc);
        
        Temperature(sc);

        Border_Crossing(sc);

        Magazine(sc);

        Data_files(sc);


        ///////     Problems so OBHT      ///////

        Red_Cross(sc);
        
        Air_quality(sc);

        BirthDays_OBHT(sc);
        
        Spellcheck(sc);
        
        College_applications(sc);
        
        Traffic_lights(sc);

        Calling_num(sc);

        Criminals(sc);

        Permutations(sc);
        
        Exams_s(sc);
        
        Company(sc);

        Gifts_Santa(sc);

        Web_domains(sc);

        */


        sc.close();


    }




}
