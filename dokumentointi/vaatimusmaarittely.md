# Vaatimusmäärittely

## Sovelluksen tarkoitus 
Sovelluksen avulla käyttäjä voi hallinnoida ja listata haluamiaan teoksia oli se sitten elokuvia, musiikkia tai kirjoja. Sovellusta voi käyttää eri käyttäjät, joilla jokaisella on omat teoksensa. 

## Käyttäjät
Aluksi käyttäjiä on vain tavallinen käyttäjä. Käyttäjiä on tarkoitus olla lopussa rooliltaan tavallinen käyttäjä ja pääkäyttäjä. Tavallinen käyttäjä käyttää ohjelmaa sen tavallisen toiminnan mukaisesti. Pääkäyttäjä voi hallinnoida eri käyttäjiä.

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista
- käyttäjä voi luoda järjestelmään käyttäjätunnukset 
	- käyttäjätunnukselta vaaditaan, että se on uniikki ja pituudeltaan vähintään 3 merkkiä
- käyttäjä voi kirjautua järjestelmään
	- kirjautuminen onnistuu olemassaolevalla käyttäjätunnuksella kirjautumislomakkeelle
	- jos käyttäjää ei ole olemassa, salasana tai käyttäjätunnus on väärin, ilmoittaa ohjelma tästä virheilmoituksella

### Kirjautumisen jälkeen 
- käyttäjä näkee teoskategoriat
- käyttäjä voi luoda uuden kategorian 
	- uusi kategoria näkyy vain käyttäjälle
- käyttäjä voi navikoida alakategoriaan, jossa näkee teosten listauksen
- käyttäjä voi lisätä teoksia alakategorian mukaan
- käyttäjä voi kirjautua ulos järjestelmästä

## Jatkokehitysideoita
Seuraavat toiminallisuudet toivottavasti täydentävät ajan kuluessa ohjelmaa
- pääkäyttäjän mahdollisuus poistaa ja lisätä käyttäjiä
- pääkäyttäjä näkee muut käyttäjät
- pääkäyttäjä voi lisätä eri kenttiä teoksille
- alakategoriassa teoksia voi filtteröidä eri tavoin
