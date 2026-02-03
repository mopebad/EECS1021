import org.firmata4j.firmata.*;
import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import java.io.IOException;

public class ledBlink {

    public static void main(String[] args)
            throws IOException
    {
        String myPort = "COM5"; // modified for your own computer & setup.
        IODevice myGroveBoard = new FirmataDevice(myPort);

        try {
            myGroveBoard.start(); // start comms with board;
            System.out.println("Board started.");
            myGroveBoard.ensureInitializationIsDone();
        }
        catch (Exception ex) {
            System.out.println("couldn't connect to board.");
        }
        finally {
            var myLED = myGroveBoard.getPin(4);
            myLED.setMode(Pin.Mode.OUTPUT);

            // flash LED 10 times
            for (int i = 0; i < 10; i++) {

                // LED D4 on.
                myLED.setValue(1);

                // Pause for half a second.
                try {
                    Thread.sleep(500);
                }
                catch(Exception ex){
                    System.out.println("sleep error.");
                }

                // LED D4 off.
                myLED.setValue(0);

                // Pause for half a second.
                try {
                    Thread.sleep(500);
                }
                catch(Exception ex){
                    System.out.println("sleep error.");
                }
            }

            myGroveBoard.stop(); // finish with the board.
            System.out.println("Board stopped.");
        }
    }
}
