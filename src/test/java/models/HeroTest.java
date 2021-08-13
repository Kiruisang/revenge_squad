package models;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.util.ArrayList;

import static groovy.util.GroovyTestCase.assertEquals;
import static org.junit.Assert.*;

public class HeroTest {
    @Before
    public void setUp() throws Exception{}

    @After
    public void tearUp() throws Exception{Hero.clearAllHeros();};

    @Test
    public void createsInstanceOfHeroCorrectly_true()throws Exception {
        ArrayList<String> specialPower = new ArrayList<String>();
        ArrayList<String> weakness = new ArrayList<String>();
        specialPower.add("dance fighting");
        weakness.add("Venom");

        Hero hero = new Hero("Matata", 25, "dance fighting", "Venom");
        assertEquals(true,hero instanceof Hero);
    }
    public Hero newHero() throws Exception {
        ArrayList<String> specialPower = new ArrayList<String>();
        ArrayList<String> weakness = new ArrayList<String>();
        specialPower.add("dance fighting");
        weakness.add("Venom");


        Hero newHero = new Hero("Matata", 25, "dance fighting", "Venom");
        return newHero;
    }
    public Hero anotherHero() throws Exception {
        ArrayList<String> specialPower = new ArrayList<String>();
        ArrayList<String> weakness = new ArrayList<String>();
        specialPower.add("dance fighting");
        weakness.add("Venom");

        Hero anotherHero = new Hero("Matata", 25, "dance fighting", "Venom");
        return anotherHero;
    }
    @Test
    public void savedName() throws Exception{
        Hero hero = newHero();
        assertEquals("Matata",hero.getName());
    }
    @Test
    public void savedAge() throws Exception {
        Hero hero = newHero();
        assertEquals(30,hero.getAge());
    }
    @Test
    public void savedSpecialPowers() throws Exception {
        Hero hero = newHero();
        assertTrue(hero.getSpecial_power().contains("dance fighting"));
    }

    @Test
    public void savedWeakness() throws Exception {
        Hero hero = newHero();
        assertTrue(hero.getWeakness().contains("Venom"));
    }
    @Test
    public void all_returns_all_instancesOfHero() throws Exception{
        Hero hero = newHero();
        Hero hero1 = anotherHero();
        assertEquals(2,Hero.getAll().size());
     }
     @Test
     public void allPostsAboutHeros_true() throws Exception {
        Hero hero = newHero();
        Hero hero1 = anotherHero();
        assertTrue(Hero.getAll().contains(hero));
        assertTrue(Hero.getAll().contains(hero1));

     }

    @Test
    public void allHerosHaveAllPosts_true() throws Exception {
        Hero hero = newHero();
        Hero hero1 = anotherHero();

        assertTrue(Hero.getAll().contains(hero));
        assertTrue(Hero.getAll().contains(hero1));
    }

    @Test
    public void getId_heroInstantiateWithAnId_1() throws Exception {
        Hero hero = newHero();
        assertEquals(1,hero.getId());
    }

    @Test
    public void find_returnsCategoryWithSameId_secondCategory() {
        Hero.clear();
        Hero hero = new Hero("Hulk1",40,"Strength","Anger issues");
        assertEquals(Hero.find(hero.getId()), hero);
    }

    @Test
    public void find_hero_by_name() {
        Hero.clear();
        Hero hero = new Hero("Hulk1",40,"Strength","Anger issues");
        assertTrue(Hero.find("Hulk1"));
    }

    @Test
    public void find_returnsNullWhenNoTaskFound_null() {
        assertTrue(Hero.find(1000) == null);
    }



}