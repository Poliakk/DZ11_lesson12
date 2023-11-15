/* DZ11_lesson12
Расширим предыдущую задачу. Замерить время добавления, поиска и удаления объектов из коллекции
в LinkedList, ArrayList, TreeSet, HashSet.
Сравнить время и сделать вывод о том, в каких условиях какая коллекция работает быстрее.
 */

import java.util.*;

public class TimeMeter {
    public static void main(String[] args) {
        Random random = new Random();
        long timeStart;
        LinkedList<Object> ekz1 = new LinkedList<>();
        ArrayList<Object> ekz2 = new ArrayList<>();
        HashSet<Object> ekz3 = new HashSet<>();
        TreeSet<Object> ekz4 = new TreeSet<>();

        ArrayList<Collection<Object>> ekzes = new ArrayList<>();
        ekzes.add(ekz1);
        ekzes.add(ekz2);
        ekzes.add(ekz3);
        ekzes.add(ekz4);

        for (int i = 0; i < 4; i++) {
            Collection<Object> ekz = ekzes.get(i);
            timeStart = System.nanoTime();
            toFill(ekz);
            System.out.printf("Время заполнения %s, %d мс\n", ekz.getClass().getSimpleName(), timing(timeStart) / 1000000);

            System.out.println("Время на операцию с элементом:");
            timeStart = System.nanoTime();
            toFind(random, ekz);
            long findingT = timing(timeStart) / 1000000;

            timeStart = System.nanoTime();
            toRemove(random, ekz);
            long removingT = timing(timeStart) / 1000000;

            System.out.printf("поиск %dмс\tудаление %dмс\n\n", findingT, removingT);
        }
    }

    public static long timing(long timeStart) {
        long timeStop = System.nanoTime();
        return timeStop - timeStart;
    }
    //При исп-нии Collection при заполнении через рандом ищет и удаляет непонятно что,
    // начинается с 100к элементов и диапазона рандома. После добавления в тот же метод size() работает.

    //При исп-нии Collection при заполнении через присвоение элементу значения счетчика i
    // работает нормально.

    // 11java не принимает nextInt() с диапазоном.

    public static void toFill(Collection<Object> ekz) {
        for (int i = 1; i < 500_001; i++) {
            ekz.add(i);
        }
    }

    //Непрерывным циклом интов LinkedList заполняется быстрее всех,
    //затем HashSet, ArrayList, TreeSet.
    //С ручной вставкой HashSet, ArrayList, LinkedList, TreeSet.
    //Удаляет и ищет быстрее: HashSet, TreeSet (мкс), ArrayList, LinkedList(мс)
    // (на больших объемах линкд гораздо быстрее в удалении).
    public static void toFind(Random random, Collection<Object> ekz) {
        long sumSearch = 0;
        int j;
        for (j = 1; j < 1001; j++) {
            long time = System.nanoTime();
            ekz.contains(random.nextInt(500_000));
            long timeDiff = System.nanoTime() - time;
            sumSearch = (sumSearch + timeDiff);
        }
        System.out.println("Внутри метода Тпоиска, мкс = " + sumSearch / j);
    }

    public static void toRemove(Random random, Collection<Object> ekz) {
        long sumDel = 0;
        int j;
        for (j = 1; j < 1001; j++) {
            long time = System.nanoTime();
            ekz.remove(random.nextInt(500_000));
            long timeDiff = System.nanoTime() - time;
            sumDel = (sumDel + timeDiff);
        }
        System.out.println("Внутри метода Тудаления, мкс = " + sumDel / j);
    }
}