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
        //вместе
        System.out.println("Работа при типе Collection");
        for (int i = 0; i < 4; i++) {
            Collection<Object> ekz = ekzes.get(i);
            System.out.printf(ekz.getClass().getSimpleName());
            System.out.println("\nВремя выполнения операции:");
            toFill(ekz);
            toFind(random, ekz);
            toRemove(random, ekz);
        }

        //по отдельности
        System.out.println("===============================\nРабота при типе List и Set");
        for (int i = 0; i < 4; i++) {
            System.out.printf(ekzes.get(i).getClass().getSimpleName());
            System.out.println("\nВремя выполнения операции:");
            if (i < 2) {
                List<Object> ekz = (List<Object>) ekzes.get(i);
                toFillList(ekz);
                toFindLElemList(random, ekz);
                toGetElemList(random, ekz);
                toRemoveElemList(random, ekz);
            } else {
                Set<Object> ekz = (Set<Object>) ekzes.get(i);
                toFillSet(ekz);
                toFindLElemSet(random, ekz);
                toRemoveElemSet(random, ekz);
            }
        }
    }
    //При исп-нии Collection при заполнении через рандом ищет и удаляет непонятно что,
    // начинается с 100к элементов и диапазона рандома. После добавления в тот же метод size() работает.

    //При исп-нии Collection при заполнении через присвоение элементу значения счетчика i
    // работает нормально.

    public static void toFill(Collection<Object> ekz) {
        long time = System.currentTimeMillis();
        for (int i = 1; i < 500_001; i++) {
            ekz.add(i);
        }
        long timeDiff = System.currentTimeMillis() - time;
        System.out.printf("заполнение коллекции за %d мс", timeDiff);
    }

    public static void toFillList(List<Object> ekz) {
        long time = System.currentTimeMillis();
        for (int i = 1; i < 500_001; i++) {
            ekz.add(i);
        }
        long timeDiff = System.currentTimeMillis() - time;
        System.out.printf("заполнение коллекции за %d мс", timeDiff);
    }
    public static void toFillSet(Set<Object> ekz) {
        long time = System.currentTimeMillis();
        for (int i = 1; i < 500_001; i++) {
            ekz.add(i);
        }
        long timeDiff = System.currentTimeMillis() - time;
        System.out.printf("заполнение коллекции за %d мс", timeDiff);
    }
    //Непрерывным циклом интов LinkedList заполняется быстрее всех,
    //затем HashSet, ArrayList, TreeSet.
    //С ручной вставкой HashSet, ArrayList, LinkedList, TreeSet.
    //Удаляет и ищет быстрее: HashSet, TreeSet (мкс), ArrayList, LinkedList(мс)
    // (на больших объемах линкд гораздо быстрее в удалении).
    public static void toFind(Random random, Collection<Object> ekz) {
        int j;
        long time = System.nanoTime();
        for (j = 1; j < 1001; j++) {
            ekz.contains(random.nextInt(500_000));
        }
        float timeDiff = (float) ((System.nanoTime() - time) / j / 1000.0);
        System.out.printf("\nпоиск элемента, мс = %.3f", timeDiff);
    }
    public static void toFindLElemList(Random random, List<Object> ekz) {
        int j;
        long time = System.nanoTime();
        for (j = 1; j < 1001; j++) {
            ekz.contains(random.nextInt(500_000));
        }
        float timeDiff = (float) ((System.nanoTime() - time) / j / 1000.0);
        System.out.printf("\nналичие элемента, мс = %.3f", timeDiff);
    }public static void toFindLElemSet(Random random, Set<Object> ekz) {
        int j;
        long time = System.nanoTime();
        for (j = 1; j < 1001; j++) {
            ekz.contains(random.nextInt(500_000));
        }
        float timeDiff = (float) ((System.nanoTime() - time) / j / 1000.0);
        System.out.printf("\nналичие элемента, мс = %.3f", timeDiff);
    }
    public static void toGetElemList(Random random, List<Object> ekz) {
        int j;
        long time = System.nanoTime();
        for (j = 1; j < 1001; j++) {
            ekz.get(random.nextInt(500_000));
        }
        float timeDiff = (float) ((System.nanoTime() - time) / j / 1000.0);
        System.out.printf("\nполучение элемента, мс = %.3f", timeDiff);
    }
    public static void toRemove(Random random, Collection<Object> ekz) {
        int j;
        long time = System.nanoTime();
        for (j = 1; j < 1001; j++) {
            ekz.remove(random.nextInt(500_000));
        }
        float timeDiff = (float) ((System.nanoTime() - time) / j / 1000.0);
        System.out.printf("\nудаление элемента, мс = %.3f\n\n", timeDiff);
    }
    public static void toRemoveElemList(Random random, List<Object> ekz) {
        int j;
        long time = System.nanoTime();
        for (j = 1; j < 1001; j++) {
            ekz.remove(random.nextInt(500_000));
        }
        float timeDiff = (float) ((System.nanoTime() - time) / j / 1000.0);
        System.out.printf("\nудаление элемента, мс = %.3f\n\n", timeDiff);
    }
    public static void toRemoveElemSet(Random random, Set<Object> ekz) {
        int j;
        long time = System.nanoTime();
        for (j = 1; j < 1001; j++) {
            ekz.remove(random.nextInt(500_000));
        }
        float timeDiff = (float) ((System.nanoTime() - time) / j / 1000.0);
        System.out.printf("\nудаление элемента, мс = %.3f\n\n", timeDiff);
    }
}