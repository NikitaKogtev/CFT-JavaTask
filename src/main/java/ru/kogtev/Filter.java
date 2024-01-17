package ru.kogtev;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class Filter {

    private int amountLong = 0;
    private long maxLong;
    private long minLong;
    private double avgLong;
    private long sumLong;
    private int amountDouble = 0;
    private double maxDouble;
    private double minDouble;
    private double avgDouble;
    private double sumDouble;
    private String outputDirectory;
    private int amountStrings = 0;
    private int maxString;
    private int minString;

    String filePrefix;
    boolean appendToFile;
    boolean fullStatistics;
    boolean shortStatistics;

    public Filter(String outputDirectory, String filePrefix, boolean appendToFile, boolean fullStatistics, boolean shortStatistics) {
        this.outputDirectory = outputDirectory;
        this.filePrefix = filePrefix;
        this.appendToFile = appendToFile;
        this.fullStatistics = fullStatistics;
        this.shortStatistics = shortStatistics;
    }


    public void filter(List<String> inputFilesPaths) throws IOException {

        List<String> filesValue = new ArrayList<>();

        for (String str : inputFilesPaths) {
            filesValue.addAll(readFile(str));
        }

        List<String> longs = new ArrayList<>();
        List<String> doubles = new ArrayList<>();
        List<String> strings = new ArrayList<>();

        boolean isFirstLong = true;
        boolean isFirstDouble = true;
        boolean isFirstString = true;

        for (String s : filesValue) {

            try {
                long value = Long.parseLong(s);

                if (fullStatistics) {
                    getLongStatistic(value, isFirstLong);
                }

                longs.add(s);
                isFirstLong = false;

                if (shortStatistics || fullStatistics) {
                    amountLong++;
                }

                continue;
            } catch (NumberFormatException ignored) {
            }

            try {
                double value = Double.parseDouble(s);
                if (fullStatistics) {
                    getDoubleStatistic(value, isFirstDouble);
                }

                doubles.add(s);
                isFirstDouble = false;

                if (shortStatistics || fullStatistics) {
                    amountDouble++;
                }
                continue;
            } catch (NumberFormatException ignored) {
            }


            if (fullStatistics) {
                getStringStatistic(s, isFirstString);
            }

            strings.add(s);

            isFirstDouble = false;

            if (shortStatistics || fullStatistics) {
                amountStrings++;
            }

        }

        avgLong = (double) sumLong / amountLong;
        avgDouble = sumDouble / amountDouble;

        writeFile("integers.txt", longs);
        writeFile("floats.txt", doubles);
        writeFile("strings.txt", strings);

        System.out.println(amountStrings);
    }


    public List<String> readFile(String inputFile) throws IOException {

        List<String> inputFiles = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        String line = reader.readLine();

        while (line != null) {
            inputFiles.add(line);
            line = reader.readLine();
        }
        return inputFiles;
    }

    public void writeFile(String outputFile, List<String> type) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        for (String x : type) {
            writer.write(x);
            writer.newLine();
        }
        writer.close();
    }

    public void getLongStatistic(long value, boolean isFirstLong) {
        if (isFirstLong) {
            maxLong = value;
            minLong = value;
        } else if (value > maxLong) {
            maxLong = value;
        } else if (value < minLong) {
            minLong = value;
        }
        sumLong += value;
    }

    public void getDoubleStatistic(double value, boolean isFirstDouble) {
        if (isFirstDouble) {
            maxDouble = value;
            minDouble = value;
        } else if (value > maxDouble) {
            maxDouble = value;
        } else if (value < minDouble) {
            minDouble = value;
        }
        sumDouble += value;
    }

    public void getStringStatistic(String value, boolean isFirstString) {
        if (isFirstString) {
            maxString = value.length();
            minDouble = value.length();
        } else if (value.length() > maxString) {
            maxDouble = value.length();
        } else if (value.length() < minString) {
            minString = value.length();
        }
    }



    public void getStatistic(boolean fullStatistics, boolean shortStatistics) {
       if (shortStatistics) {

       }
    }
}



