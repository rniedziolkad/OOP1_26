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

    public AgeBracketDeaths getAge(int age) {
        int young = age - (age % 5);
        int old = young + 4;
        int count;

        if (age >= 95) {
            count = zgony[19];
        } else {
            count = zgony[age / 5];
        }

        return new AgeBracketDeaths(young, old, count);
    }

    public class AgeBracketDeaths {
        public final int young;
        public final int old;
        public final int deathCount;

        public AgeBracketDeaths(int young, int old, int deathCount) {
            this.young = young;
            this.old = old;
            this.deathCount = deathCount;
        }
    }


}
