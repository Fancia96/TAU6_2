;

import org.easymock.EasyMock;
import org.example.Car;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CarEasyTest {
    private Car myFerrari = EasyMock.createMock(Car.class);

    @Test
    public void test_instance_car(){
        assertTrue(myFerrari instanceof Car);
    }

    @Test
    public void test_default_behavior_needsFuel(){
        assertFalse(myFerrari.needsFuel(), "New test double should return False as boolean");
    }

    @Test
    public void test_default_behavior_temperature(){
        assertEquals(0.0, myFerrari.getEngineTemperature(), "New test double should return 0.0");
    }

    @Test
    public void test_stubbing_mock(){
        EasyMock.expect(myFerrari.needsFuel()).andReturn(true);
        EasyMock.replay(myFerrari);
        assertTrue(myFerrari.needsFuel());

    }

    @Test
    public void test_exception(){
        EasyMock.expect(myFerrari.needsFuel()).andThrow(new RuntimeException());
        EasyMock.replay(myFerrari);
        assertThrows(RuntimeException.class, () -> {
            myFerrari.needsFuel();
        });
    }
    @Test
    public void testIfCarNeedsFuelWhenHeNeeds(){
        EasyMock.expect(myFerrari.needsFuel()).andReturn(true);
        EasyMock.replay(myFerrari);
        assertTrue(myFerrari.needsFuel());
    }

    @Test
    public void testIfCarNeedsFuel(){
        EasyMock.expect(myFerrari.needsFuel()).andReturn(false);
        EasyMock.replay(myFerrari);
        assertFalse(myFerrari.needsFuel());
    }

    @Test
    void testEngineTemperature() {
        EasyMock.expect(myFerrari.getEngineTemperature()).andReturn(70.0);
        EasyMock.replay(myFerrari);
        assertEquals(myFerrari.getEngineTemperature(), 70.0);
    }@Test

    public void testCarIsNotNull(){
        assertNotNull(myFerrari);
    }

    @Test
    public void testNotTrowExceptionWhileDrivingToSomewhere(){
        assertDoesNotThrow(() -> myFerrari.driveTo("Gdańsk"));
    }

}
