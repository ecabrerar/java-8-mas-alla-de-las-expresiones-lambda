package org.ecabrerar.examples.java8.demo.streams;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.ecabrerar.examples.java8.demo.Person;
import org.ecabrerar.examples.java8.demo.Team;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author ecabrerar
 * @date Mar 27, 2015
 */
public class ProcesandoDatosConStreams {

    private static List<Team> teams;

    @BeforeClass
    public static void setUp() {
        teams = new ArrayList<>();
        teams.add(new Team("STL", "St. Louis Cardinals", true, 2011));
        teams.add(new Team("NYM", "NY  Mets", true, 1986));
        teams.add(new Team("LAA", "LA  Angels", true, 2002));
        teams.add(new Team("WSN", "Washington Nationals", false, 0));
        teams.add(new Team("LAD", "LA Dodgers", false, 0));
    }

    @Test
    public void empty_stream() {

        Stream<String> emptyStream = Stream.empty();

        long val = emptyStream.count();

        assertTrue(val == 0);
    }

    @Test
    public void stream_from_array() {

        int[] numbers = {1, 2, 3, 4, 5, 6, 7};

        int sum = Arrays.stream(numbers).sum();

        assertEquals(28, sum);
    }

    @Test
    public void stream_to_list() {

        List<String> abc = Stream.of("a", "b", "c")
                .collect(Collectors.toList());

        assertTrue(abc.size() == 3);
    }

    @Test
    public void terminal_operation_foreach() {

        teams.forEach(p -> System.out.println(p));

    }

    @Test
    public void terminal_operation_count() {

        long count = Stream.of("one").count();

        assertEquals(1, count);
    }

    @Test
    public void terminal_operation_anymatch() {

        boolean hasNotWonWorldSeries = teams.stream().anyMatch(p -> !p.isHasWonWoldSeries());

        assertTrue(hasNotWonWorldSeries);
    }

    @Test
    public void probando_con_filter() {

        long totalEquipos = teams.stream().filter(t -> t.isHasWonWoldSeries())
                .mapToInt(Team::getLastTimeWonWorldSeries)
                .count();

        assertEquals(3, totalEquipos);

    }

    @Test
    public void probando_con_filter_min() {

        OptionalInt oldestYear = teams.stream()
                .filter(t -> t.isHasWonWoldSeries())
                .mapToInt(Team::getLastTimeWonWorldSeries)
                .min();

        assertEquals(1986, oldestYear.getAsInt());

    }

    @Test
    public void terminal_operation_allmatch() {

        boolean containsAL = teams.stream().allMatch(
                p -> p.getName().contains("LA"));

        assertFalse(containsAL);
    }

    @Test
    public void terminal_operation_nonematch() {

        boolean noElementEqualTo5 = IntStream.of(1, 2, 3)
                .noneMatch(p -> p == 5);

        assertTrue(noElementEqualTo5);
    }

    @Test
    public void terminal_operation_findfirst() {

        Optional<String> val = Stream.of("one", "two").findFirst();

        assertEquals("one", val.get());
    }

    @Test
    public void terminal_operation_findany() {

        Optional<String> val = Stream.of("one", "two").findAny();

        assertEquals("one", val.get());
    }

    @Test
    public void probar_con_optional() {

        Optional<Team> opt = teams.stream().findFirst();

        assertTrue(opt.isPresent());
    }

    @Test
    public void probar_con_optional_empty() {

        Optional<Team> opt = Optional.empty();

        assertFalse(opt.isPresent());
    }

    @Test
    public void probar_con_optional_else() {

        Optional<Team> opt = Optional.empty();

        assertTrue(opt.orElse(new Team("TOR", "Toronto Blue Jays", true, 1992)).isHasWonWoldSeries());

    }

    @Test
    public void intstream_empty() {

        IntStream emptyStream = IntStream.empty();

        assertEquals(0, emptyStream.count());
    }

    @Test
    public void intstream_generate() {

        OptionalInt one = IntStream.generate(() -> 1).limit(10).distinct()
                .findFirst();

        assertEquals(1, one.getAsInt());
    }

    @Test
    public void intstream_of() {

        OptionalInt max = IntStream.of(5, 10).max();

        assertEquals(10, max.getAsInt());
    }

     @Test
    public void intstream_of_sum() {

        int total = IntStream.of(5, 10,15,25,35).sum();

        assertEquals(90, total);
    }

    @Test
    public void probar_flatmap(){
        List<String>  lista = Arrays.asList("BarcampRD", "BarcampRD Lambdas y API Stream");

        			int total =		lista.stream()
							              .map(s -> s.split(" ")) //  Stream<String[]>
							              .flatMap(Arrays::stream)  // Stream<String>
							              .map(Object::toString)
							              .distinct()
							              .peek(System.out::println)
							              .collect(Collectors.toList()) //  Stream<String> de 5 elementos
							              .size();

            assertEquals(5, total);
    }

    @Test
    public void filter_unique_elements() {

    	List<String>  lista = Arrays.asList("BarcampRD", "BarcampRD Lambdas y API Stream");

		int total =		lista.stream()
				              .flatMap(s ->  Arrays.stream(s.split(" ")))  // Stream<String>
				              .map(Object::toString)
				              .distinct()
				              .peek(System.out::println)
				              .collect(Collectors.toList()) //  Stream<String> de 5 elementos
				              .size();

				              assertEquals(5, total);

    }

    @Test
	public void should_return_the_sum_of_the_value_of_the_given_stream(){

	  Stream<BigDecimal> amounts =  Stream.of(new BigDecimal(100000.00)
			  								  ,new BigDecimal(499000.50)
			  								  , new BigDecimal(999.50));

//	  BigDecimal total =  amounts.map(t -> t).reduce(BigDecimal.ZERO, BigDecimal::add);

	 BigDecimal total =  amounts.map(t -> t).reduce(BigDecimal.ZERO,(t, u) -> t.add(u));

	  Assert.assertTrue(new BigDecimal(600000.00).compareTo(total)==0);

	}

	@Test
	public void should_return_unique_element_of_the_given_list(){
		List<String>  fruits = Arrays.asList("Naranja","Mango","Naranja","Fresa","Melon","Lechoza","Melon","Mango");

	   int listSize = fruits
			           .stream()
			           .distinct()
			           .collect(Collectors.toList())
			           .size();

	   Assert.assertEquals(5, listSize);
	}

	@Test
	public void should_return_unique_person_of_given_list(){
		List<Person>  people = new ArrayList<>();

		people.add(new Person("Doug Lea", 53));
		people.add(new Person("James Gosling", 62));
		people.add(new Person("Josh Bloch", 55));
		people.add(new Person("Doug Lea", 53));
		people.add(new Person("Arun Gupta", 43));
		people.add(new Person("Bruno Souza", 37));
		people.add(new Person("Josh Bloch", 55));


		int uniquePeople =  people.stream()
								  .map(Object::toString)
								  .distinct()
								  .peek(System.out::println)
								  .collect(Collectors.toList())
								  .size();

		 Assert.assertEquals(5, uniquePeople);

	}
}
