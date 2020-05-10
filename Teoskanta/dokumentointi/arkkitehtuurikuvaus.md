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


## Sovelluslogiikka 

Loogisen datamallin sovelluksessa muodostavat käyttäjää ja teoksia kuvaavat luokat Title ja User. 

<img src="https://github.com/NuiS4ncE/ot-harjoitustyo/blob/master/Teoskanta/dokumentointi/images/logicaldatamodel.png" width="160">

Toiminnallisuudesta vastaavat luokat UserService, TitleListService ja TitleService. Luokissa on käyttöliittymän toiminnoille omat metodit servicen mukaisesti liittyen joko teoksiin tai käyttäjätapahtumiin. 
Esimerkkinä: 
 - boolean newUser(String username, String password)
 - boolean createTitle(String name, String author, String year, String category)
 - ObservableList<Title>getObservableTitles(String category)
 - boolean deleteTitle(Title title)

TitleService ja TitleListService pakkauksessa teoskanta.title käyttävät tietokantaan tallennettuja tietoja pakkauksessa teoskanta.title.dao olevan DBTitleDaon kautta. 
Pakkauksessa teoskanta.user oleva UserService taas käyttää tietoja pakkauksessa teoskanta.user.dao olevan DBUserDaon kautta. 
