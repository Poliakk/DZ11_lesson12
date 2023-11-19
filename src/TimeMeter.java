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

        long tAddC = 0, tFindC = 0, tRemoveC = 0;
        long tAddL, tAddS, tFindL, tFindS, tRemoveL, tRemoveS;
        //вместе
        System.out.println("Работа при типе Collection");
        for (int i = 0; i < 4; i++) {
            Collection<Object> ekz = ekzes.get(i);
            System.out.printf(ekz.getClass().getSimpleName());
            System.out.println("\nВремя выполнения операции:");
            tAddC = toFill(ekz);
            tFindC = toFind(random, ekz);
            tRemoveC = toRemove(random, ekz);
            System.out.println("\n");
        }

        //по отдельности
        System.out.println("===============================\nРабота при типе List и Set");
        for (int i = 0; i < 4; i++) {
            System.out.printf(ekzes.get(i).getClass().getSimpleName());
            System.out.println("\nВремя выполнения операции:");
            if (i < 2) {
                List<Object> ekz = (List<Object>) ekzes.get(i);
                ekz.clear();
                tAddL = toFillList(ekz);
                tFindL = toFindLElemList(random, ekz);
                toGetElemList(random, ekz);
                tRemoveL = toRemoveElemList(random, ekz);
                System.out.println("\nРазница времени выполнения операции при обращении через Collection и List");
                System.out.println("заполнение " + (tAddC - tAddL));
                System.out.println("поиск " + (tFindC - tFindL));
                System.out.println("удаление " + (tRemoveC - tRemoveL));
                System.out.println();
            } else {
                Set<Object> ekz = (Set<Object>) ekzes.get(i);
                ekz.clear();
                tAddS = toFillSet(ekz);
                tFindS = toFindLElemSet(random, ekz);
                tRemoveS = toRemoveElemSet(random, ekz);
                //сравнение обращения через Collection и List, Set
                System.out.println("\nРазница времени выполнения операции при обращении через Collection и Set");
                System.out.println("заполнение tAddC = " + tAddC + ", tAddS = " + tAddS + (tAddC - tAddS));
                System.out.println("поиск " + (tFindC - tFindS));
                System.out.println("удаление " + (tRemoveC - tRemoveS));
                System.out.println();
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
    public static long toFind(Random random, Collection<Object> ekz) {
        int j;
        long time = System.nanoTime();
        for (j = 1; j < 1001; j++) {
            ekz.contains(random.nextInt(400_000));
        }
        float timeDiff = (float) ((System.nanoTime() - time)) / j / 1000;
        System.out.printf("\nпоиск элемента, мс = %.2f", timeDiff);
        return (long) timeDiff;
    }

    public static long toFindLElemList(Random random, List<Object> ekz) {
        int j;
        long time = System.nanoTime();
        for (j = 1; j < 1001; j++) {
            ekz.contains(random.nextInt(400_000));
        }
        float timeDiff = (float) ((System.nanoTime() - time)) / j / 1000;
        System.out.printf("наличие  элемента, мс = %.2f", timeDiff);
        return (long) timeDiff;
    }

    public static long toFindLElemSet(Random random, Set<Object> ekz) {
        int j;
        long time = System.nanoTime();
        for (j = 1; j < 1001; j++) {
            ekz.contains(random.nextInt(400_000));
        }
        float timeDiff = (float) ((System.nanoTime() - time)) / j / 1000;
        System.out.printf("наличие  элемента, мс = %.2f", timeDiff);
        return (long) timeDiff;
    }

    public static void toGetElemList(Random random, List<Object> ekz) {
        int j;
        long time = System.nanoTime();
        for (j = 1; j < 1001; j++) {
            ekz.get(random.nextInt(400_000));
        }
        long timeDiff = (System.nanoTime() - time) / j;
        System.out.printf("\nполучение элемента, мс = %d", timeDiff);
    }

    public static long toRemove(Random random, Collection<Object> ekz) {
        int j;
        long time = System.nanoTime();
        for (j = 1; j < 1001; j++) {
            ekz.size();
            ekz.remove(random.nextInt(400_000));
        }
        float timeDiff = (float) ((System.nanoTime() - time)) / j / 1000;
        System.out.printf("\nудаление элемента, мс = %.2f", timeDiff);
        return (long) timeDiff;
    }

    public static long toRemoveElemList(Random random, List<Object> ekz) {
        ekz.size();
        int j;
        long time = System.nanoTime();
        for (j = 1; j < 1001; j++) {
            ekz.size();
            ekz.remove(random.nextInt(400_000));
            ekz.size();
        }
        float timeDiff = (float) ((System.nanoTime() - time)) / j / 1000;
        System.out.printf("\nудаление элемента, мс = %.2f", timeDiff);
        return (long) timeDiff;
    }

    public static long toRemoveElemSet(Random random, Set<Object> ekz) {
        ekz.size();
        int j;
        long time = System.nanoTime();
        for (j = 1; j < 1001; j++) {
            ekz.size();
            ekz.remove(random.nextInt(400_000));
            ekz.size();
        }
        float timeDiff = (float) ((System.nanoTime() - time)) / j / 1000;
        System.out.printf("\nудаление элемента, мс = %.2f", timeDiff);
        return (long) timeDiff;
    }
}