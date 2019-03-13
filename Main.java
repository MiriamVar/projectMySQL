package it.sovy.validateDocument;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args ) throws FileNotFoundException {
        File doc = new File("C:\\Users\\user\\IdeaProjects\\validateDocument\\src\\data.txt");
        Database database =null;

        try {
            Scanner sc = new Scanner(doc);
            while (sc.hasNextLine()) {
                String[] datas = sc.nextLine().split(" ");
                String name = datas[0];
                String surname=datas[1];
                String birthNumber = datas[2];
                String date = datas[3];

                Valid a = new Valid();
                System.out.println(a.validBirthNumberAsDOB(birthNumber,date));
                System.out.println(a.divisibility(birthNumber));

                database = new Database();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                Date date2 = null;
                try {
                    date2 = dateFormat.parse(date);
                }
                catch (ParseException e){
                    e.printStackTrace();
                }

                if(a.validBirthNumberAsDOB(birthNumber,date)&&(a.divisibility(birthNumber))){
                    Person osoba = new Person(name,surname,date2,birthNumber);
                 //   database.insertNewPerson(osoba);
                }
                else {
                    System.out.println("Nezapisujem nic");
                }
                database.countOfWomen();
            }
            sc.close();

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Person dalsi =database.getName("Varga");
        System.out.println(dalsi.getName()+" "+dalsi.getSurname()+" "+dalsi.getDateOfBirth()+" "+dalsi.getPin());

        Person dalsi2 =database.getBirthNumber("980413/6606");
        System.out.println(dalsi2.getName()+" "+dalsi2.getSurname()+" "+dalsi2.getDateOfBirth()+" "+dalsi2.getPin());

        database.countOfWomen();

        List<Person> persons = database.getAllMen();
        for (int i=0;i<persons.size();i++){
            System.out.println(persons.get(i).getName()+" "+persons.get(i).getSurname()+" "+persons.get(i).getDateOfBirth()+" "+persons.get(i).getPin());
        }

        List<Person> persons2 = database.getAllAdults();
        for (int i=0;i<persons2.size();i++){
            System.out.println(persons2.get(i).getName()+" "+persons2.get(i).getSurname()+" "+persons2.get(i).getDateOfBirth()+" "+persons2.get(i).getPin());
        }
    }

}
