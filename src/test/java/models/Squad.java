package models;
import java.util.ArrayList;


public class Squad {
    private int maxSize;
    private String name;
    private String cause;
    private static ArrayList<Squad> instances = new ArrayList<Squad>();
    private int id;

    public Squad(int maxSize, String name, String cause) {
        this.maxSize = maxSize;
        this.name = name;
        this.cause = cause;
        instances.add(this);
        this.id = instances.size();

    }

    public int getMaxSize() {
        return maxSize;
    }

    public String getName() {
        return name;
    }

    public String getCause() {
        return cause;
    }
    public static ArrayList<Squad> all() {
        return instances;
    }
    public void setId(int id) { this.id = id;}
    public int getId()
    {
        return id;
    }

    public static Squad find(int id) {
        return instances.get(id - 1);
    }
}