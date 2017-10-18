package be.tvh.my_company;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by tim036 on 29/09/2017.
 */
public class Utility {
    private Utility() {
    } //Singleton

    public static boolean isOver18(Date dateOfBirth) {
        return Period.between(Utility.convertDate(dateOfBirth), LocalDate.now()).getYears() >= 18;
    }

    public static Date convertDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate convertDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
