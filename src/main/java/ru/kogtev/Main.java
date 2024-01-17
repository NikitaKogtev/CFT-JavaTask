package ru.kogtev;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        //-o: Устанавливает директорию вывода (по умолчанию текущая директория).
        //-p: Устанавливает префикс файлов.
        //-a: Режим добавления в существующие файлы.
        //-s: Включает краткую статистику.
        //-f: Включает полную статистику.
        /*В процессе фильтрации данных необходимо собирать статистику по каждому типу данных.
        Статистика должна поддерживаться двух видов: краткая и полная. Выбор статистики производится опциями -s и -f соответственно.
        Краткая статистика содержит только количество элементов записанных в исходящие файлы. Полная статистика для чисел
        дополнительно содержит минимальное и максимальное значения, сумма и среднее.
        Полная статистика для строк, помимо их количества, содержит также размер самой
        короткой строки и самой длинной.*/

        //java -jar util.jar -s -a -p sample- in1.txt in2.txt

        System.out.println("Hello!");

        List<String> inputFilesPaths = new ArrayList<>();

        String outputDirectory = " ";
        String filePrefix = " ";
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
                case ("-s"):
                    shortStatistics = true;
                    break;
                case ("-f"):
                    fullStatistics = true;
                    break;
                default:
                    inputFilesPaths.add(args[i]);
                    break;
            }
        }

        Filter filter = new Filter(outputDirectory,filePrefix,appendToFile,shortStatistics,fullStatistics);

        filter.filter(inputFilesPaths);

    }
}

