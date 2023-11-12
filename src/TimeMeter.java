/* DZ11_lesson12
Расширим предыдущую задачу. Замерить время добавления, поиска и удаления объектов из коллекции
в LinkedList, ArrayList, TreeSet, HashSet.
Сравнить время и сделать вывод о том, в каких условиях какая коллекция работает быстрее.
 */

import java.util.*;

public class TimeMeter {
    public static void main(String[] args) {
        long timeStart, timeStop;
        ArrayList<Object> obj2 = new ArrayList<>();
        LinkedList<Object>  obj1 = new LinkedList<>();
        TreeSet<Object> obj3 = new TreeSet<>();
        HashSet<Object> obj4 = new HashSet<>();

        timeStart = System.currentTimeMillis();
        lListToFill(obj1);
        long time = timing(timeStart);
        System.out.printf("Время заполнения %s, %d мс", obj1.getClass().getSimpleName(), time);
        System.out.println("\nВремя на операцию с элементом, мс");
        //System.out.printf("LinkedList\nдобавление: %\tпоиск: %\tудаление: %", );
    }
        public static long timing(long timeStart){
            long timeStop = System.currentTimeMillis();
            long time = timeStop - timeStart;
            return time;
        }

    public static void lListToFill (List<Object> obj1) {
        for (int i = 0; i < 500000; i++) {
            obj1.add(new Object());
        }
    }
      /*  timeStart = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            arrayList.add(new Object());
        }
        timeStop = System.currentTimeMillis();
        long arrayListTime = timeStop - timeStart;

        System.out.println("Время заполнения, мс");
        System.out.println("LinkedList: " + linkedListTime);
        System.out.println("ArrayList: " + arrayListTime);

        System.out.print("Разница, мс: " + (arrayListTime - linkedListTime));
    }*/
}