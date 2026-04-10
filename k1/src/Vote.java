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

    public static Vote summarize(List<Vote> votes){
        List<String> summarizedLocation = new ArrayList<>();
        Map<Candidate, Integer> summarizedValues = new HashMap<>();
        for(Vote v : votes){
            for(Candidate key : v.votesForCandidate.keySet()) {
                if(summarizedValues.containsKey(key)) {
                    int sum = summarizedValues.get(key)+v.votesForCandidate.get(key);
                    summarizedValues.put(key, sum);
                } else {
                    summarizedValues.put(key, v.votesForCandidate.get(key));
                }
            }
        }
        return new Vote(summarizedValues, summarizedLocation);
    }
}
