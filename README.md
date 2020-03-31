# Ohjelmointitekniikka, harjoitustyö

University of Helsinki department of Computer Science exercise on the course Ohjelmointitekniikka. 
<br></br>
[vaatimusmaarittely.md](https://github.com/NuiS4ncE/ot-harjoitustyo/blob/master/Teoskanta/dokumentointi/vaatimusmaarittely.md) <br> </br>
[tuntikirjanpito.md](https://github.com/NuiS4ncE/ot-harjoitustyo/blob/master/Teoskanta/dokumentointi/tuntikirjanpito.md)

## Teoskanta

Sovelluksen avulla käyttäjä voi hallinnoida ja listata haluamiaan teoksia oli se sitten elokuvia, musiikkia tai kirjoja. Sovellusta voi käyttää eri käyttäjät, joilla jokaisella on omat teoksensa. 

### Suoritus

Ohjelma suoritetaan komennoilla
```
cd Teoskanta
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

