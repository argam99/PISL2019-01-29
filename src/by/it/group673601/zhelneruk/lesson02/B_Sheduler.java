package lesson02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
даны интервальные события events
реализуйте метод calcStartTimes, так, чтобы число принятых к выполнению
непересекающихся событий было максимально.

Алгоритм жадный. Для реализации обдумайте надежный шаг.

*/

public class B_Sheduler  {
    //событие у аудитории(два поля: начало и конец)
    static class Event implements Comparable<Event>{
        int start;
        int stop;

        Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Event o) {
            if (this.start > o.start) { return 1;
            } else if (this.start == o.start) { return 0; }
            return -1;
        }

        @Override
        public String toString() {
            return "("+ start +":" + stop + ")";
        }
    }

    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = {  new Event(0, 3),  new Event(0, 1), new Event(1, 2), new Event(3, 5),
                            new Event(1, 3),  new Event(1, 3), new Event(1, 3), new Event(3, 6),
                            new Event(2, 7),  new Event(2, 3), new Event(2, 7), new Event(7, 9),
                            new Event(3, 5),  new Event(2, 4), new Event(2, 3), new Event(3, 7),
                            new Event(4, 5),  new Event(6, 7), new Event(6, 9), new Event(7, 9),
                            new Event(8, 9),  new Event(4, 6), new Event(8, 10), new Event(7, 10)
                          };

        List<Event> starts = instance.calcStartTimes(events,0,10);  //рассчитаем оптимальное заполнение аудитории
        System.out.println(starts);                                 //покажем рассчитанный график занятий
    }

    List<Event> calcStartTimes(Event[] events, int from, int to) {
        List<Event> listresult = new ArrayList<>();
        Arrays.sort(events);
        System.out.println(Arrays.asList(events));
        for (Event e : events) {

            if (listresult.isEmpty()) { //проходим второй раз (не пустой)
                listresult.add(e);
            } else if (e.start == listresult.get(listresult.size() - 1).start && e.stop < listresult.get(listresult.size() - 1).stop) {
                listresult.remove(listresult.get(listresult.size() - 1));
                listresult.add(e);

            } else if (e.start >= listresult.get(listresult.size() - 1).stop) {
                listresult.add(e);

            }
        }
        return listresult;
    }
}
