import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {

        if (args.length ==0){
            System.out.println("Не указан путь к файлу");
            return;
        }
        ArrayList<Float> list1 = getListInterval(args[0],"\\cash1.txt");
        ArrayList<Float> list2 = getListInterval(args[0],"\\cash2.txt.");
        ArrayList<Float> list3 = getListInterval(args[0],"\\cash3.txt");
        ArrayList<Float> list4 = getListInterval(args[0],"\\cash4.txt");
        ArrayList<Float> list5 = getListInterval(args[0],"\\cash5.txt");

        ArrayList<Float> sumList = getSumLengthQueue(list1,list2,list3,list4,list5);
        maxPeopleInterval(sumList);

    }
    public static void maxPeopleInterval(ArrayList<Float> listLengthQueue){
        int maxInterval = 0;
        for (int i = 1; i <listLengthQueue.size() ; i++) {
            if (listLengthQueue.get(i)> listLengthQueue.get(maxInterval)){
                maxInterval = i;
            }
        }
        maxInterval += 1;
        System.out.println(maxInterval);
    }


    public static ArrayList<Float> getSumLengthQueue(ArrayList<Float>...arrayLists){

        ArrayList<Float> sumLengthQueue = new ArrayList<>(100);
        for (int i = 0; i <arrayLists[0].size() ; i++) {
            sumLengthQueue.add(i,arrayLists[0].get(i));
        }
        for (int i = 1; i <arrayLists.length ; i++) {
            for (int j = 0; j < arrayLists[i].size() ; j++) {
                ArrayList<Float> listLenItv = arrayLists[i];
                Float sumNumber = sumLengthQueue.get(j) + listLenItv.get(j);
                sumLengthQueue.set(j,sumNumber);

            }
        }
        return sumLengthQueue;
    }

    public static ArrayList<Float> getListInterval(String filePath, String fileName){
        StringBuilder strBuild = new StringBuilder();

        try{
            String fullPath = filePath + fileName;
            FileReader reader  = new FileReader(fullPath);
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
        ArrayList<Float> listInterval = new ArrayList<>(20);
        for (int i = 0; i <arrayData.length ; i++) {
            Float number = Float.parseFloat(arrayData[i]);
            listInterval.add(i,number);
        }
        return listInterval;

    }
}
