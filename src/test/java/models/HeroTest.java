package models;

import groovy.util.GroovyTestCase;
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

        Hero hero = new Hero("Matata", 30, specialPower,weakness,1);
        assertEquals(true,hero instanceof Hero);
    }
    public Hero newHero() throws Exception {
        ArrayList<String> specialPower = new ArrayList<String>();
        ArrayList<String> weakness = new ArrayList<String>();
        specialPower.add("dance fighting");
        weakness.add("Venom");


        Hero newHero = new Hero("Matata", 30, specialPower, weakness,1);
        return newHero;
    }
    public Hero anotherHero() throws Exception {
        ArrayList<String> specialPower = new ArrayList<String>();
        ArrayList<String> weakness = new ArrayList<String>();
        specialPower.add("dance fighting");
        weakness.add("Venom");

        Hero anotherHero = new Hero("Matata", 30, specialPower, weakness, 1);
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
        GroovyTestCase.assertEquals(30,hero.getAge());
    }
    @Test
    public void savedSpecialspecialPower() throws Exception {
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
    public void HeroInstanceWithName_true() throws Exception{
        Hero hero = newHero();
        assertEquals("Matata",hero.getName());
    }

    @Test
    public void findReturnsCorrectPost() throws Exception{
        Hero hero = anotherHero();
        assertEquals(1, Hero.findById(hero.getId()).getId());
    }

    @Test
    public void findReturnsCorrectHeroWhenMoreThanOneHeroExists() throws Exception{
        Hero hero = anotherHero();
        Hero anotherHero = anotherHero();

        assertEquals(2,Hero.findById(anotherHero.getId()).getId());
    }

    @Test
    public void updateChangesHerosContent() throws Exception{
        Hero hero = anotherHero();
        String formerName = hero.getName();
        int formerAge = hero.getAge();
        ArrayList<String> formerspecialPower = hero.getSpecial_power();
        ArrayList<String> formerWeaknesses = hero.getWeakness();
        int formerId = hero.getId();

        ArrayList<String> specialPower = new ArrayList<String>();
        ArrayList<String> weaknesses = new ArrayList<String>();
        specialPower.add("Free kicks");
        specialPower.add("Skills");
        weaknesses.add("dribbles too much");
        weaknesses.add("magic left foot");

        hero.update("Messi",34,specialPower,weaknesses);

        assertEquals(formerId, hero.getId());
        assertNotEquals(formerName, hero.getName());
        assertNotEquals(formerAge,hero.getAge());
    }

    @Test
    public void deleteDeletesASpecificHero() throws Exception {
        Hero hero = anotherHero();
        Hero anotherHero = anotherHero();
        hero.deleteHero();
        assertEquals(1,Hero.getAll().size());
        assertEquals(Hero.getAll().get(0).getId(),2);
    }

    @Test
    public void deleteAllHerosDeletesAllHeros() throws Exception{
        Hero hero = anotherHero();
        Hero anotherHero = anotherHero();

        Hero.clearAllHeros();
        assertEquals(0,Hero.getAll().size());
    }
}

