import java.util.*;

public class Anarchists {
	
	ArrayList<MyDate> attacks = new ArrayList<MyDate>();
	
	public static void main(String[] args) {
		
		MyDate curr = new MyDate();
		if (willAttack(curr)) 
			attacks.add(curr);
		
	}
	
	public boolean willAttack(MyDate date) {
		return ((date.day * date.month) == date.year);
	}
	
}

public class MyDate {
	
	public int day;
	public int month;
	public int year;
	
	public MyDate() {
		day = 1;
		month = 1;
		year = 1;
	}
	
	public MyDate(int theDay, int theMonth, int theYear) {
		day = theDay;
		month = theMonth;
		year = theYear;
	}
	
	public void addDay() {
		if (this.isLeapYear() && month == 2 && day <= 28) { // leap years
			day++;
		} else if (this.isLeapYear() && month == 2 && day == 29) {
			day = 1;
			month++;
		} else if (month == 2 && day == 28) {
			day = 1;
			month++;
		} else if (month == 12 && day == 31) { // end of year
			day = 1;
			month = 1;
			year++;
		} else if (day == 30 && (month == 9 || month == 4 || month == 5 || month == 11)) { // 30 days hath september
			day = 1;
			month++;
		} else if (day == 31) { // all the rest have 31
			day = 1;
			month++;
		} else { // just a normal day
			day++;
		}
	}
	
	public boolean isLeapYear() {
		return (year % 4 == 0)
	}
	
	public String toString() {
		return String.format("%02d/%02d/%02d", theMonth, theDay, theYear);
	}
	
}