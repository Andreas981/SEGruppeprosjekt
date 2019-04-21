# SEGruppeprosjekt: Gruppe 6

Besvarelse for gruppeprosjektet i ITF20317-1 19V Software Engineering og testing.

## Problemstilling

Ettersom underholdningsbildet i norske byer stadig blir større ønsker de lokale kinoene et system som kan bistå andre lokale arrangører med oppsett og billettsalg for arrangementer. Vi skal utvikle kjernesystemet i denne sfæren og definere det som er påkrevd for at produkteier får det produktet de ønsker.

## Besvarelse

Besvarelsen er i flere deler:
* Prototypen, skrevet i Java
* Dokumentasjon/Produkt beskrivelse
* Grupperapport

## Bruksanvisnings
Installasjon & oppstart
Programmet er terminalbasert, Derfor anbefaler vi å kjøre i fullskjerm og ‘runme.bat’ og ‘TesseraPrototype.jar’ må ligge i samme mappe.

For å starte programmet klikk på ‘runme.bat\’. Dette åpner en kommandolinje instans av prototypen.
Applikasjon
Brukeren navigerer seg rundt med å skrive inn tallet på valget man ønsker å gå inn på. 
F.eks “Type ‘1’ to sign in .
Her taster brukeren inn 1 og trykker enter, for å komme videre i menyen.

Feil brukerinput vil føre til at menyen kommer opp på nytt eller en error beskjed.

For andre innstillinger f.eks input av navn, skriver man inn verdien og så trykker enter.

Siden det ikke er mulig å registrere en ny organizer bruker fra menyen i prototypen så finnes det to “dummy” brukere.

Det er mulig å registrere en ny bruker (Customer) fra hovedmenyen.
Registrerte kontoer
Organizer level 1:
	Brukernavn: oleol
	Passord: abc123t 

Organizer level 2:
	Brukernavn: karino
	Passord: abc123 

Customer:
	Brukernavn: persen
	Passord: abc123


*Mulig scenarioer som kan skape forvirring:
Når man har valgt å kjøpe/verifisere en billett, blir man forespurt om et “Event number”
Hver event som lists har et slikt nummer. Det er enten en firesifret eller dobbel sifret kombinasjon. Hvis en event har (0-0-0-0) som event nummer, tastes det inn som:
0-0-0-0.

## Contributers

* [Andreas Nielsen](https://github.com/Andreni)
* [Andreas Mikalsen](https://github.com/Andreas981)
* [Herman Lippert](https://github.com/Hermanlippert)
* [Gudjon Sveinbjørnsson](https://github.com/Gudjon97)

## License

[License goes here]
