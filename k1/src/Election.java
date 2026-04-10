import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Election {
    private List<Candidate> candidates;
    private ElectionTurn firstTurn, secondTurn;

    public Election(String filePath) {
        populateCandidates(filePath);
        firstTurn = new ElectionTurn(candidates);
        secondTurn = null;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public ElectionTurn getFirstTurn() {
        return firstTurn;
    }

    public ElectionTurn getSecondTurn() {
        return secondTurn;
    }

    private void populateCandidates(String filePath) {
        List<Candidate> loaded = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // wczytywanie linii w pętli
            // (gdy dojdzie do końca pliku to próba wczytania linii zwróci null)
            while ((line = reader.readLine()) != null) {
                Candidate parsed = new Candidate(line);
                loaded.add(parsed);
            }
        } catch (IOException e) {
            System.err.println("Bład odczytu. " + e.getMessage());
        }
        this.candidates = loaded;
    }

    public void populate() {
        firstTurn.populate("1.csv");
    }

    public List<Candidate> copyCandidates() {
        List<Candidate> result = new ArrayList<>();
        for (int i = 0; i < candidates.size(); i++){
            result.add(candidates.get(i));
        }
//        for (Candidate c : candidates) {
//            result.add(c);
//        }
        return result;
    }
}
