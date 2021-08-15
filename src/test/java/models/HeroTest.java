package models;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.util.ArrayList;

import static groovy.util.GroovyTestCase.assertEquals;
import static org.junit.Assert.*;

public class HeroTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        Hero.clearAllHeros();
    }

    @Test
    public void NewHeroObjectGetsCorrectlyCreated_true() throws Exception{
        ArrayList<String> specialPower = new ArrayList<String>();
        ArrayList<String> weakness = new ArrayList<String>();
        specialPower.add("fly");
        specialPower.add("super strength");
        weakness.add("destructive temper");
        weakness.add("kryptonite");

        Hero hero = new Hero("supergirl",23,specialPower,weakness,1);
        assertEquals(true, hero instanceof Hero);
    }

    public Hero newHero() throws Exception{
        ArrayList<String> specialPower = new ArrayList<String>();
        ArrayList<String> weakness = new ArrayList<String>();
        specialPower.add("fly");
        specialPower.add("super strength");
        weakness.add("destructive temper");
        weakness.add("kryptonite");

        Hero hero = new Hero("supergirl",23,specialPower,weakness,1);
        return  hero;
    }

    public Hero anotherHero() throws Exception{
        ArrayList<String> specialPower = new ArrayList<String>();
        ArrayList<String> weakness = new ArrayList<String>();
        specialPower.add("fly");
        specialPower.add("super strength");
        weakness.add("magic");
        weakness.add("kryptonite");

        Hero anotherHero = new Hero("superman",30,specialPower,weakness,1);

        return  anotherHero;
    }

    @Test
    public void HeroInstanceWithName_true() throws Exception{
        Hero hero = newHero();
        assertEquals("supergirl",hero.getName());
    }

    @Test
    public void HeroInstanceWithAge_true() throws Exception{
        Hero hero = newHero();
        assertEquals(23,hero.getAge());
    }

    @Test
    public void HeroInstanceWithspecialPower_true() throws Exception{
        Hero hero = newHero();
        assertTrue(hero.getSpecial_power().contains("fly"));
    }

    @Test
    public void HeroInstanceWithweakness_true() throws Exception{
        Hero hero = newHero();
        assertTrue(hero.getWeakness().contains("kryptonite"));
    }

    @Test
    public void AllHerosReturnedCorrevtly_true() throws Exception {
        Hero hero = newHero();
        Hero anotherHero = anotherHero();
        assertEquals(2,Hero.getAll().size());
    }

    @Test
    public void AllHerosContainsAllPosts_true() throws Exception {
        Hero hero = newHero();
        Hero anotherHero = anotherHero();

        assertTrue(Hero.getAll().contains(hero));
        assertTrue(Hero.getAll().contains(anotherHero));
    }

    @Test
    public void getId_herosInstantiateWithAnID_1() throws Exception{
        Hero hero =newHero();
        assertEquals(1,hero.getId());
    }

    @Test
    public void findReturnsCorrectPost() throws Exception{
        Hero hero = newHero();
        assertEquals(1, Hero.findById(hero.getId()).getId());
    }

    @Test
    public void findReturnsCorrectHeroWhenMoreThanOneHeroExists() throws Exception{
        Hero hero = newHero();
        Hero anotherHero = anotherHero();

        assertEquals(2,Hero.findById(anotherHero.getId()).getId());
    };

    @Test
    public void updateChangesHerosContent() throws Exception{
        Hero hero = newHero();
        String formerName = hero.getName();
        int formerAge = hero.getAge();
        ArrayList<String> formerspecialPower = hero.getSpecial_power();
        ArrayList<String> formerweakness = hero.getWeakness();
        int formerId = hero.getId();

        ArrayList<String> specialPower = new ArrayList<String>();
        ArrayList<String> weakness = new ArrayList<String>();
        specialPower.add("risk taker");
        specialPower.add("fast");
        weakness.add("trust too much");
        weakness.add("magic");

        hero.update("clare",19,specialPower,weakness);

        assertEquals(formerId, hero.getId());
        assertNotEquals(formerName, hero.getName());
        assertNotEquals(formerAge,hero.getAge());
    }

    @Test
    public void deleteDeletesASpecificHero() throws Exception {
        Hero hero = newHero();
        Hero anotherHero = anotherHero();
        hero.deleteHero();
        assertEquals(1,Hero.getAll().size());
        assertEquals(Hero.getAll().get(0).getId(),2);
    }

    @Test
    public void deleteAllHerosDeletesAllHeros() throws Exception{
        Hero hero = newHero();
        Hero anotherHero = anotherHero();

        Hero.clearAllHeros();
        assertEquals(0,Hero.getAll().size());
    }
}