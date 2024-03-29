package cphbusiness.groupone.system;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {


    public static void exceptionLog(String in){
        try(FileWriter fileWriter = new FileWriter("exceptionLog.txt", true)){
            fileWriter.write(in);
            fileWriter.append(" | ");
            fileWriter.append(LocalDateTime.now().toString());
            fileWriter.append("\n");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void consoleLog(String in){
        System.out.println(in);
    }
}
