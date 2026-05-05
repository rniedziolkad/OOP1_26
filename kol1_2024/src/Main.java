public class Main {
    public static void main(String[] args) {
        DigitalClock digitalClock= new DigitalClock(DigitalClock.Type.H12);
        digitalClock.setTime(23, 59, 59);
        System.out.println("Godzina: " + digitalClock.toString());
    }

}