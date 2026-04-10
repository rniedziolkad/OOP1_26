import java.util.*;

public class Vote {
    private Map<Candidate, Integer> votesForCandidate;
    private List<String> location;

    public static Vote fromCsvLine(String line, List<Candidate> candidates){
        List<String> parsedLocation = new ArrayList<>();
        Map<Candidate, Integer> parsedValues = new HashMap<>();
        String[] elements = line.split(",", -1);
        parsedLocation.add(elements[2]);
        parsedLocation.add(elements[1]);
        parsedLocation.add(elements[0]);
        for (int i = 0; i < candidates.size(); i++){
            parsedValues.put(candidates.get(i), Integer.parseInt(elements[i+3]));
        }
        return new Vote(parsedValues, parsedLocation);
    }

    public Vote(Map<Candidate, Integer> votesForCandidate, List<String> location) {
        this.votesForCandidate = votesForCandidate;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "votesForCandidate=" + votesForCandidate +
                ", location=" + location +
                '}';
    }
}
