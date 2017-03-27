/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ecabrerar.examples.java8.demo.datetime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;

import org.ecabrerar.examples.java8.demo.DiasFestivosRD;
import org.ecabrerar.examples.java8.demo.TemporadaCiclonica;
import org.junit.Test;

/**
 *
 * @author ecabrerar
 */
public class TrabajarConDateTime {


    @Test
    public void calcular_diferencia_entre_dos_fecha_java8_usando_period() {

	    LocalDate sinceBarCamp2013 = LocalDate.of(2013, Month.NOVEMBER, 16);
	    LocalDate currentDate = LocalDate.of(2015, Month.NOVEMBER, 28);

	    Period betweenDates = Period.between(sinceBarCamp2013, currentDate);

	    int diffInDays = betweenDates.getDays();
	    int diffInMonths = betweenDates.getMonths();
	    int diffInYears = betweenDates.getYears();

            System.out.println(String.format("%d %d %d", diffInDays,diffInMonths,diffInYears));

	    assertTrue(diffInDays == 12);
	    assertTrue(diffInMonths >= 0);
	    assertTrue(diffInYears == 2);
	}

    @Test
    public void es_festivo_el_26_enero_2016() {

	    LocalDate date = LocalDate.of(2016, Month.JANUARY, 26);

	    Boolean isHoliday = date.query(new DiasFestivosRD());

	    assertTrue(isHoliday);
	}


    @Test
    public void obtener_cantidad_dias_que_tienes_un_mes() {

	    LocalDate date = LocalDate.of(2015, Month.NOVEMBER, 01);

	    int length = date.getMonth().length(true);

	    assertEquals(30, length);

   }

   @Test
   public void cantidad_dias_que_tienes_un_mes_usando_lengthOfMonth() {

	    LocalDate date = LocalDate.of(2015, Month.MARCH, 01);

	    int length2 = date.lengthOfMonth();

	    assertEquals(31, length2);
   }

   @Test
   public void obtener_zonahoraria(){
       ZoneId zone = ZoneId.systemDefault();

       System.out.println(" "+zone);
       //ZoneId zoneAmerica = ZoneId.of("America/Santo_Domingo");
       ZoneId zoneBerlin = ZoneId.of("Europe/Berlin");

       assertNotEquals(zone, zoneBerlin);

   }

   @Test
   public void es_antes_de_la_temporada_ciclonica () {

       LocalDate date = LocalDate.of(2017, Month.JANUARY, 02);

       Boolean esTemporadaCiclonica = date.query(new TemporadaCiclonica());

       assertFalse(esTemporadaCiclonica);
   }

   @Test
   public void es_durante_la_temporada_ciclonica () {

       LocalDate date = LocalDate.of(2017, Month.JUNE, 30);

       Boolean esTemporadaCiclonica = date.query(new TemporadaCiclonica());

       assertTrue(esTemporadaCiclonica);
   }

   @Test
   public void es_despues_de_la_temporada_ciclonica () {

       LocalDate date = LocalDate.of(2017, Month.DECEMBER, 30);

       Boolean esTemporadaCiclonica = date.query(new TemporadaCiclonica());

       assertFalse(esTemporadaCiclonica);
   }
}
