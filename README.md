Behebung einer Notlage

Projekt:
- Maven

Projektstruktur:
- Ein Ordner für je 1 Projekt/Microservice:
	- Notlage
	- FachlicherAdministrator
	- Bezugsperson

Entwicklungsrichtlinien:
- Für jede Subdomäne ein Branch
- Bei Release wird der Branch in den Master commited -> Master enthält alle Releases

Containerisierung:

$ ./mvnw clean install dockerfile:build

$ #docker images

$ #docker run --name bp1 -p 127.0.1.1:80:8080 -t springio/bezugsperson # Im Vordergrund starten

$ #docker rm bp1 #Löschen eines Containers

$ #docker stop $(docker ps -q) #Alles stoppen

$ #docker rm $(docker ps -aq) #Alle Container entfernen

$ for ((i=1; i<4; i++)); do docker run -d -p 127.0.1.$i:80:8080 -t behebungeinernotlage/bezugsperson; done

$ #docker logs $name #Containerlogs anzeigen

$

