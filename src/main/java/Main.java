import lib.AbstractSolution;
import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws Exception {
        Thread.sleep(100);
        Class<?> clazz = Class.forName("solutions.Solution" + args[0]);
        Constructor<?> constructor = clazz.getConstructor();
        AbstractSolution solution = (AbstractSolution) constructor.newInstance();
        long startTime = System.currentTimeMillis();
        System.out.println(String.join("", Collections.nCopies(80, "-")) + "\n" +
                "Solution " + args[0] + ": " + solution.run() + "\n" +
                String.join("", Collections.nCopies(80, "-"))
        );
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(runTime);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(runTime);
        System.out.println(String.format("%02d min, %02d sec, %03d ms",
                minutes,
                seconds - TimeUnit.MINUTES.toSeconds(minutes),
                runTime - TimeUnit.SECONDS.toMillis(seconds) - TimeUnit.MINUTES.toMillis(minutes)
        ));
        System.out.println(String.join("", Collections.nCopies(80, "-")));
    }
}
