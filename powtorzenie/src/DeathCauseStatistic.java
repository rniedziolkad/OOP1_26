import java.util.Arrays;

public class DeathCauseStatistic {
    private String choroba;
    private Integer[] zgony;

    public String getChoroba() {
        return choroba;
    }

    public static DeathCauseStatistic FromCsvLine(String line){
        String[] parts=line.split(",");
        Integer[] parts2=new Integer[20];
        for(int i=2; i<parts.length;i++){
            if(parts[i].equals("-")){
                parts2[i-2]=0;
            }else{
                parts2[i-2] = Integer.parseInt(parts[i]);
            }

        }
        return new DeathCauseStatistic(parts[0].trim(),parts2);
    }

    @Override
    public String toString() {
        return "DeathCauseStatistic{" +
                "choroba='" + choroba + '\'' +
                ", zgony=" + Arrays.toString(zgony) +
                '}';
    }

    public DeathCauseStatistic(String choroba, Integer[] zgony) {
        this.choroba = choroba;
        this.zgony = zgony;
    }
}
