;

import org.easymock.EasyMock;
import org.easymock.TestSubject;
import org.example.Client;
import raceresult.Message;
import raceresult.RaceResultService;
import raceresult.RaceResultServiceInterface;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


public class RaceResulServiceTestEasyMock {

    RaceResultServiceInterface raceResultServiceInterface = EasyMock.createMock(RaceResultServiceInterface.class);

    @Test
    public void test_instance_car(){
        assertTrue(raceResultServiceInterface instanceof RaceResultServiceInterface);
    }

    @Test
    public void test_add_subscriber(){
        Client CLIENT = new Client();
        CLIENT.email = "email1";
        RaceResultService raceResultService = new RaceResultService();
        raceResultService.addSubscriber(CLIENT);
        System.out.println(raceResultService.getSubscriber(CLIENT.getEmail())+"jhjhjh");
        assertEquals(raceResultService.getSubscriber("email1").getEmail(), CLIENT.getEmail());
    }

    @Test
    public void test_add_subscriber_fail(){
        Client CLIENT = new Client();
        CLIENT.email = "email1";
        RaceResultService raceResultService = new RaceResultService();
        //raceResultService.addSubscriber(CLIENT);
        System.out.println(raceResultService.getSubscriber(CLIENT.getEmail())+"jhjhjh");
        assertEquals(raceResultService.getSubscriber("email1"), null);
    }

    @Test
    public void test_sending_message() {
        Message MESSAGE = new Message();
        MESSAGE.message_text = "cbc";

        Client CLIENT = new Client();
        CLIENT.email = "email2";

        RaceResultService raceResultService = new RaceResultService();
        raceResultService.addSubscriber(CLIENT);

        raceResultService.send(MESSAGE, CLIENT);
        EasyMock.replay(raceResultServiceInterface);

        assertTrue(raceResultService.isMessageSent(CLIENT));
    }

    @Test
    public void test_sending_message_fail() {
        Message MESSAGE = new Message();
        MESSAGE.message_text = "cbc";

        Client CLIENT = new Client();
        CLIENT.email = "email2";

        RaceResultService raceResultService = new RaceResultService();
        raceResultService.addSubscriber(CLIENT);

        //raceResultService.send(MESSAGE, CLIENT);
        EasyMock.replay(raceResultServiceInterface);

        assertFalse(raceResultService.isMessageSent(CLIENT));
    }

    @Test
    public void test_remove_subscriber() {
        Client CLIENT = new Client();
        CLIENT.email = "email2";

        RaceResultService raceResultService = new RaceResultService();
        raceResultService.addSubscriber(CLIENT);


        raceResultService.removeSubscriber(CLIENT);
        EasyMock.replay(raceResultServiceInterface);

        assertEquals(raceResultService.getSubscriber("email2"), null);
    }

}
