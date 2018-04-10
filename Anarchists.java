import java.util.*;

public class Anarchists { 
	
	static MyDate[] biggestGapDates = new MyDate[2];
	
	public static void main(String[] args) {
		
		ArrayList<MyDate> attacks = new ArrayList<MyDate>();
		MyDate curr = new MyDate();
		
		while (!isEnd(curr)) {
			if (willAttack(curr)) 
				attacks.add(new MyDate(curr));
			curr.addDay();
		}
		
		int[] attacksPerYear = calculateAttacksPerYear(attacks);
		int biggestGap = biggestGap(attacks);
		
		System.out.println(attacks);
		System.out.println();
		System.out.println("num of attacks: " + attacks.size());
		System.out.println();
		System.out.println("attacks per year:");
		printArrayFormatted(attacksPerYear);
		System.out.println();
		System.out.println("year w most attacks: " + String.format("%02d", max(attacksPerYear)));
		System.out.println();
		System.out.print("years w no attacks: ");
		System.out.println(countZeroes(attacksPerYear));
		System.out.println();
		System.out.println("biggest gap (between " + biggestGapDates[0] + " and " + biggestGapDates[1] + "): "
			 + biggestGap + " days");
	}
	
	public static boolean willAttack(MyDate date) {
		return ((date.day * date.month) == date.year);
	}
	
	public static boolean isEnd(MyDate date) {
		if ((date.year == 99) && (date.month == 12) && (date.day == 31))
			return true;
		return false;
	}
	
	public static int[] calculateAttacksPerYear(ArrayList<MyDate> list) {
		int[] years = new int[100];
		MyDate curr = new MyDate();
		for (int i = 0; i < list.size(); i++) {
			curr = list.get(i);
			years[curr.year]++;
		}
		
		return years;
	}
	
	public static void printArrayFormatted(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			System.out.println(String.format("%02d", i) + ": " + arr[i]);
		}
	}
	
	public static int max(int[] arr) {
		int max = arr[0];
		int maxIndex = 0;
		
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
				maxIndex = i;
			}
		}
		
		return maxIndex;
	}
	
	public static ArrayList<Integer> countZeroes(int[] arr) {
		
		ArrayList<Integer> zeroes = new ArrayList<Integer>();
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0)
				zeroes.add(new Integer(i));
		}
		
		return zeroes;
		
	}
	
	public static int biggestGap(ArrayList<MyDate> list) {
		
		MyDate prev;
		MyDate curr;
		int maxGap = 0;
		int currGap;
		
		for(int i = 1; i < list.size(); i++) {
			prev = new MyDate(list.get(i - 1));
			curr = new MyDate(list.get(i));
			currGap = 0;
			while (!prev.equals(curr)) {
				prev.addDay();
				currGap++;
			}
			if (currGap > maxGap) {
				maxGap = currGap;
				biggestGapDates[0] = list.get(i - 1);
				biggestGapDates[1] = list.get(i);
			}

		}
		
		return maxGap;
		
	}
	
}

class MyDate {
	
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
	
	public MyDate(MyDate theDate) {
		day = theDate.day;
		month = theDate.month;
		year = theDate.year;
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
		return (year % 4 == 0);
	}
	
	public boolean equals(MyDate rhs) {
		if (this.year != rhs.year) {
			return false;
		} else if (this.month != rhs.month) {
			return false;
		} else if (this.day != rhs.day) {
			return false;
		}
		
		return true;
		
	}
	
	public String toString() {
		return String.format("%02d/%02d/%02d", month, day, year);
	}
	
}