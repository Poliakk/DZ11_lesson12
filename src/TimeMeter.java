/* DZ11_lesson12
Расширим предыдущую задачу. Замерить время добавления, поиска и удаления объектов из коллекции
в LinkedList, ArrayList, TreeSet, HashSet.
Сравнить время и сделать вывод о том, в каких условиях какая коллекция работает быстрее.
 */

import java.util.*;

public class TimeMeter {
    public static void main(String[] args) {
        Random random = new Random();
        LinkedList<Object> ekz1 = new LinkedList<>();
        ArrayList<Object> ekz2 = new ArrayList<>();
        HashSet<Object> ekz3 = new HashSet<>();
        TreeSet<Object> ekz4 = new TreeSet<>();

        ArrayList<Collection<Object>> ekzes = new ArrayList<>();
        ekzes.add(ekz1);
        ekzes.add(ekz2);
        ekzes.add(ekz3);
        ekzes.add(ekz4);

        long tAddC, tAddL, tAddS;
        float tFindC, tRemoveC, tFindL, tFindS, tRemoveL, tRemoveS;

        System.out.println("Работа при типе Collection");
        for (int i = 0; i < 4; i++) {
            Collection<Object> ekz = ekzes.get(i);
            System.out.printf(ekz.getClass().getSimpleName());
            System.out.println("\nВремя выполнения операции:");
            tAddC = toFill(ekz);
            tFindC = toFind(random, ekz);
            tRemoveC = toRemove(random, ekz);
            System.out.println();

            System.out.println("-------------------------\nРабота при типе List и Set");
            System.out.printf(ekzes.get(i).getClass().getSimpleName());
            System.out.println("\nВремя выполнения операции:");
            if (i < 2) {
                List<Object> ekzL = (List<Object>) ekzes.get(i);
                //ekz.clear();
                tAddL = toFillList(ekzL);
                tFindL = toFindLElemList(random, ekzL);
                toGetElemList(random, ekzL);
                tRemoveL = toRemoveElemList(random, ekzL);
                System.out.println("\n-------------------------\nРазница времени выполнения операции при обращении через Collection и List");
                System.out.printf("заполнение %d мс", (tAddC - tAddL));
                System.out.printf("\nпоиск %.2f мс ", (tFindC - tFindL));
                System.out.printf("\nудаление %.2f мс ", (tRemoveC - tRemoveL));
                System.out.println("\n=========================");
            } else {
                Set<Object> ekzS = (Set<Object>) ekzes.get(i);
                //ekz.clear();
                tAddS = toFillSet(ekzS);
                tFindS = toFindLElemSet(random, ekzS);
                tRemoveS = toRemoveElemSet(random, ekzS);
                //сравнение обращения через Collection и List, Set
                System.out.println("\n-------------------------\nРазница времени выполнения операции при обращении через Collection и Set");
                System.out.printf("заполнение %d мс", (tAddC - tAddS));
                System.out.printf("\nпоиск %.2f мс ", (tFindC - tFindS));
                System.out.printf("\nудаление %.2f мс ", (tRemoveC - tRemoveS));
                System.out.println("\n=========================");
            }
        }
    }
    //При исп-нии Collection при заполнении через рандом ищет и удаляет непонятно что,
    // начинается с 100к элементов и диапазона рандома. После добавления в тот же метод size() работает.

    //При исп-нии Collection при заполнении через присвоение элементу значения счетчика i
    // работает нормально.

    public static long toFill(Collection<Object> ekz) {
        long time = System.currentTimeMillis();
        for (int i = 1; i < 500_001; i++) {
            ekz.add(i);
        }
        long timeDiff = System.currentTimeMillis() - time;
        System.out.printf("заполнение коллекции за %d мс", timeDiff);
        return timeDiff;
    }

    public static long toFillList(List<Object> ekz){
        long time = System.currentTimeMillis();
        for (int i = 1; i < 500_001; i++) {
            ekz.add(i);
        }
        long timeDiff = System.currentTimeMillis() - time;
        System.out.printf("заполнение коллекции за %d мс\n", timeDiff);
        return timeDiff;
    }

    public static long toFillSet(Set<Object> ekz) {
        long time = System.currentTimeMillis();
        for (int i = 1; i < 500_001; i++) {
            ekz.add(i);
        }
        long timeDiff = System.currentTimeMillis() - time;
        System.out.printf("заполнение коллекции за %d мс\n", timeDiff);
        return timeDiff;
    }

    //Непрерывным циклом интов LinkedList заполняется быстрее всех,
    //затем HashSet, ArrayList, TreeSet.
    //С ручной вставкой HashSet, ArrayList, LinkedList, TreeSet.
    //Удаляет и ищет быстрее: HashSet, TreeSet (мкс), ArrayList, LinkedList(мс)
    // (на больших объемах линкд гораздо быстрее в удалении).
    public static float toFind(Random random, Collection<Object> ekz) {
        int j;
        long time = System.nanoTime();
        for (j = 1; j < 1001; j++) {
            ekz.contains(random.nextInt(400_000));
        }
        float timeDiff = (float) ((System.nanoTime() - time)) / j / 1000;
        System.out.printf("\nпоиск элемента, мс = %.2f", timeDiff);
        return timeDiff;
    }

    public static float toFindLElemList(Random random, List<Object> ekz) {
        int j;
        long time = System.nanoTime();
        for (j = 1; j < 1001; j++) {
            ekz.contains(random.nextInt(400_000));
        }
        float timeDiff = (float) ((System.nanoTime() - time)) / j / 1000;
        System.out.printf("наличие  элемента, мс = %.2f", timeDiff);
        return timeDiff;
    }

    public static float toFindLElemSet(Random random, Set<Object> ekz) {
        int j;
        long time = System.nanoTime();
        for (j = 1; j < 1001; j++) {
            ekz.contains(random.nextInt(400_000));
        }
        float timeDiff = (float) ((System.nanoTime() - time)) / j / 1000;
        System.out.printf("наличие  элемента, мс = %.2f", timeDiff);
        return timeDiff;
    }

    public static void toGetElemList(Random random, List<Object> ekz) {
        int j;
        long time = System.nanoTime();
        for (j = 1; j < 1001; j++) {
            ekz.get(random.nextInt(400_000));
        }
        long timeDiff = (System.nanoTime() - time) / j / 1000;
        System.out.printf("\nполучение элемента, мс = %d", timeDiff);
    }

    public static float toRemove(Random random, Collection<Object> ekz) {
        int j;
        long time = System.nanoTime();
        for (j = 1; j < 1001; j++) {
            ekz.remove(random.nextInt(400_000));
        }
        float timeDiff = (float) ((System.nanoTime() - time)) / j / 1000;
        System.out.printf("\nудаление элемента, мс = %.2f", timeDiff);
        return timeDiff;
    }

    public static float toRemoveElemList(Random random, List<Object> ekz) {
        int j;
        long time = System.nanoTime();
        for (j = 1; j < 1001; j++) {
            ekz.remove(random.nextInt(400_000));
        }
        float timeDiff = (float) ((System.nanoTime() - time)) / j / 1000;
        System.out.printf("\nудаление элемента, мс = %.2f", timeDiff);
        return timeDiff;
    }

    public static float toRemoveElemSet(Random random, Set<Object> ekz) {
        int j;
        long time = System.nanoTime();
        for (j = 1; j < 1001; j++) {
            ekz.remove(random.nextInt(400_000));
        }
        float timeDiff = (float) ((System.nanoTime() - time)) / j / 1000;
        System.out.printf("\nудаление элемента, мс = %.2f", timeDiff);
        return timeDiff;
    }
}