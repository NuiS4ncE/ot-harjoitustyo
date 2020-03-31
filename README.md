# Ohjelmointitekniikka, harjoitustyö

University of Helsinki department of Computer Science exercise on the course Ohjelmointitekniikka. 
<br></br>
[vaatimusmaarittely.md](https://github.com/NuiS4ncE/ot-harjoitustyo/blob/master/Teoskanta/dokumentointi/vaatimusmaarittely.md) <br> </br>
[tuntikirjanpito.md](https://github.com/NuiS4ncE/ot-harjoitustyo/blob/master/Teoskanta/dokumentointi/tuntikirjanpito.md)

## Teoskanta

Teoskanta is a Java program that can be used to list and sort different artistic titles i.e books, movies etc. 

### Suoritus

Ohjelma suoritetaan komennolla
```
mvn compile exec:java -Dexec.mainClass=teoskanta.Main
```

### Testaus

Testit suoritetaan komennolla 

```
mvn test
```

Testikattavuusraportti luodaan komennolla 
```
mvn jacoco:report
```

Kattavuusraporttia tarkastellaan selaimella avaamalla tiedosto _target/site/jacoco/index.html_

