# Käyttöohje 

Lataa tiedosto [Teoskanta-1.0-SNAPSHOT.jar](https://github.com/NuiS4ncE/ot-harjoitustyo/releases/download/viikko6/Teoskanta-1.0-SNAPSHOT.jar) .

Ohjelma käynnistyessään luo tai tarkistaa tietokantatiedoston olemassaolon. Tiedosto löytyy nimellä "database.db".

## Käynnistäminen

Ohjelma käynnistetään terminaalikomennolla 
```
java -jar Teoskanta-1.0-SNAPSHOT.jar
```

## Kirjautumisnäkymä 

Kirjautumisikkuna on ensimmäinen ikkuna sovellusta käynnistäessä.

<img src="https://github.com/NuiS4ncE/ot-harjoitustyo/blob/master/Teoskanta/dokumentointi/images/loginscreen.png" width="700">

Syöttämällä olemassa olevan käyttäjätunnuksen ja salasanan ohjelmaan pääsee kirjautumaan sisään painamalla _login_-painiketta. 

## Käyttäjätunnuksen luonti

Kirjautumisikkunasta pääsee käyttäjätunnuksen luontiin painamalla painiketta _create new user_.

<img src="https://github.com/NuiS4ncE/ot-harjoitustyo/blob/master/Teoskanta/dokumentointi/images/createuserscreen.png" width="700">

Tekstikenttiin voi kirjoittaa haluamansa tunnuksen ja salasanan ja luonti onnistuu painamalla _create_. Tämän jälkeen siirrytään takaisin kirjautumissivulle. 
Käyttäjätunnuksen luonnista voi mennä takaisin kirjautumis näkymään painikkeella _back_.

## Päänäkymä 

Kirjautumisesta siirrytään päänäkymään, jossa voi lisätä, poistaa ja filtteröidä teoksia. 

<img src="https://github.com/NuiS4ncE/ot-harjoitustyo/blob/master/Teoskanta/dokumentointi/images/mainscreen.png" width="700">

Päänäkymässä uuden teoksen lisääminen onnistuu kirjoittamalla tiedot tekstikenttiin ja painamalla _create_-painiketta. Tekstikentät ovat järjestyksessä, teoksen nimi, tekijä ja kategoria. 

<img src="https://github.com/NuiS4ncE/ot-harjoitustyo/blob/master/Teoskanta/dokumentointi/images/mainscreencreated.png" width="700">

Kategorioita voi filtteröidä luonnin ja listaamisen yhteydessä vetolaatikoilla.

<img src="https://github.com/NuiS4ncE/ot-harjoitustyo/blob/master/Teoskanta/dokumentointi/images/mainscreentest2.png" width="700">

Teoksia voi poistaa valitsemalla teos listasta ja painamalla _delete_-painiketta.

<img src="https://github.com/NuiS4ncE/ot-harjoitustyo/blob/master/Teoskanta/dokumentointi/images/mainscreenselect.png" width="700">

Painamalla _logout_-painiketta palataan kirjautumisnäkymään. 
