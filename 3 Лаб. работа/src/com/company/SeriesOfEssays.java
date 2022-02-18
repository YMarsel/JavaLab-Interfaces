package com.company;

import java.util.Arrays;

public class SeriesOfEssays implements Library
{
    private int[] ArrayOfPagesInEssay;
    private String SeriesName;
    private int CountOfIntroPagesInBook;

    SeriesOfEssays(){};
    SeriesOfEssays(int[] pages, String seriesName,int introPages)
    {
        this.ArrayOfPagesInEssay = pages;
        this.SeriesName = seriesName;
        this.CountOfIntroPagesInBook = introPages;
    }

    public void SetPagesArray(int[] array)
    {
        this.ArrayOfPagesInEssay = array;
    }
    public int[] GetPagesArray()
    {
        return ArrayOfPagesInEssay;
    }
    public String GetName()
    {
        return SeriesName;
    }
    @Override
    public void SetName(String name) throws ValidationException {
        if (name.isEmpty())
            throw new ValidationException("Имя не может быть пустым!", new NullPointerException());
        if (name.length() > 100)
            throw new ValidationException("Имя не может быть длинее 100 символов!", new IllegalArgumentException());
        this.SeriesName = name;
    }
    public int GetPageCount (int pageNumber)
    {
        if (pageNumber > ArrayOfPagesInEssay.length || pageNumber < 0)
            throw new OutOfBounds("Выход за пределы массива", new RuntimeException());
        return ArrayOfPagesInEssay[pageNumber];
    }
    public void SetPageCount(int pageNumber, int value) throws OutOfBounds
    {
        if (value<0)
            throw new OutOfBounds("Не существует страницы с отрицательным номером!", new RuntimeException());
        if (pageNumber > ArrayOfPagesInEssay.length || pageNumber < 0)
            throw new OutOfBounds("Выход за пределы массива", new RuntimeException());
        ArrayOfPagesInEssay[pageNumber] = value;
    }
    public int QuotedPages()
    {
        int sumOfIntroPages;
        int countOfPagesInSeries = 0;
        for (int i = 0; i< ArrayOfPagesInEssay.length; i++)
        {
            countOfPagesInSeries += ArrayOfPagesInEssay[i];
        }
        sumOfIntroPages = ArrayOfPagesInEssay.length * CountOfIntroPagesInBook;
        return countOfPagesInSeries -= sumOfIntroPages;
    }
    public void SetCountOfUnquotedPages(int count)
    {
        this.CountOfIntroPagesInBook = count;
    }
    public int GetCountOfUnquotedPages()
    {
        return CountOfIntroPagesInBook;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SeriesOfEssays other = (SeriesOfEssays) obj;
        if (ArrayOfPagesInEssay != other.ArrayOfPagesInEssay)
            return false;
        if (SeriesName != other.SeriesName)
            return false;
        if (CountOfIntroPagesInBook != other.CountOfIntroPagesInBook)
            return false;
        return true;
    }

    @Override
    public boolean SameType(Object obj)
    {
        if (obj instanceof SeriesOfEssays)
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((SeriesName == null) ? 0 : SeriesName.hashCode());
        result = prime * result + ((ArrayOfPagesInEssay == null) ? 0 : Arrays.hashCode(ArrayOfPagesInEssay));
        result = prime * result + ((CountOfIntroPagesInBook == 0) ? 0 : CountOfIntroPagesInBook);
        return result;
    }

    @Override
    public String toString()
    {
        return "SeriesOfEssays{" +
                "ArrayOfPagesInEssay=" + Arrays.toString(ArrayOfPagesInEssay) +
                ", SeriesName='" + SeriesName + '\'' +
                ", CountOfIntroPagesInBook=" + CountOfIntroPagesInBook +
                '}';
    }
}
