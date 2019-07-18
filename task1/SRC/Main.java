import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;


public class Main {

    public static void main(String[] args) {

        if (args.length ==0){
            System.out.println("Не указан путь к файлу");
            return;
        }
        ArrayList<Short>  listNumber= getListNumber(args[0],1000);
        System.out.println(getPercentile(listNumber,90));
        System.out.println(getPercentile(listNumber,50));
        getMaxValue(listNumber);
        getMinValue(listNumber);
        getAvgValue(listNumber);

    }

    public static ArrayList<Short>  getListNumber(String filePath,int listCapacity){
        ArrayList<Short> listNumber = new ArrayList<>(listCapacity);
        String regex = "-?\\d+";
        System.out.println(filePath);

        try{
            FileReader reader  = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String bufReader = bufferedReader.readLine();
            while (bufReader != null){
                if (bufReader.matches(regex)){
                    short num = Short.parseShort(bufReader);
                    listNumber.add(num);
                }
                bufReader = bufferedReader.readLine();
            }
            bufferedReader.close();
        }catch (IOException exp){
            System.out.println("Файл не найден");
            exp.printStackTrace();
        }
        Collections.sort(listNumber);

        return listNumber;

    }


    public static void getMinValue (ArrayList<Short> listNumber){
        if (listNumber.isEmpty()){
            return;
        }
        DecimalFormat decFor = new DecimalFormat("#.00");
        System.out.println(decFor.format(Collections.min(listNumber)) + "\\n");
    }
    public static void getMaxValue (ArrayList<Short> listNumber){
        if (listNumber.isEmpty()){
            return;
        }
        DecimalFormat decFor = new DecimalFormat("#.00");
        System.out.println(decFor.format(Collections.max(listNumber)) + "\\n");
    }
    public static void getAvgValue (ArrayList<Short> listNumber){
        if (listNumber.isEmpty()){
            return;
        }
        DecimalFormat decFor = new DecimalFormat("#.00");
        int sum = 0;
        for (Short num: listNumber
                ) {
            sum = sum + num;

        }
        double avg = (double)sum/listNumber.size();
        System.out.println(decFor.format(avg) + "\\n");

    }

    public static String getPercentile(ArrayList<Short> listNumber, int percentile ){
        if (listNumber.isEmpty()){
            return "ArrayList пустой";
        }
        Collections.sort(listNumber);
        DecimalFormat df = new DecimalFormat("#.00");
        float serialNum = percentile*(float)(listNumber.size()-1)/100 + 1;
        int serialRang = (int) serialNum;
        if (serialNum%1 == 0){
            String result = (df.format(listNumber.get(serialRang)-1) + "\\n");
            return result;
        }
        float remainderDivision = serialNum%1;
        float percentileResult =
                listNumber.get(serialRang-1) +  remainderDivision*(listNumber.get(serialRang) - listNumber.get(serialRang-1));
        String result = (df.format(percentileResult) + "\\n");
        return result;
    }
}
