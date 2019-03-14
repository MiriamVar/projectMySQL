package it.sovy.validateDocument;
import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Database {
    private final String userName = "sef";
    private final String password = "klaudia";
    private final String url = "jdbc:mysql://localhost:3306/db1";

    public void insertNewPerson(Person osoba){
        Connection connect=getConnetion();
        try {
            PreparedStatement statement = connect.prepareStatement("insert into person(name,surname,dateOfBirth,pin) values(?,?,?,?)");
            statement.setString(1,osoba.getName());
            statement.setString(2,osoba.getSurname());
            statement.setDate(3, new Date(osoba.getDateOfBirth().getTime()));
            statement.setString(4,osoba.getPin());
            int result = statement.executeUpdate();

            connect.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    private Connection getConnetion(){
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Is connection");
            connection = DriverManager.getConnection(url,userName,password);
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public Person getName (String surname){
        Connection connect=getConnetion();
        String firstName = "";
        String lastName = "";
        Date dateCreated = new Date(2000-10-10);
        String birthNumber = "";

        try {
            PreparedStatement statement = connect.prepareStatement("select * from person where person.surname = ?");
            statement.setString(1,surname);
            ResultSet res = statement.executeQuery();
            if (res.next()){
                while (res.next()) {
                     firstName = res.getString("name");
                     lastName = res.getString("surname");
                     dateCreated = res.getDate("dateOfBirth");
                     birthNumber = res.getString("pin");
                }
            }else{
                return null;
            }

            connect.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        Person clovek = new Person(firstName,lastName,dateCreated,birthNumber);
        return clovek;
    }

    public Person getBirthNumber (String pin){
        Connection connect=getConnetion();
        String firstName = "";
        String lastName = "";
        Date dateCreated = new Date(2000-10-10);
        String birthNumber = "";
        try {
            PreparedStatement statement = connect.prepareStatement("select * from person where person.pin = ?");
            statement.setString(1,pin);
            ResultSet res = statement.executeQuery();
                while (res.next()) {
                    firstName = res.getString("name");
                    lastName = res.getString("surname");
                    dateCreated = res.getDate("dateOfBirth");
                    birthNumber = res.getString("pin");
            }
                connect.close();
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        Person clovek = new Person(firstName,lastName,dateCreated,birthNumber);
        return clovek;
    }

    public void countOfWomen (){
        Connection connect=getConnetion();
        try {
            PreparedStatement statement = connect.prepareStatement("select count(*) as pocet from person where pin like '__5%' or pin like '__6%'");
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                System.out.println(res.getInt("pocet"));
            }
            connect.close();

        }
        catch (SQLException e){
            e.printStackTrace();
            return;
        }
    }

    public List<Person> getAllMen(){
        Connection connect=getConnetion();
        String query = "select * from person  where pin like '__0%' or pin like '__1%'";
        List<Person> people = new ArrayList<>();
        ResultSet res;
        try {
            PreparedStatement statement = connect.prepareStatement(query);
            res= statement.executeQuery();
            while(res.next()){
              String  firstName = res.getString("name");
              String  lastName = res.getString("surname");
              Date dateCreated = res.getDate("dateOfBirth");
              String birthNumber = res.getString("pin");
              Person clovek = new Person(firstName,lastName,dateCreated,birthNumber);
              people.add(clovek);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }


    public List<Person> getAllAdults(){
        Connection connect=getConnetion();
        String query = "select * from person  where dateOfBirth <= curdate() - interval 18 year";

        List<Person> people = new ArrayList<>();
        ResultSet res;
        try {
            PreparedStatement statement = connect.prepareStatement(query);
            res= statement.executeQuery();
            while(res.next()){
                    String  firstName = res.getString("name");
                    String  lastName = res.getString("surname");
                    Date dateCreated = res.getDate("dateOfBirth");
                    String birthNumber = res.getString("pin");
                    Person clovek = new Person(firstName,lastName,dateCreated,birthNumber);
                    people.add(clovek);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return  people;
    }


    public Set<String> getAllFirstNames(){
        Connection connect=getConnetion();
        String query = "select name from person";
        Set<String> people = new HashSet<>();
        ResultSet res;
        try {
            PreparedStatement statement = connect.prepareStatement(query);
            res= statement.executeQuery();
            while(res.next()){
                String  firstName = res.getString("name");
                people.add(firstName);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        return people;
    }

    public List<Person> getAllPersons() {
        Connection connect = getConnetion();
        String query = "select * from person";

        List<Person> people = new ArrayList<>();
        ResultSet res;
        try {
            PreparedStatement statement = connect.prepareStatement(query);
            res = statement.executeQuery();
            while (res.next()) {
                String firstName = res.getString("name");
                String lastName = res.getString("surname");
                Date dateCreated = res.getDate("dateOfBirth");
                String birthNumber = res.getString("pin");
                Person clovek = new Person(firstName, lastName, dateCreated, birthNumber);
                people.add(clovek);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return people;

    }
}
