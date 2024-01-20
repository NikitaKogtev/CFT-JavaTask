package ru.kogtev;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> inputFilesPaths = new ArrayList<>();

        String outputDirectory = "";
        String filePrefix = "";
        boolean appendToFile = false;
        boolean fullStatistics = false;
        boolean shortStatistics = false;

        try {
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case ("-o"):
                        outputDirectory = args[++i];
                        break;
                    case ("-p"):
                        filePrefix = args[++i];
                        break;
                    case ("-a"):
                        appendToFile = true;
                        break;
                    case ("-f"):
                        fullStatistics = true;
                        break;
                    case ("-s"):
                        shortStatistics = true;
                        break;
                    default:
                        inputFilesPaths.add(args[i]);
                        break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Аргументы введены неверно");
        }

        Filter filter = new Filter(outputDirectory, filePrefix, appendToFile, fullStatistics, shortStatistics);

        try {
            filter.filter(inputFilesPaths);
        } catch (FileNotFoundException e) {
            System.out.println("Указанный файл не найден");
        } catch (IOException e) {
            System.out.println("Проблема с файлами");
        }
    }
}

