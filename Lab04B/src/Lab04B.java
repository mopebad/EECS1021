import org.firmata4j.firmata.*;
import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Lab04B {
    public static void main(String[] args)
            throws IOException, ParseException {

        String myPort = "COM5"; // modify for your own computer & setup.
        IODevice myGroveBoard = new FirmataDevice(myPort);

        try {
            myGroveBoard.start();
            System.out.println("Board started.");
            myGroveBoard.ensureInitializationIsDone();
        }
        catch (Exception ex) {
            System.out.println("couldn't connect to board.");
        }
        finally {

            // Get alarm time from user
            Date alarmTime = getAlarmDate();

            // Initialize buzzer (D5) and button (D6)
            Pin myBuzzer = myGroveBoard.getPin(5);
            myBuzzer.setMode(Pin.Mode.PWM);

            Pin myButton = myGroveBoard.getPin(6);
            myButton.setMode(Pin.Mode.INPUT);

            // Wait until current time reaches alarm time
            while (new Date().before(alarmTime)) {
                try { Thread.sleep(200); } catch (InterruptedException ignored) {}
            }

            System.out.println("ALARM ACTIVE");

            // Button press tracking
            boolean wasPressed = false;
            long pressStart = 0;
            boolean stopAlarm = false;

            // Beep 30 times unless stopped
            for (int i = 0; i < 30 && !stopAlarm; i++) {

                // Buzzer ON (100 ms)
                myBuzzer.setValue(255);
                long onStart = System.currentTimeMillis();

                while (System.currentTimeMillis() - onStart < 100) {
                    long v = myButton.getValue();
                    long now = System.currentTimeMillis();

                    if (v == 1 && !wasPressed) {
                        wasPressed = true;
                        pressStart = now;
                    } else if (v == 0 && wasPressed) {
                        wasPressed = false;
                        if (now - pressStart < 500) {
                            stopAlarm = true;
                            break;
                        }
                    }
                    try { Thread.sleep(10); } catch (InterruptedException ignored) {}
                }

                // Buzzer OFF (100 ms)
                myBuzzer.setValue(0);
                long offStart = System.currentTimeMillis();

                while (System.currentTimeMillis() - offStart < 100) {
                    long v = myButton.getValue();
                    long now = System.currentTimeMillis();

                    if (v == 1 && !wasPressed) {
                        wasPressed = true;
                        pressStart = now;
                    } else if (v == 0 && wasPressed) {
                        wasPressed = false;
                        if (now - pressStart < 500) {
                            stopAlarm = true;
                            break;
                        }
                    }
                    try { Thread.sleep(10); } catch (InterruptedException ignored) {}
                }
            }

            // Ensure buzzer is off
            myBuzzer.setValue(0);

            // Close board connection
            try { myGroveBoard.stop(); } catch (Exception ignored) {}
        }
    }

    public static Date getAlarmDate() throws ParseException {
        Scanner input = new Scanner(System.in);
        System.out.println("This program uses Arduino board to act as an alarm clock.");
        System.out.println("Below, you can enter your desire date and time to set an alarm.");
        String dateStr;
        Date DT;

        while (true) {
            System.out.print("Enter four digits the year (e.g. 2022): ");
            int year = input.nextInt();
            System.out.print("Enter two digits the month (e.g. 06): ");
            int mm = input.nextInt();
            System.out.print("Enter two digits the day (e.g. 28): ");
            int dd = input.nextInt();
            System.out.print("Enter two digits the hour (e.g. 07): ");
            int HH = input.nextInt();
            System.out.print("Enter two digits the minutes (e.g. 12): ");
            int MM = input.nextInt();
            System.out.print("Enter two digits the second (e.g. 52): ");
            int SS = input.nextInt();

            if (!(year < 2022 || mm > 59 || mm < 0 || dd < 0 || dd > 31 || HH < 0 || HH > 24 || MM < 0
                    || MM > 59 || SS < 0 || SS > 59)) {

                dateStr = "" + dd + "/" + mm + "/" + year + " " + HH + ":" + MM + ":" + SS;
                SimpleDateFormat frmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                DT = frmt.parse(dateStr);

                Date NOW = new Date();
                if (NOW.before(DT)) {
                    System.out.println("The entered alarm time is " + DT);
                    break;
                }
            }
            System.out.println("The entered date is not correct. Please reenter your desired date");
        }
        return DT;
    }
}
