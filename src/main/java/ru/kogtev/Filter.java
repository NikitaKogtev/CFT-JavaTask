package ru.kogtev;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
    private boolean isFirstLong = true;
    private boolean isFirstDouble = true;
    private boolean isFirstString = true;
    private long parsedLong;
    private double parsedDouble;
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


        for (String s : filesValue) {

            if (tryParseLong(s)) {
                getLongStatistic(parsedLong);
                longs.add(s);
                continue;
            }

            if (tryParseDouble(s)) {
                getDoubleStatistic(parsedDouble);
                doubles.add(s);
                continue;
            }

            getStringStatistic(s);
            strings.add(s);
        }

        avgLong = (double) sumLong / amountLong;
        avgDouble = sumDouble / amountDouble;

        printStatistic();

        writeFile("integers.txt", longs);
        writeFile("floats.txt", doubles);
        writeFile("strings.txt", strings);

    }

    private List<String> readFile(String inputFile) throws IOException {

        List<String> inputFiles = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        String line = reader.readLine();

        while (line != null) {
            inputFiles.add(line);
            line = reader.readLine();
        }
        return inputFiles;
    }

    private boolean tryParseLong(String value) {
        try {
            parsedLong = Long.parseLong(value);
        } catch (NumberFormatException ignored) {
            return false;
        }
        return true;
    }

    private boolean tryParseDouble(String value) {
        try {
            parsedDouble = Double.parseDouble(value);
        } catch (NumberFormatException ignored) {
            return false;
        }
        return true;
    }


    private void writeFile(String fileName, List<String> type) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(outputDirectory + filePrefix + fileName, appendToFile));

        for (String x : type) {
            writer.write(x);
            writer.newLine();
        }
        writer.close();
    }

    private void getLongStatistic(long value) {
        if (fullStatistics || shortStatistics) {
            if (fullStatistics) {
                if (isFirstLong) {
                    maxLong = value;
                    minLong = value;
                    isFirstLong = false;
                } else if (value > maxLong) {
                    maxLong = value;
                } else if (value < minLong) {
                    minLong = value;
                }
                sumLong += value;
            }
            amountLong++;
        }
    }

    private void getDoubleStatistic(double value) {
        if (fullStatistics || shortStatistics) {
            if (fullStatistics) {
                if (isFirstDouble) {
                    maxDouble = value;
                    minDouble = value;
                    isFirstDouble = false;
                } else if (value > maxDouble) {
                    maxDouble = value;
                } else if (value < minDouble) {
                    minDouble = value;
                }
                sumDouble += value;
            }
            amountDouble++;
        }
    }

    private void getStringStatistic(String value) {
        if (fullStatistics || shortStatistics) {
            if (fullStatistics) {
                if (isFirstString) {
                    maxString = value.length();
                    minString = value.length();
                } else if (value.length() > maxString) {
                    maxString = value.length();
                } else if (value.length() < minString) {
                    minString = value.length();
                }
            }
            amountStrings++;
        }
    }


    private void printStatistic() {
        if (shortStatistics) {
            System.out.println("Количество элементов записанных в {" +
                    "integers.txt = " + amountLong +
                    ", floats.txt =" + amountDouble +
                    ", strings.txt =" + amountStrings +
                    '}');
        }
        if (fullStatistics) {
            System.out.println("Количество элементов записанных в {" +
                    "integers.txt = " + amountLong +
                    ", floats.txt =" + amountDouble +
                    ", strings.txt =" + amountStrings +
                    "\n" +
                    "Минимальное значение элементов в {" +
                    "integers.txt = " + minLong +
                    ", floats.txt = " + minDouble +
                    ", strings.txt = " + minString +
                    "\n" +
                    "Максимальное значение элементов в {" +
                    "integers.txt = " + maxLong +
                    ", floats.txt = " + maxDouble +
                    ", strings.txt = " + maxString +
                    "\n" +
                    "Сумма значений элементов в {" +
                    "integers.txt = " + sumLong +
                    ", floats.txt = " + sumDouble +
                    "\n" +
                    "Среднее значений элементов в {" +
                    "integers.txt = " + avgLong +
                    ", floats.txt = " + avgDouble +
                    '}');
        }
    }

}


