package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.naming.NameNotFoundException;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    @Override
    public Comparator<String> sortByDays() {
        return null;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return null;
    }

    public enum Month{
       GENNAIO("gennaio", 31), 
        FEBBRAIO("febbraio", 28), 
        MARZO("marzo", 31), 
        APRILE("aprile", 30), 
        MAGGIO("maggio", 31), 
        GIUGNO("giugno", 30), 
        LUGLIO("luglio", 31), 
        AGOSTO("agosto", 31), 
        SETTEMBRE("settembre", 30),
        OTTOBRE("ottobre", 31), 
        NOVEMBRE("novembre", 30), 
        DICEMBRE("dicembre", 31);

        private final int ndays;
        private final String name;

        private Month(String name, int ndays){
            this.name = name;
            this.ndays = ndays;
        }

        private String getName(){
            return this.name;
        }

        static private Month fromString(String givenString){
            for(Month month : Objects.requireNonNull(Month.values())){
                if(month.getName().equalsIgnoreCase(givenString)){
                   return month; 
                }

                if(month.getName().contains(givenString.toLowerCase())){
                    return month;
                }
            }
            throw new NoSuchElementException();
        }
    }

    static class SortByMonthOrder implements Comparator<String>(){
        public int compare(String s1, String s2){
            Month m1 = Month.fromString(s1);
            Month m2 = Month.fromString(s2);

            return Integer.compare(m1.getName(), )
            
        }

    }
}
