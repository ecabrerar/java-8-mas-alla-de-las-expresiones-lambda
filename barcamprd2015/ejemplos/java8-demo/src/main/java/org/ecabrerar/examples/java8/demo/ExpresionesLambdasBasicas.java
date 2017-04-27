/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ecabrerar.examples.java8.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author ecabrerar
 */
public class ExpresionesLambdasBasicas {
    
    private void ejecutar(){
        System.out.println("Expresiones Lambdas Básicas");
        
        System.out.println("Ejecutando ejercicio 1");
        ejercicio1();
        
        System.out.println("Ejecutando ejercicio 2");
        ejercicio2();
        
        System.out.println("Ejecutando ejercicio 3");
        ejercicio3();
        
        System.out.println("Ejecutando ejercicio 4");
        ejercicio4();
        
        System.out.println("Ejecutando ejercicio 5");
        ejercicio5();
        
        System.out.println("Ejecutando ejercicio 6");
        ejercicio6();        
        
    }
    
 
    private void ejercicio1() {
        printTeams(new ArrayList<>(), new CheckTeam() {

             @Override
             public boolean test(Team t) {
                  return t.isHasWonWoldSeries()
                    && t.getLastTimeWonWorldSeries() >= 1990
                    && t.getLastTimeWonWorldSeries() <= 2015;
             }
        });   
      
    }

    private void ejercicio2() {
       printTeamWhereLastTimeWonWorldSeriesOtherThan(new ArrayList<>(), 1986);
    }
    
    
     private void ejercicio3() {
       printTeamWithinYearRange(new ArrayList<>(), 1985, 2000);
    }
     
    private void ejercicio4() {
         printTeamsWithPredicate(new ArrayList<>(),                
                t -> t.isHasWonWoldSeries()
                && t.getLastTimeWonWorldSeries() >= 1990
                && t.getLastTimeWonWorldSeries() <= 2015
        );
         
         printTeamsWithPredicate(new ArrayList<>(), (Team t) -> t.isHasWonWoldSeries()
                 && t.getLastTimeWonWorldSeries() >= 1990
                 && t.getLastTimeWonWorldSeries() <= 2015
               
             
         );
    }
    
     private void ejercicio5() {
        processTeamsWithFunction(new ArrayList<>(),
                 t -> t.isHasWonWoldSeries()
                && t.getLastTimeWonWorldSeries() >= 1990
                && t.getLastTimeWonWorldSeries() <= 2015,
                t -> t.getTeamId(),
                name -> System.out.println(name)
     );
    }
     
    private void ejercicio6(){
         
       List<Team> teams = new ArrayList<>();
       
       teams.stream()
               .filter(t -> t.isHasWonWoldSeries() 
                       && t.getLastTimeWonWorldSeries() >= 1990 
                       && t.getLastTimeWonWorldSeries() <= 2015
               )
               .forEach((Team t) -> {
                   System.out.println(" "+t.getName());
       });
       
        
    }
    
    
    public static void printTeamsHasNotWonWorldSeries(List<Team> teams){
        for (Team team : teams) {            
             if(!team.isHasWonWoldSeries()){
               System.out.println(""+ team.toString());
            }            
        }

    }
    
    public static void printTeamWhereLastTimeWonWorldSeriesOtherThan(List<Team> teams, int year) {

        for (Team t : teams) {
            if (t.getLastTimeWonWorldSeries() >= year) {
                System.out.println(""+ t.toString());
            }

        }
    }
    

    public static void printTeamWithinYearRange(List<Team> teams, int min, int year) {
        for (Team t : teams) {
            if (min <= t.getLastTimeWonWorldSeries() && t.getLastTimeWonWorldSeries() < year) {
               System.out.println(""+ t.toString());
            }
        }
    }

    public static void printTeams(List<Team> teams, CheckTeam tester) {

        for (Team t : teams) {
            if(tester.test(t)){
                System.out.println(""+ t.toString());
            }
        }
    }
    
    public static void printTeamsWithPredicate(List<Team> teams, Predicate<Team> tester){
        
        for (Team t : teams) {            
             if(tester.test(t)){
               System.out.println(""+ t.toString());
            }            
        }
    }
    
    public static void processTeams(List<Team> teams, Predicate<Team> tester, Consumer<Team> block){
        for (Team t : teams) {            
             if(tester.test(t)){
                block.accept(t);
            }            
        }
    }
    
    public static void processTeamsWithFunction(List<Team> teams, Predicate<Team> tester,Function<Team, String> mapper, Consumer<String> block){
         for (Team t : teams) {            
             if(tester.test(t)){
                String data = mapper.apply(t);
                block.accept(data);
            }            
        }
    }
    
    public static <X, Y> void processElements(Iterable<X> source,
                                                Predicate<X> tester,
                                                Function<X, Y> mapper,
                                                Consumer<Y> block){
        
        for (X p : source) {
             if(tester.test(p)){
                Y data = mapper.apply(p);
                block.accept(data);
            }  
        }
        
    }
    
 
    
    public static void main(String[] args) {
        
        ExpresionesLambdasBasicas exp = new ExpresionesLambdasBasicas();
        exp.ejecutar();
        
    }

}
