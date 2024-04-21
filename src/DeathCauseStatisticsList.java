import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DeathCauseStatisticsList {
        public List<DeathCauseStatistics> list = new ArrayList<>();

        public void repopulate(String path) {
            list.clear();
    //        DeathCauseStatistics.fromCsvLine()
            try {
                list = Files.lines(Paths.get(path))
                        .skip(2)
        //                .map(v -> DeathCauseStatistics.fromCsvLine(v))
                        .map(DeathCauseStatistics::fromCsvLine)     //to to samoco to ^
                        .collect(Collectors.toList());

                int age = 32;
                list.stream().sorted(
                        (x,y) -> Integer.compare(
                                y.AgeDeaths(age).deathCount,
                                x.AgeDeaths(age).deathCount
                        )
                ).collect(Collectors.toList()).subList(0,10);
                //aby to odwrócić to można zamienić x z y powyżej lub to zrobić
                //nvm po prostu zamieniliśmy x i y
                //do zad 4 ważny jest overload Stringa (ten z limitem)
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
