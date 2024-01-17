package ru.kogtev;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        List<String> inputFilesPaths = new ArrayList<>();

        String outputDirectory = "";
        String filePrefix = "";
        boolean appendToFile = false;
        boolean fullStatistics = false;
        boolean shortStatistics = false;

        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
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

        Filter filter = new Filter(outputDirectory, filePrefix, appendToFile, fullStatistics, shortStatistics);

        filter.filter(inputFilesPaths);

    }
}

