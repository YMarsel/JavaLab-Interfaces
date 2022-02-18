package com.company;
import java.util.ArrayList;
import java.util.Scanner;

/*
TODO:
Организовать базу (массив) объектов типа интерфейс, которую заполнить объектами описанных типов вперемежку (по желанию пользователя).
Выполнить следующие действия:
- вывести полную информацию обо всех объектах массива; +
- найти в массиве объекты, функциональный метод которых возвращают одинаковый результат, поместить такие объекты в другой массив +
- разбить исходный массив на два массива, в которых будут храниться однотипные элементы +
 */

public class Main
{
    private static Scanner scan = new Scanner(System.in);
    private static int objectsCount;
    private static ArrayList<Library> Texts = new ArrayList<>();
    private static ArrayList<Library> SameResult = new ArrayList<>();
    private static ArrayList<CollectionOfArticles> Collections = new ArrayList<>();
    private static ArrayList<SeriesOfEssays> Series = new ArrayList<>();

    public static void main(String[] args)
    {
        SetObjectsCount(Integer.parseInt(scan.nextLine()));
        FillArrayList(objectsCount);
        OutputArray(Texts);
    }

    public static void SplitArrayByType(ArrayList arr)
    {
        for(int i = 0; i< arr.size(); i++)
        {
            if (arr.get(i) instanceof CollectionOfArticles)
                Collections.add((CollectionOfArticles) arr.get(i));
            if (arr.get(i) instanceof SeriesOfEssays)
                Series.add((SeriesOfEssays)arr.get(i));
        }
    }

    public static void ArrayOfSameResults()
    {
        for (int start = 0; start < Texts.size()-1; start++) {
            for (int index = start + 1; index < Texts.size(); index++)
            {
                {
                    if (Texts.get(start).QuotedPages() == Texts.get(index).QuotedPages())
                    {
                        SameResult.add(Texts.get(start));
                        SameResult.add(Texts.get(index));
                    }
                }
            }
        }
    }
    public static void OutputArray(ArrayList arr)
    {
        for(int i = 0; i< arr.size(); i++)
        {
            System.out.println(arr.get(i));
        }
    }

    public static void FillArrayList(int count)
    {
        int i;
        for (i=0;i<count;i++)
        {
            GenerateNewObject();
        }
    }

    public static void SetObjectsCount(int count)
    {
        if (!(count < 0))
        {
            objectsCount = count;
        }
        else
        {
            System.out.println("Введено некорректное значение! Количество элементов не может равняться нулю или меньше нуля");
        }
    }
    public static int GetObjectsCount()
    {
        return objectsCount;
    }

    private static void GenerateNewObject()
    {
        String inputType;
        String inputName;
        int inputCount = 0;
        int inputUnquotedPagesCount;

        System.out.println("Какой экземпляр создать? Серию(книг) или коллекцию(статей)? Введите слово Серия или слово Коллекция: ");
            inputType = scan.nextLine();
        System.out.println("Введите имя серии/коллекции: ");
            inputName = scan.nextLine();
        System.out.println("Введите кол-во книг или статей: ");
            inputCount = Integer.parseInt(scan.nextLine());
        System.out.println("Сколько из страниц незначащие (Вводная информация или аннотация):");
        inputUnquotedPagesCount = Integer.parseInt(scan.nextLine());
        int[] temp = new int[inputCount];

        //Заполнение случайными числами от 10 до 130
        int begin=10;
        int end = 130;
        for (int i =0;i<inputCount;i++)
        {
            temp[i] = begin + (int)(Math.random() * 130);
        }

        //Создаём экземпляр нужный пользователю и передаём его в массив интерфейса
        if (inputType.equals("Серия") || inputType.equals("серия") || inputType.equals("СЕРИЯ"))
        {
            Texts.add(new SeriesOfEssays(temp,inputName,inputUnquotedPagesCount));
        }
        if (inputType.equals("Коллекция") || inputType.equals("коллекция") || inputType.equals("КОЛЛЕКЦИЯ"))
        {
            Texts.add(new CollectionOfArticles(temp,inputName,inputUnquotedPagesCount));
        }
    }
}

class ValidationException extends Exception
{
    ValidationException(String errorMessage,Throwable err)
    {
        super(errorMessage, err);
    }
}

class OutOfBounds extends RuntimeException
{
    OutOfBounds(String errorMessage, Throwable err)
    {
        super(errorMessage,err);
    }
}

interface Library
{
    String GetName();
    boolean SameType(Object obj);
    void SetName(String name) throws ValidationException;
    void SetPagesArray(int[] array);
    void SetPageCount(int pageNumber, int value);
    void SetCountOfUnquotedPages(int count);
    int[] GetPagesArray();
    int GetPageCount (int pageNumber);
    int QuotedPages();
    int GetCountOfUnquotedPages();
}