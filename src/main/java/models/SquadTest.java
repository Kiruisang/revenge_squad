package models;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class SquadTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    public Squad setupSquad(){
        return new Squad(5, "Iron man", "Defeat Iron Monger");
    }

    @Test
    public void NewSquadObjectGetsCorrectlyCreated_true() throws Exception{
        Squad squad = new Squad(5, "Iron man", "Defeat Iron Monger");
        assertEquals(true, squad instanceof Squad);
    }

    @Test
    public void SquadInstantiatesWithMaxSize_true() {
        Squad squad = setupSquad();
        assertEquals(5, squad.getMaxSize());
    }

    @Test
    public void SquadInstantiatesWithName_true() {
        Squad squad = setupSquad();
        assertEquals("Iron man", squad.getName());
    }

    @Test
    public void SquadInstantiatesWithCause_true() {
        Squad squad = setupSquad();
        assertEquals("Defeat Iron Monger", squad.getCause());
    }
}
