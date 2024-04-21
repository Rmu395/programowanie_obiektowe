import java.util.ArrayList;
import java.util.List;

public class DeathCauseStatistics {
//    //my ver (not checked)
//    private String ICDCode;
//    private int[] deathCount = new int[21];   //<-- te liczby raczej nie dobrze
//
//    public DeathCauseStatistic(String ICDCode, int[] deathCount) {
//        this.ICDCode = ICDCode;
//        this.deathCount = deathCount;
//    }
//
//    public static DeathCauseStatistic fromCsvLine(String line) {
//        String[] lineTemp = line.split(",");
//        String ICDCodeTemp = lineTemp[0].trim();
//
//        int tempN = 0;
//        int[] deathCountTemp = new int[21];
//        for(String thisCountStr : lineTemp) {
//            deathCountTemp[tempN++] = Integer.getInteger(thisCountStr);
//        }
//
//        return new DeathCauseStatistic(ICDCodeTemp, deathCountTemp);
//    }
//
//    public String getICDCode() {
//        return ICDCode;
//    }
    private String ICDCode;
    private int[] deathCount;

    public DeathCauseStatistics(int[] deathCount, String ICDCode) {
        this.deathCount = deathCount;
        this.ICDCode = ICDCode;
    }

    public String getICDCode() {
        return ICDCode;
    }

    public static DeathCauseStatistics fromCsvLine(String line) {
        String[] splittedLine = line.split(",");
        String ICD = splittedLine[0].trim();
        int[] deaths = new int[20];
        for (int i = 0; i < 20; i++) {
            if (splittedLine[i + 2].equals("-")) {
                deaths[i] = 0;
            } else {
                deaths[i] = Integer.parseInt(splittedLine[i + 2]);
            }
        }
        return new DeathCauseStatistics(deaths, ICD);
    }

    // moja wersja (ponoć dobrze)
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

    public AgeBracketDeaths AgeDeaths(int age) {
        if ((age/5)*5 != 95)
            return new AgeBracketDeaths(
                (age/5)*5,
                ((age/5)*5)+4,
                deathCount[age/5]
            );
        else
            return new AgeBracketDeaths(
                (age/5)*5,
                Integer.MAX_VALUE,
                deathCount[age/5]
            );
    }

}
