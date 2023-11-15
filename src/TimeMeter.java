/* DZ11_lesson12
Расширим предыдущую задачу. Замерить время добавления, поиска и удаления объектов из коллекции
в LinkedList, ArrayList, TreeSet, HashSet.
Сравнить время и сделать вывод о том, в каких условиях какая коллекция работает быстрее.
 */

import java.util.*;

public class TimeMeter {
    public static void main(String[] args) {
        Random random = new Random();
        Object o = 200000;
        //o = "the one";
        int sizes = 0;
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
            //toFill1(random, ekz, o);
            toFill(ekz, o);
            System.out.printf("Время заполнения %s, %d мс\n", ekz.getClass().getSimpleName(), timing(timeStart) / 1000000);

            int size0 = ekz.size();
            //System.out.println("Размер объекта: " + ekz.size() + "\n");

            System.out.println("Время на операцию с элементом:");
            timeStart = System.nanoTime();
            toFind(random, ekz, o);
            long findingT = timing(timeStart) / 1000000;

            timeStart = System.nanoTime();
            toRemove(random, ekz, o);
            long removingT = timing(timeStart) / 1000000;

            System.out.printf("поиск %dмс\tудаление %dмс\n\n", findingT, removingT);
/*
            int sizeProof = ekz.size() - size0;
            if (sizeProof != 0) {
                sizes++;
                System.out.println("Размер " + ekz.getClass().getSimpleName() + " изменился к " + ekz.size() + ".\n");
            }
*/
        }
/*
        if (sizes != 0) {
            System.out.println("Размер одной из коллекций изменился!");
        }
*/
    }

    public static long timing(long timeStart) {
        long timeStop = System.nanoTime();
        return timeStop - timeStart;
    }
/*
    public static void toPrint(Collection<Object> ekz) {
        for (Object i : ekz) {
            System.out.print(" " + i);
        }
        System.out.println("\n" + ekz.size() + "\n");
    }
 */

    //HashSet, TreeSet через Collection заполняется не полностью из-за дублей рандома.
    //В Collection нет get().
    //TreeSet строковый объект среди интеджеров не взял на заполнение, но берет среди объектов

    //При исп-нии Collection при заполнении через рандом ищет и удаляет непонятно что,
    // начинается с 100к элементов и диапазона рандома. После добавления в тот же метод size() работает.

    //При исп-нии Collection при заполнении через присвоение элементу значения счетчика i
    // работает нормально.

    // 11java не принимает nextInt() с диапазоном.

    public static void toFill1(Random random, Collection<Object> ekz, Object o) {
        for (int i = 1; i < 500_001; i++) {
            ekz.add(random.nextInt(500_000));
        }
        //ekz.add(o);
    }

    public static void toFill(Collection<Object> ekz, Object o) {
        for (int i = 1; i < 500_001; i++) {
            ekz.add(i);
        }
        /*
        //только для о посередине коллекции
        ekz.add(o);
        for (int i = 500_001; i < 800_001; i++) {
            ekz.add(i);
        }*/
    }

    //Непрерывным циклом интов LinkedList заполняется быстрее всех,
    //затем HashSet, ArrayList, TreeSet.
    //С ручной вставкой HashSet, ArrayList, LinkedList, TreeSet.
    //Удаляет и ищет быстрее: HashSet, TreeSet (мкс), ArrayList, LinkedList(мс)
    // (на больших объемах линкд гораздо быстрее в удалении).
    public static void toFind(Random random, Collection<Object> ekz, Object o) {
        long sumSearch = 0;
        int j;
        for (j = 1; j < 1001; j++) {
            long time = System.nanoTime();
            //ekz.contains(random.nextInt(500_000));
            ekz.contains(o);
            long timeDiff = System.nanoTime() - time;
            sumSearch = (sumSearch + timeDiff);
        }
        System.out.println("Внутри метода Тпоиска, мкс = " + sumSearch / j);
    }

    public static void toRemove(Random random, Collection<Object> ekz, Object o) {
        long sumDel = 0;
        int j;
        for (j = 1; j < 1001; j++) {
            long time = System.nanoTime();
            //ekz.remove(random.nextInt(500_000));
            ekz.remove(o);
            long timeDiff = System.nanoTime() - time;
            sumDel = (sumDel + timeDiff);
        }
        System.out.println("Внутри метода Тудаления, мкс = " + sumDel / j);
    }
}