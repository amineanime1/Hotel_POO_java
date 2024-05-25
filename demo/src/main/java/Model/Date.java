package Model;

import java.util.Arrays;
import java.util.List;

class DayError extends Exception{
    public DayError(String msg){
        super(msg);
    }
}
class NegativeNumber extends Exception{
    public NegativeNumber(String msg){
        super(msg);
    }
}
class MonthError  extends Exception{
    public MonthError (String msg){
        super(msg);
    }
}

public class Date {
    private int day;
    private int month;
    private int year;
    List<Integer> month31 = Arrays.asList(1,3,5,7,8,10,12);
    List<Integer> month30 = Arrays.asList(4,6,9,11);

    public Date(int d, int m, int y) throws DayError, NegativeNumber, MonthError {
        if (d < 0 || m < 0 || y < 0) throw new NegativeNumber("Date attributes cannot be negative.");
        if (m < 1 || m > 12) throw new MonthError("Month must be between 1 and 12.");
        if ((month31.contains(m) && (d < 1 || d > 31)) ||
                (month30.contains(m) && (d < 1 || d > 30)) ||
                (m == 2 && (d < 1 || d > (isLeapYear(y) ? 29 : 28)))) {
            throw new DayError("Invalid day for the given month.");
        }

        this.day = d;
        this.month = m;
        this.year = y;
    }

    public int getDay(){return this.day;}
    public int getMonth(){return this.month;}
    public int getYear(){return this.year;}

    public void setDay(int d) throws DayError, NegativeNumber{
        if (d < 0) throw new NegativeNumber("Day cannot be negative.");
        if ((month31.contains(this.month) && (d < 1 || d > 31)) ||
                (month30.contains(this.month) && (d < 1 || d > 30)) ||
                (this.month == 2 && (d < 1 || d > (isLeapYear(this.year) ? 29 : 28)))) {
            throw new DayError("Invalid day for the given month.");
        }

        this.day = d;}
    public void setMonth(int m)throws MonthError,DayError{
        if (m < 1 || m > 12) throw new MonthError("Month must be between 1 and 12.");
        if ((month30.contains(m) && this.day == 31) ||
                (m == 2 && this.day > (isLeapYear(this.year) ? 29 : 28))) {
            throw new DayError("Invalid day for the given month.");
        }
        this.month = m;}
    public void setYear(int y) throws NegativeNumber,DayError{
        if (y < 0) throw new NegativeNumber("Year cannot be negative.");
        if (this.month == 2 && this.day == 29 && !isLeapYear(y)) {
            throw new DayError("Invalid day for the given month.");
        }this.year = y;}

    private boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            } else {
                return true;
            }
        }
        return false;
    }
    public boolean isBefore(Date other) {
        if (this.year < other.year) return true;
        if (this.year == other.year) {
            if (this.month < other.month) return true;
            if (this.month == other.month) {
                return this.day < other.day;
            }
        }
        return false;
    }
    public static Date fromString(String dateStr) throws DayError, NegativeNumber, MonthError {
        String[] parts = dateStr.split("/");
        if (parts.length != 3) {
            System.out.println("dd/MM/yyyy");
        }
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        return new Date(day, month, year);
    }
}