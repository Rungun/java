import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Visitor> visitors =  getListVisit(args[0]);
        ArrayList<PeriodVisit> periodVisit = getListPeriodVisit((short) 8,(short) 20);
        PeriodVisit.getMaxPeriodVisit(periodVisit,visitors);
    }


    public static ArrayList<PeriodVisit> getListPeriodVisit(short start, short end){
        short period = (short) (end - start);
        short countMinute = (short) (period*60);
        ArrayList<PeriodVisit> periodVisit = new ArrayList<>(1000);
        short minute = 0;
        for (int i = 0; i < countMinute ; i++) {
            if (i%60 == 0 && i!=0){
                start  += 1;
                minute = 0;
            }
            periodVisit.add(i,new PeriodVisit(start, minute));
            minute  +=1;
        }
        return periodVisit;
    }


    public static ArrayList<Visitor> getListVisit(String filePath){
        StringBuilder strBuild = new StringBuilder();

        try{
            FileReader reader  = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String bufReader = bufferedReader.readLine();
            while (bufReader != null){
                strBuild.append(bufReader);
                bufReader = bufferedReader.readLine();
            }
            bufferedReader.close();
        }catch (IOException exp){
            System.out.println("Файл не найден");
            exp.printStackTrace();
        }
        String data = strBuild.toString();
        String[] arrayData = data.split("\\\\[n]");
        ArrayList<Visitor> listVisitor = new ArrayList<>(100);
        for (int i = 0; i <arrayData.length ; i++) {
            listVisitor.add(new Visitor(arrayData[i]));
        }
        return listVisitor;
    }
}
