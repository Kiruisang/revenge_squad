package models;
import java.util.ArrayList;


public class Hero {
    private String name;
    private int age;
    private ArrayList<String> specialPower = new ArrayList<String>();
    private  ArrayList<String> weakness = new ArrayList<String>();
    private static ArrayList<Hero> instances = new ArrayList<Hero>();
    private int Id;
    private int squadId;

    public Hero(String name, int age, ArrayList<String> specialPower, ArrayList<String> weakness, int squadId) {
        this.name = name;
        this.age = age;
        this.specialPower = specialPower;
        this.weakness = weakness;
        this.squadId = squadId;
        instances.add(this);
        Id = instances.size();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<String> getSpecial_power() {
        return specialPower;
    }

    public ArrayList<String> getWeakness() {
        return weakness;
    }
    public static ArrayList<Hero> getAll() {
        return instances;
    }
    public static void clear() {
        instances.clear();
    }

    public int getId()
    {
        return Id;
    }

    public static Hero find(int id) {
        return instances.get(id - 1);
    }
    public void update(String name, int age, ArrayList<String> specialPower, ArrayList<String> weakness) {
        this.name = name;
        this.age = age;
        this.specialPower = specialPower;
        this.weakness = weakness;
    }
    public void deleteHero(){
        instances.remove(Id -1);
    }
    public int getSquadId() {
        return squadId;
    }
}