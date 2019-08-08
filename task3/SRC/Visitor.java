public class Visitor {
    private String timeVisit;
    private short startHour;
    private short startMinute;
    private short endHour;
    private short endMinute;
    private short visitDuration;
    public Visitor(String timeVisit){
        this.timeVisit = timeVisit;
        String[] timeStartEnd = timeVisit.split("\\s");
        String timeStart = timeStartEnd[0];
        String timeEnd = timeStartEnd[1];
        String[] hourMinuteStart =timeStart.split("\\:");
        String[] hourMinuteEnd =timeEnd.split("\\:");
        Short[] start = {Short.parseShort(hourMinuteStart[0]),Short.parseShort(hourMinuteStart[1])};
        this.startHour =start[0];
        this.startMinute=start[1];
        Short[] end = {Short.parseShort(hourMinuteEnd[0]),Short.parseShort(hourMinuteEnd[1])};
        this.endHour = end[0];
        this.endMinute = end[1];
        if ((end[0] - start[0]) == 0){
            this.visitDuration =(short) (end[1] - start[1]);
        }else {
            short difHour = (short)(end[0]- start[0]);
            this.visitDuration = (short)(difHour*60 + (end[1] - start[1]));
        }
    }

    public short getVisitDuration() {
        return visitDuration;
    }

    public short getStartHour() {
        return startHour;
    }

    public short getStartMinute() {
        return startMinute;
    }

    public short getEndHour() {
        return endHour;
    }

    public short getEndMinute() {
        return endMinute;
    }

    public String getTimeVisit() {
        return timeVisit;
    }
}
