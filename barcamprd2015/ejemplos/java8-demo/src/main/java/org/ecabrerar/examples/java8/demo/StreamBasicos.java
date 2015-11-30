/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ecabrerar.examples.java8.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author ecabrerar
 */
public class StreamBasicos {
    
    
    private void ejecutar(){
        System.out.println("Stream Básicos");
        
        System.out.println("Ejecutando ejercicio 1");
        ejercicio1();
        
        System.out.println("Ejecutando ejercicio 2");
        ejercicio2();       
       
    }
    
    /**
     * Procesar la siguiente lista para que solamente contenga cadena de caracteres impares
     */
    private void ejercicio1() {
        
        List<String> list = Arrays.asList(
        "BarCamp", "MongoDB", "10Gen", "TokuMX", "Nagios", "PUCMM", "Ruby", "JSON", "JSON");
    
    
      List<String> newList = list
            .stream()
            .filter(new Predicate<String>() {

            @Override
            public boolean test(String t) {
               return (t.length() % 2)==1;
            }
        }).map(String::toLowerCase)
          .collect(Collectors.toList());
          
        /*
     List<String> newList1 = list
            .stream()
            .filter(s -> (s.length() % 2)==1)
            .map(String::toLowerCase)
            .collect(Collectors.toList());*/
                   
    
        newList.forEach(System.out::println);
    }

       /**
      * Convertir par de datos del map en un stream
      * 
     */    
    private void ejercicio2(){
        Map<String, Integer> map = new TreeMap<>();
        map.put("c", 3);
        map.put("b", 2);
        map.put("a", 1);
        

        StringBuilder sb = new StringBuilder();
        map.forEach((String k, Integer v) -> {
            sb.append(String.format("%s%s", k,v));
        });
        
        System.out.println(sb.toString());
    }
    
    public static void main(String[] args) {
        StreamBasicos stream = new StreamBasicos();
        stream.ejecutar();
    }

    
}
