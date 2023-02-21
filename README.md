# WorldBeers

Prima di guardare il progetto un paio di note:
* Nel testo dite "dovrà mostrare una barra di ricerca e la lista completa delle birre". A giudicare dalla documentazione, l'api per ottenere la birra è paginata, con dimensione massima di 80. 
Stavo per implementare paging, ma poi scrivete "La barra di ricerca dovrà filtrare le birre in base a nome e descrizione". L'api non supporta ricerca per descrizione, ed essendo che avete parlato
di filtro, ho pensato che paging non avesse senso, quindi non l'ho implementato, la lista iniziale contiene direttamente 80 elementi. Comunque conosco paging e posso mostrarvi un altro paio di progetti
nei quali l'ho usato in compose (e dovrei avere anche qualcosa in xml).
* com.github.simonecascino:destinationbuilder è un annotation processor che ho sviluppato io e pubblicato su jitpack. È un poc, avevo idea di continuare lo sviluppo,
ma non sono mai riuscito a trovare il tempo per farlo. Comunque per progetti medio/piccoli funziona benissimo. 
* Ho usato coil al posto di glide, è quella maggiormente raccomandata per compose.
* La suddivisione in moduli e l'uso della clean architecture è un po' un overkill per un progetto così piccolo. Ad ogni modo avrei suddiviso ulteriormente, specie
il modulo core, dove ho un po' di tutto. Ho considerato lista/dettaglio come un'unica feature dell'app.
* Normalmente lavoro un po' anche su gradle, per ridurre la roba che mi porto appresso nei vari moduli, ma non ci sono arrivato, così come per i test.
