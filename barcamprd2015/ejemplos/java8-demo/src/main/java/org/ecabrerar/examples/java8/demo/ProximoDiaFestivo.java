package org.ecabrerar.examples.java8.demo;

import java.time.Month;
import java.time.MonthDay;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.Arrays;
import java.util.List;

/**
 * @author ecabrerar
 *
 */
public class ProximoDiaFestivo implements TemporalAdjuster{
    
    static final List<MonthDay> DIAS_FESTIVOS =  Arrays.asList(
            MonthDay.of(Month.JANUARY, 1), // Año Nuevo
            MonthDay.of(Month.JANUARY, 6), // Día Santos Reyes
            MonthDay.of(Month.JANUARY, 21), // Día de la Altagracia
            MonthDay.of(Month.JANUARY, 26), // Día del Natalicio de Duarte
            MonthDay.of(Month.FEBRUARY, 27), // Día Independecia Nacional
            MonthDay.of(Month.MAY, 1),      //Día Trabajador
            MonthDay.of(Month.AUGUST, 16),  //Día Restauración
            MonthDay.of(Month.SEPTEMBER, 24), //Día de las Mercedes
            MonthDay.of(Month.NOVEMBER, 6), //Día Constitución
            MonthDay.of(Month.DECEMBER, 25) //Día de Navidad

    );

    @Override
    public Temporal adjustInto(Temporal temporal) {
	
	 MonthDay currentMonthDay = MonthDay.from(temporal);
	        int year = temporal.get(ChronoField.YEAR);
	        

	        for (MonthDay element : DIAS_FESTIVOS) {
	            if (currentMonthDay.isBefore(element)) {
	                return element.atYear(year);
	            }
	        }

	       // if it hasn't been returned, then return the first element
	     return DIAS_FESTIVOS.get(0).atYear(year + 1);
    }

}
