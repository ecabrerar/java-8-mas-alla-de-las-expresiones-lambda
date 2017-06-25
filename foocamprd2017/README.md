### FOOCAMPRD 2017 

#### Kit de supervivencia para Java 8: Como prepararse para Java 9
> Eudris Cabrera


Mi presentación en el [FooCamp RD 2016](http://foocamp.org.do/). 
En esta charla abordaremos en forma de repaso las principales funcionalidades de Java 8 y las nuevas funcionalidades de Java 9.

Ver presentación en [SlideShare](https://www.slideshare.net/eudris/kit-de-supervivencia-para-java-8-como-prepararse-para-java-9) y demos sobre Java 8 en [Github](https://github.com/ecabrerar/java-8-mas-alla-de-las-expresiones-lambda/tree/master/barcamprd2015/ejemplos)

#### Explorando Java 9
Ver demos aquí [Github](https://github.com/ecabrerar/java-8-mas-alla-de-las-expresiones-lambda/tree/master/foocamprd2017/java-9-Jigsaw-ejemplos)

Primero debe descargar [java 9 early access](http://jdk.java.net/9/). Seguir las instrucciones de [instalación aquí ](https://docs.oracle.com/javase/9/install/overview-jdk-9-and-jre-9-installation.htm#JSJIG-GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A)

* Asegurarse de colocar Java 9 en el PATH
* Clonar el repositorio https://github.com/ecabrerar/java-8-mas-alla-de-las-expresiones-lambda
 ```bash 
      cd java-8-mas-alla-de-las-expresiones-lambda/foocamprd2017/java-9-Jigsaw-ejemplos
   ```

Para compilar el ejemplo I : 
* Crear un directorio donde vas a colocar los archivos compilados y ponerle el nombre *mods/com.eudriscabrera.examples.greetings* 
```bash 
    mkdir mods/com.eudriscabrera.examples.greetings
```
 
Usando el comando *javac* para compilar el módulo.
```bash
javac -d mods/com.eudriscabrera.examples.greetings
\com.eudriscabrera.examples.greetings/com/eudriscabrera/examples/greetings/Main.java
com.eudriscabrera.examples.greetings/module-info.java

```

En el comando anterior la opción -d indica la ruta del directorio donde va a colocar los archivos compilados.

Para compilar el ejemplo I : 
* Crear un directorio donde vas a colocar los archivos compilados y ponerle el nombre *mods/com.eudriscabrera.examples.greetings.client*
```bash 
    mkdir mods/com.eudriscabrera.examples.greetings.client
```

Para correr el módulo
```bash
java --module-path mods -m com.eudriscabrera.examples.greetings/com.eudriscabrera.examples.greetings.Main
```

Para compilar el ejemplo II : 
Este módulo depende del módulo anterior, por lo que debemos especificar la ruta donde está compilado el módulo com.eudriscabrera.examples.greetings

```bash
javac --module-path mods -d mods/com.eudriscabrera.examples.greetings.client com.eudriscabrera.examples.greetings.client/module-info.java com.eudriscabrera.examples.greetings.client/com/eudriscabrera/examples/greetings/client/GreetingsClient.java

```

Para correr el módulo
```bash
java --module-path mods -m com.eudriscabrera.examples.greetings.client/com.eudriscabrera.examples.greetings.client.GreetingsClient
```

La opción module-path indica la ruta de los módulos que depende el módulo que está compilando.

Happy Learning !.
