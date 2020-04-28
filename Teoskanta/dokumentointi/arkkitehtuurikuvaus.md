# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne on kolmitasoinen ja pakkausten rakenne on esitetty kuvassa:

<img src="https://github.com/NuiS4ncE/ot-harjoitustyo/blob/master/Teoskanta/dokumentointi/images/ak1.png" width="160">

Teoskanta.ui-pakkaus käyttää JavaFX:ää ja toteuttaa sovelluslogiikkansa teoskanta.domain:in kautta. Teoskanta.domain taas käyttää teoskanta.dao:a tietokannan käsittelyyn. 

## Käyttöliittymä 

Käyttöliittymä sisältää kolme erillistä näkymää

- kirjautuminen
- käyttäjätunnuksen luonti
- teosten listaus

joista jokainen on oma Javan Scene-olionsa. Oliot sijoitetaan sovelluksen Stageen, joka on 
usein ui-luokkien parametreissä. Käyttöliittymä on rakenteellisesti sijoitettu erillisiin luokkiin koodissa.
Käyttöliittymän pääluokkana toimii [teoskanta.ui.TeoskantaUi](https://github.com/NuiS4ncE/ot-harjoitustyo/blob/master/Teoskanta/src/main/java/teoskanta/ui/TeoskantaUi.java). 

Käyttöliittymä kutsuu servicejä, jotka käsittelevät tietokantaluokkia, tarvittaessa.


