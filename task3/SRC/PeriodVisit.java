import java.util.ArrayList;
import java.util.Collections;


public class PeriodVisit implements Comparable<PeriodVisit> {
    private short hour;
    private short minute;
    private int countVisitors = 0;
    public PeriodVisit(short hour, short minute){
        this.hour = hour;
        this.minute = minute;
    }
    @Override
    public int compareTo(PeriodVisit periodVisit) {
        return countVisitors - periodVisit.getCountVisitors();
    }
    public void countVisitors(ArrayList<Visitor> visitor) {
        for (Visitor person : visitor
                ) {
            if (hour == person.getStartHour() && hour == person.getEndHour() && minute <= person.getEndMinute() && minute >= person.getStartMinute())
                countVisitors += 1;
            else if (hour != person.getStartHour() && hour == person.getEndHour() && minute <= person.getEndMinute())
                countVisitors += 1;
            else if (hour == person.getStartHour() && hour < person.getEndHour() && minute >= person.getStartMinute())
                countVisitors += 1;
            else if (hour > person.getStartHour() && hour < person.getEndHour())
                countVisitors +=1;

        }
    }
    public static ArrayList<PeriodVisit> listTimeMaxVisit (ArrayList<PeriodVisit> listTimeVisit, ArrayList<Visitor> visitors){
        for (PeriodVisit visit: listTimeVisit
             ) {
            visit.countVisitors(visitors);
        }
        int maxCountVisitors = Collections.max(listTimeVisit).getCountVisitors();

        ArrayList<PeriodVisit> listTimeMaxVisit = new ArrayList<>();
        for (PeriodVisit visit:listTimeVisit
             ) {
            if (visit.getCountVisitors() == maxCountVisitors){
                listTimeMaxVisit.add(visit);
            }
        }
        return listTimeMaxVisit;
    }
    public static void getMaxPeriodVisit(ArrayList<PeriodVisit> periodVisitList, ArrayList<Visitor> listVisitor){
        ArrayList<PeriodVisit> timeVisit = PeriodVisit.listTimeMaxVisit(periodVisitList,listVisitor);

        PeriodVisit startPer = timeVisit.get(0);
        PeriodVisit endPer = timeVisit.get(0);
        String startMinute;
        String endMinute;
        for (int i = 0; i < timeVisit.size() - 1 ; i++) {


            if (timeVisit.get(i).hour == timeVisit.get(i+1).hour){
                if ((timeVisit.get(i).minute + 1) == timeVisit.get(i+1).minute){
                    endPer = timeVisit.get(i+1);
                    if (i == timeVisit.size()-2){
                        System.out.println(startPer.hour + ":" + String.format("%02d",startPer.minute) + "-" + endPer.hour + ":" + String.format("%02d",endPer.minute) + "\\n" + "\n");
                    }
                }else {
                    startPer = timeVisit.get(i+1);
                    System.out.println(startPer.hour + ":" + String.format("%02d",startPer.minute) + "-" + endPer.hour + ":" + String.format("%02d",endPer.minute) + "\\n" + "\n");
                }

            }else if ((timeVisit.get(i).minute+1) == 60 && timeVisit.get(i+1).minute == 0 && (timeVisit.get(i).hour+1) == timeVisit.get(i+1).hour){
                endPer = timeVisit.get(i+1);
                if (i == timeVisit.size()-2){
                    System.out.println(startPer.hour + ":" + String.format("%02d",startPer.minute) + "-" + endPer.hour + ":" + String.format("%02d",endPer.minute) + "\\n" + "\n");
                }
            }else {
                System.out.println(startPer.hour + ":" + String.format("%02d",startPer.minute) + "-" + endPer.hour + ":" + String.format("%02d",endPer.minute) + "\\n" + "\n");
                startPer = timeVisit.get(i+1);
            }

        }

    }


    public short getHour() {
        return hour;
    }

    public void setHour(short hour) {
        this.hour = hour;
    }

    public short getMinute() {
        return minute;
    }

    public void setMinute(short minute) {
        this.minute = minute;
    }

    public int getCountVisitors() {
        return countVisitors;
    }

    public void setCountVisitors(int countVisitors) {
        this.countVisitors = countVisitors;
    }


}
