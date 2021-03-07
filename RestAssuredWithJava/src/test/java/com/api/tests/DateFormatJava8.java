import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatJava8 {

	public static void main(String[] args) {

		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

		LocalDate localDate = LocalDate.now();

		DayOfWeek dayOfWeek = localDate.getDayOfWeek();
		String dateString = "";

		switch (dayOfWeek.toString()) {
		case "SATURDAY":
			System.out.println("dayOfWeek -  from Saturday " + localDate.getDayOfWeek().toString());
			dateString = FOMATTER.format(localDate.minusDays(1));
			System.out.println("dayOfWeek -  from Saturday: Date Format " + dateString);
			
			break;
		case "SUNDAY":
			System.out.println("dayOfWeek -  from Sunday " + localDate.getDayOfWeek().toString());
			dateString = FOMATTER.format(localDate.minusDays(2));
			System.out.println("dayOfWeek -  from Sunday: Date Format " + dateString);
			LocalDateTime localDateTime = LocalDateTime.now();
			FOMATTER = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mma");
			System.out.println("Date Format dd-MMM-yyyy hh:mm a " + FOMATTER.format(localDateTime));
			break;
		case "MONDAY":
			System.out.println("dayOfWeek -  from Monday " + localDate.getDayOfWeek().toString());
			dateString = FOMATTER.format(localDate.minusDays(3));
			System.out.println("dayOfWeek -  from Monday: Date Format " + dateString);
			break;
		default:
			System.out.println("dayOfWeek -  from Sunday " + localDate.getDayOfWeek().toString());
			dateString = FOMATTER.format(localDate.minusDays(1));
			System.out.println("dayOfWeek -  from Saturday: Date Format " + dateString);
			break;

		}
		// 03/08/2021

	}

}
