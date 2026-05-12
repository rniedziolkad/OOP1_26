public class Main {
    public static void main(String[] args) {
        DeathCauseStatistic deathCauseStatistic=DeathCauseStatistic.FromCsvLine("A04.7          ,758,-,-,-,-,-,1,-,1,3,5,9,12,30,58,64,94,161,192,95,33");
        System.out.println(deathCauseStatistic);

        System.out.println(deathCauseStatistic.getAge(90).deathCount);
        DeathCauseStatisticsList list = new DeathCauseStatisticsList();
        list.repopulate("zgony.csv");
//        System.out.println("Nasza lista: " + list.toString());
        ICDCodeTabularOptimizedForMemory tabular = new ICDCodeTabularOptimizedForMemory();
        for (DeathCauseStatistic stat : list.mostDeadlyDiseases(2,4)) {
            System.out.println(tabular.getDescription(stat.getChoroba()));
            System.out.println(stat);
        }
    }
}