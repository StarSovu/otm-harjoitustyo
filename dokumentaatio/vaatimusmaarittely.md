# Vaatimusmäärittely

## Sovelluksen tarkoitus
Sovellus on käärmepeli (matopeli), jossa voi pelata joko yksin enemmän perinteisen matopelin kaltaista peliä tai kaksin vastakkain.

## Käyttäjät
Vain tavallisia käyttäjiä (yksi tai kaksi kerrallaan riippuen siitä pelaako yksin vai kaksin).Yksinpelissä käyttäjä kirjoittaa nimen, joka näkyy top-listoilla.

## Perusversion tarjoama toiminnallisuus

Sovelluksen käynnistyessä näkyy valikko.
- Valikossa on nappeja, joita painamalla voi valita joko yksin- tai kaksinpelin.
- Pelin päätyttyä palataan valikkoon.

Yksipeli
- Ohjataan käärmettä.
- Käärme kasvaa, kun se syö appelsiinin. Samalla saadaan yksi piste.
- Jos käärme osuu itseensä tai seinään, peli on ohi.
- Käärme ei voi kääntyä vastakkaiseen suuntaan siitä, mihin se on sillä hetkellä menossa.
- Joka kymmenes pisteen tienaus aihettaa sitruunan ilmestymisen.
- Syömällä sitruunan tienataan kaksi pistettä ilman, että käärme kasvaa.
- Tavoitteena saada mahdollisimman monta pistettä.
- Kun peli käynnistetään, kysytään käyttäjänimeä.
- Top-listoilla näkyy kymmenen parasta suoritusta (nimi ja pisteet).

Kaksinpeli
- Pelaaja 1 ja pelaaja 2 ohjaa kumpikin käärmettä.
- Käärmeen kasvaminen ja ohjaaminen toimivat samalla tavalla kuin yksinpelissä.
- Sitruunoita ei ole eikä pisteitä lasketa.
- Jos käärme osuu itseensä, seinään tai liikkuu kohti vastustajan käärmettä, häviää ja peli loppuu.
- Jos molemmat pelaajat häviävät samanaikaisesti, peli loppuu tasan.

## Jatkokehitysideoita
