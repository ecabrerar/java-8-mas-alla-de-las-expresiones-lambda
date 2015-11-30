package org.ecabrerar.examples.java8.demo.lambdas;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;
import org.junit.Test;

import static java.util.Comparator.comparing;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * @author ecabrerar
 * @date Mar 27, 2015
 */
public class ExpresionesLambdasFundamentos {

    List<Person> personList;
    
    @Before
    public void setUp() {
       personList  = new ArrayList<>();
        
       personList.add(new Person("Wilson",35));
       personList.add(new Person("Peter",25));   
       personList.add(new Person("Jones",27));  

    }

    @Test
    public void order_list_object_with_java() {
        //Sorting a list of objects

        Collections.sort(personList, new ComparePersonsByName());    
              
        assertTrue(personList.stream().findFirst().get().getName().equals("Jones"));
    }
    
    @Test
    public void order_list_object_with_java8_lambda() {
        //Sorting a list of objects
   
        Collections.sort(personList, (Person p1, Person p2)
                -> p1.getName().compareTo(p2.getName())
        );

        assertTrue(personList.stream().findFirst().get().getName().equals("Jones"));

    }

    @Test
    public void order_list_object_with_java8_lambda_list_sort() {
        //Sorting a list of objects

        personList.sort((Person p1, Person p2)
                -> p1.getName().compareTo(p2.getName())
        );

        assertTrue(personList.stream().findFirst().get().getName().equals("Jones"));
        
    }

    @Test
    public void working_with_default_methods() {
 
        personList.sort(Comparator.comparing((Person p) -> p.getName()));

        //personList.sort(Comparator.comparing(p -> p.getName()));
        
        assertTrue(personList.stream().findFirst().get().getName().equals("Jones"));
    }
    
    @Test
    public void working_with_methods_references() {
        List<String> list = Arrays.asList("Alfa", "Bravo", "Charlie", "Delta", "Echo", "Golf");

        // list.replaceAll(s -> s.toUpperCase());
        list.replaceAll(String::toUpperCase);

        assertTrue(list.contains("DELTA"));

    }
    
     @Test
    public void working_with_java8_methods_references() {       

         //personList.sort(Comparator.comparing((Person p) -> p.getName()));

        //personList.sort(Comparator.comparing(p -> p.getName()));
        //personList.sort(Comparator.comparing(Person::getName));

        personList.sort(comparing(Person::getName));
        
        assertTrue(personList.stream().findFirst().get().getName().equals("Jones"));
    }
    
    @Test
    public void two_level_comparing_object_with_java(){
          
        personList.sort(new Comparator<Person>() {

            @Override
            public int compare(Person p1, Person p2) {
                int r = ((Integer) p1.getAge()).compareTo(p2.getAge());

                if (r != 0) {
                    return r;
                }
                return p1.getName().compareTo(p2.getName());
            }
        });
        
         assertTrue(personList.stream().findFirst().get().getName().equals("Peter"));
    }
   
    
    @Test
    public void two_level_comparing_object_with_java8() {
        //Sorting a list of objects

       //personList.sort(Comparator.comparing(Person::getAge).thenComparing(Person::getName));
        personList.sort(comparing(Person::getAge).thenComparing(Person::getName));
        
        assertTrue(personList.stream().findFirst().get().getName().equals("Peter"));
        
    }
    

    @Test
    public void remove_object_from_list_with_java() {
      
        for (Iterator<Person> it = personList.listIterator(); it.hasNext();) {

            Person person = it.next();

            if ("Jones".equals(person.getName())) {
                it.remove();
            }
        }

        assertFalse(personList.contains(new Person("Jones",25)));
    }
    
    @Test
    public void remove_object_from_list_with_java8_functional_interface_predicate() {
      
        personList.removeIf(new Predicate<Person>() {

            @Override
            public boolean test(Person person) {
                return "Jones".equals(person.getName());
            }
        });

       assertFalse(personList.contains(new Person("Jones",25)));
    }
    
    @Test
    public void remove_object_from_list_with_java8_functional_interface() {
      
        personList.removeIf((Person person) -> "Jones".equals(person.getName()));

       //personList.removeIf(person -> "Jones".equals(person.getName()));

         assertFalse(personList.contains(new Person("Jones",25)));
    }
    
    @Test
    public void replace_object_from_list_with_java() {
         List<String> list = Arrays.asList("Alfa", "Bravo", "Charlie", "Delta", "Echo", "Golf");
         List<String> listUC=Lists.newArrayList();
         
           for (ListIterator<String> it = list.listIterator(); it.hasNext();) {

              listUC.add(it.next().toUpperCase());
           }
           
          assertTrue(listUC.contains("CHARLIE"));         
    }

    @Test
    public void replace_object_from_list_with_java8_functional_interface() {
        List<String> list = Arrays.asList("Alfa", "Bravo", "Charlie", "Delta", "Echo", "Golf");
      
        list.replaceAll(String::toUpperCase);
        
       assertTrue(list.stream().anyMatch(s->s.equals("DELTA")));

    }

    class Person {

        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    class ComparePersonsByName implements Comparator<Person> {

        @Override
        public int compare(Person p1, Person p2) {
            return p1.getName().compareTo(p2.getName());
        }
    }

}
