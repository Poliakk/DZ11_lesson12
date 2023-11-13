/* DZ11_lesson12
Расширим предыдущую задачу. Замерить время добавления, поиска и удаления объектов из коллекции
в LinkedList, ArrayList, TreeSet, HashSet.
Сравнить время и сделать вывод о том, в каких условиях какая коллекция работает быстрее.
 */

import java.util.*;

public class TimeMeter {
    public static void main(String[] args) {
        Random random = new Random();
        Object o = (Integer) 2_000_000; //"Ку-ку!";
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
            timeStart = System.currentTimeMillis();
            toFill(random, ekz, o);
            System.out.printf("Время заполнения %s, %d мс\n", ekz.getClass().getSimpleName(), timing(timeStart));

            System.out.println("Размер объекта: " + ekz.size());
            //toPrint(ekz);

            System.out.println("Время на операцию с элементом:");
            timeStart = (System.currentTimeMillis());
            boolean isFound = toFind(ekz, o);
            long findingT = timing(timeStart);

            timeStart = System.currentTimeMillis();
            boolean isRemoved = toRemove(ekz, o);
            long removingT = timing(timeStart);

            System.out.printf("поиск %dмс\tудаление %dмс\n",
                    findingT, removingT);

            System.out.println("Размер объекта: " + ekz.size() + "\n");

            //toPrint(ekz);
        }
    }

    public static long timing(long timeStart) {
        long timeStop = System.currentTimeMillis();
        long time = timeStop - timeStart;
        return time;
    }

    public static void toPrint(Collection<Object> ekz) {
        for (Object i : ekz) {
            System.out.print(" " + i);
        }
        System.out.println("\n" + ekz.size() + "\n");
    }

    public static void toFill(Random random, Collection<Object> ekz, Object o) {
        for (int i = 1; i < 500_000; i++) {
            ekz.add(random.nextInt(1000_000));
        }
        ekz.add(o);
    }

    //HashSet, TreeSet через Collection заполнилось неправильно.
    //В Collection нет get()
    //TreeSet строковый объект среди интеджеров не взял на заполнение
    public static boolean toFind(Collection<Object> ekz, Object o) {
        return ekz.contains(o);
    }

    public static boolean toRemove(Collection<Object> ekz, Object o) {
        return ekz.remove(o);
    }
}