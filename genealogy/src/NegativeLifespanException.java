import java.time.LocalDate;

public class NegativeLifespanException extends RuntimeException {
    public NegativeLifespanException(LocalDate birth, LocalDate death) {
        super("Ujemna długość życia: " + birth + " -> " + death);
    }
}
