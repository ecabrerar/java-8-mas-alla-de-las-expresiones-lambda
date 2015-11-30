package org.ecabrerar.examples.java8.demo.collections;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


import org.junit.Test;

import com.google.common.collect.Lists;
import org.ecabrerar.examples.java8.demo.Team;
import org.junit.BeforeClass;

/**
 * @author ecabrerar
 * @date Mar 27, 2015
 */
public class TrabajandoConCollectionsJava8 {

    private static List<String> teamsWithNull;
    private static List<Team> teams;
    private static List<String> teamListString;

    @BeforeClass
    public static void setUp(){
      teamsWithNull = Lists.newArrayList(null, "NY  Mets", null,
                                "Washington Nationals", 
                                "LA  Angels", null);
      
       teams = new ArrayList<>();
        teams.add(new Team("STL", "St. Louis Cardinals", true, 2011));
        teams.add(new Team("NYM", "NY  Mets", true, 1986));
        teams.add(new Team("LAA", "LA  Angels", true, 2002));
        teams.add(new Team("WSN", "Washington Nationals", false, 0));
        teams.add(new Team("LAD", "LA Dodgers", false, 0));
       
       teamListString = new ArrayList<>();
       teamListString.add("St. Louis Cardinals");
       teamListString.add("NY  Mets");
       teamListString.add("LA  Angels");
       teamListString.add("Washington Nationals");
    }
    
    
    @Test
    public void convert_list_to_map_with_java() {

        Map<String, Team> mappedTeams = new HashMap<>();

        for (Team team : teams) {
            mappedTeams.put(team.getTeamId(), team);
        }

       
        assertTrue(mappedTeams.size() == 4);
        assertEquals("NY  Mets", mappedTeams.get(2).getName());
    }

  

    @Test
    public void convert_list_to_map_with_java8_lambda() {

        Map<String, Team> mappedTeams = teams.stream().collect(Collectors.toMap(Team::getTeamId, (p) -> p));

        assertTrue(mappedTeams.size() == 4);
        assertEquals("NY  Mets", mappedTeams.get(2).getName());

    }

    @Test
    public void filter_items_in_list_with_java() {

        Collection<Team> worldSeriesWinners = new ArrayList<>();

        for (Team team : teams) {

            if (team.isHasWonWoldSeries()) {
                worldSeriesWinners.add(team);
            }
        }

        assertTrue(worldSeriesWinners.size() == 3);

    }

   
    @Test
    public void filter_items_in_list_with_java8_lambda() {

        Collection<Team> worldSeriesWinners = teams
                .stream()
                .filter(p -> p.isHasWonWoldSeries())
                .collect(Collectors.toList());


        assertTrue(worldSeriesWinners.size() == 3);

    }

    @Test
    public void remove_null_from_list_java() {

        teamsWithNull.removeAll(Collections.singleton(null));

        assertEquals(4, teams.size());
    }

    @Test
    public void remove_null_from_list_java8_lambda() {

        List<String> filterStrings = teamsWithNull
                .stream()
                .filter(p -> p != null)
                .collect(Collectors.toList());

        assertEquals(3, filterStrings.size());        
    }
    
     @Test
    public void remove_null_from_list_java8_lambda_method_refence() {
      
        List<String> filterStrings2 = teamsWithNull
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        assertEquals(3, filterStrings2.size());
    }


    @Test
    public void get_first_element_in_list_with_java() {

        String firstTeam = null;

        if (!teamListString.isEmpty() && teamListString.size() > 0) {
            firstTeam = teamListString.get(0);
        }

        assertEquals("St. Louis Cardinals", firstTeam);
    }

    @Test
    public void get_first_element_in_list_with_java8() {

        Optional<String> firstTeam = teamListString.stream().findFirst();

        assertEquals("St. Louis Cardinals", firstTeam.get());
    }
  
    @Test
    public void find_elements_in_list_with_java() {

        List<Integer> numbers = Lists.newArrayList(1, 2, 3);

        Integer value = null;

        for (Integer number : numbers) {

            if (number == 3) {
                value = number;
            }
        }

        assertEquals(new Integer(3), value);
    }

}