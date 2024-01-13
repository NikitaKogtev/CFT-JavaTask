package ru.kogtev;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class FilterConsole {
    public static void main(String[] args) throws IOException {
        // cчитывание команды из консоли

        // запись консольных команд в переменные

        // чтение из файлов в один общий файл

        //-o: Устанавливает директорию вывода (по умолчанию текущая директория).
        //-p: Устанавливает префикс файлов.
        //-a: Режим добавления в существующие файлы.
        //-s: Включает краткую статистику.
        //-f: Включает полную статистику.



        String inputFile = "in1.txt";
        String inputFile1 = "in2.txt";

        List<String> inputFiles = new ArrayList<>();

        inputFiles.addAll(readFile(inputFile));

        System.out.println(inputFiles);

        inputFiles.addAll(readFile(inputFile1));

        System.out.println(inputFiles);

        List<String> integers = new ArrayList<>();
        List<String> floats = new ArrayList<>();
        List<String> strings = new ArrayList<>();

        for (String s : inputFiles) {

            try {
                Integer.parseInt(s);
                integers.add(s);
                continue;
            } catch (NumberFormatException ignored) {
            }

            try {
                Float.parseFloat(s);
                floats.add(s);
                continue;
            } catch (NumberFormatException ignored) {
            }

            strings.add(s);

        }

        System.out.println(integers);
        System.out.println(floats);
        System.out.println(strings);

        writeFile("integers.txt", integers);
        writeFile("floats.txt", floats);
        writeFile("strings.txt", strings);
    }

    public static List<String> readFile(String inputFile) throws IOException {

        List<String> inputFiles = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        String line = reader.readLine();

        while (line != null) {
            inputFiles.add(line);
            line = reader.readLine();
        }
        return inputFiles;
    }

    public static void writeFile(String outputFile, List<String> type) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        for(String x : type) {
            writer.write(x);
            writer.newLine();
        }
        writer.close();
    }
}



