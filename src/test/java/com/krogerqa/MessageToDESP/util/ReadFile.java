package com.krogerqa.MessageToDESP.util;


import java.io.File;  // Import the File class
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ReadFile {

    public void LastStatusCode(String filepath) {
        String data = null;
        Scanner myReader;
        try {
            File myObj = new File(filepath);
            myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
//            System.out.println(data);

            String[] Separted_TimmingLog = data.split("\\n");
            Integer arySizeTimingLog = Separted_TimmingLog.length;
            String Latest_TimingLog = Separted_TimmingLog[arySizeTimingLog - 1].toString();
            System.out.println("last timing log is :-"+ Latest_TimingLog);

            String[] st = Latest_TimingLog.split(",");
            Integer sz = st.length;
            String status = st[sz -1].toString() ;
            System.out.println("Status is  :-"+ status);


            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
