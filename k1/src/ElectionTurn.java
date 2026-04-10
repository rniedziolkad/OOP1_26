import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ElectionTurn  {
    private List<Candidate> candidates;
    private List<Vote> votes;
    public ElectionTurn(List<Candidate> candidates) {
        this.candidates = candidates;
        votes = new ArrayList<>();
    }
    public void populate(String filePath){
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linia = reader.readLine();   // wczytany naglowek, mozna zignorowac
            while((linia = reader.readLine()) != null) {
                Vote parstVote = Vote.fromCsvLine(linia, candidates);
                votes.add(parstVote);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "ElectionTurn{" +
                "candidates=" + candidates +
                ", votes=" + votes.get(0) +
                '}';
    }
}
