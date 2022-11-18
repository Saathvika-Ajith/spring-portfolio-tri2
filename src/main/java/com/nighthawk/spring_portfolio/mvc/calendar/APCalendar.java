package com.nighthawk.spring_portfolio.mvc.calendar;

// Prototype Implementation

public class APCalendar {

    /** Returns true if year is a leap year and false otherwise.
     * isLeapYear(2019) returns False
     * isLeapYear(2016) returns True
     */          
    public static boolean isLeapYear(int year) {
        if (((year % 4 == 0) && (year % 100!= 0)) || (year%400 == 0)) {
            return true;
        }

        else {
            return false;
        }
    }
        
    /** Returns the value representing the day of the week 
     * 0 denotes Sunday, 
     * 1 denotes Monday, ..., 
     * 6 denotes Saturday. 
     * firstDayOfYear(2019) returns 2 for Tuesday.
    */
    static int firstDayOfYear(int year) {
        int lyear, rest, totaldays, day;
    
        // Count years between
        year = (year - 1) - 1899;
    
        // Count leap years
        lyear = year / 4;
    
        // Non leap years
        rest = year - lyear;
    
        // Total number of days in the years lying between the years
        totaldays = (rest * 365) + (lyear * 366) + 1;
    
        // Actual day
        day = (totaldays % 7);
    
        return day;
    }


    /** Returns n, where month, day, and year specify the nth day of the year.
     * This method accounts for whether year is a leap year. 
     * dayOfYear(1, 1, 2019) return 1
     * dayOfYear(3, 1, 2017) returns 60, since 2017 is not a leap year
     * dayOfYear(3, 1, 2016) returns 61, since 2016 is a leap year. 
    */ 
    private static int dayOfYear(int month, int day, int year) {
        int days[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        
        if (month > 2 && year % 4 == 0) {
        ++day;
        }
 
        // Add the days in the previous months
        while (month-- > 1) {
            day = day + days[month - 1];
        }

        return day;
    }

    /** Returns the number of leap years between year1 and year2, inclusive.
     * Precondition: 0 <= year1 <= year2
    */ 
    public static int numberOfLeapYears(int year1, int year2) {
         // Count years between
        int years = (year2 - 1) - year1;
    
        // Count leap years
        int lyears = years / 4;

        return lyears;
        }

    /** Returns the value representing the day of the week for the given date
     * Precondition: The date represented by month, day, year is a valid date.
    */
    public static int dayOfWeek(int month, int day, int year) { 
        int lyear, rest, totaldays, firstDay;
    
        // Count years between
        year = (year - 1) - 1899;
    
        // Count leap years
        lyear = year / 4;
    
        // Non leap years
        rest = year - lyear;
    
        // Total number of days in the years lying between the years
        totaldays = (rest * 365) + (lyear * 366) + 1;
    
        // Actual day
        firstDay = (totaldays % 7);
            
        int days[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        
        if (month > 2 && year % 4 == 0) {
        ++day;
        }
 
        // Add the days in the previous months
        while (month-- > 1) {
            day = day + days[month - 1];
        }

        int weekday = ((firstDay + day - 1) % 7);

        return weekday;
        }

    /** Tester method */
    public static void main(String[] args) {
        // Private access modifiers
        System.out.println("firstDayOfYear: " + APCalendar.firstDayOfYear(2021));
        System.out.println("dayOfYear: " + APCalendar.dayOfYear(4, 4, 2022));

        // Public access modifiers
        System.out.println("isLeapYear: " + APCalendar.isLeapYear(1700));
        System.out.println("numberOfLeapYears: " + APCalendar.numberOfLeapYears(2010, 2025));
        System.out.println("dayOfWeek: " + APCalendar.dayOfWeek(11, 15, 2022));
    }

}