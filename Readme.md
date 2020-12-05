# Sweepline Algorithmus

This project is implementing a sweep line algorithms that counts the intersections between line segments

![Sweepline Example](https://he-s3.s3.amazonaws.com/media/uploads/acac1a4.png)

## Getting Started

### Prerequisites

* Java 8
* Maven 3.x.x

### Ausführen der Applikation

```
mvn compile
mvn exec:java -Dexec.mainClass=Main
```

Durch Ändern des `fileName` in Main.java kann die Datei mit Liniensegmenten geändert werden. 

## Laufzeit Ergebnisse
Datei s\_1000\_1.dat Liniensegmenten hat 11 Schnittpunkte:

* Python - Brute Force: 16ms
* Java	 - Brute Force: 13ms
* Java 	 - Line Sweep:  34ms

Datei s\_10000\_1.dat Liniensegmenten hat 732 Schnittpunkte:

* Python - Brute Force: 844ms
* Java	 - Brute Force: 84ms
* Java 	 - Line Sweep:  130ms

Datei s\_100000\_1.dat Liniensegmenten hat 77126 Schnittpunkte:

* Python - Brute Force: 116000ms (116s)
* Java	 - Brute Force: 1256ms
* Java 	 - Line Sweep:  937ms

Datei s\_10000\_10.dat Liniensegmenten hat 796 Schnittpunkte:

* Python - Brute Force: 119ms
* Java	 - Brute Force: 37ms
* Java 	 - Line Sweep:  44ms