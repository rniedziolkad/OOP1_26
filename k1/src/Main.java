import java.util.List;

public class Main {
    public static void main(String[] args) {
        Election e = new Election("kandydaci.txt");
        e.populate();

        Vote summarized =  Vote.summarize(e.getFirstTurn().getVotes());
        System.out.println(summarized);

    }
}