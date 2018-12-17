package faeteam3.Bezugsperson.Bezugsperson.controller;

import org.springframework.stereotype.Service;

import faeteam3.Bezugsperson.Bezugsperson.models.support.Notlage;

@Service
public class WorkerService {
	
	
	// alle Topics von Relevanz
//	BPCore.t ungeRou.t ungeVer.t    Notlage.t BPMeta.t
	
	public void analyseNotlage(Notlage n)
	{
		if(!n.isBestaetigt() && !n.isGeloest())
		{
			// // TODO
			// bearbeite neue offene Notlage
			// Sende an Notlage MS  über Rest aufruf Put Funktionen auf für Bestätigen und Lösen
			
			//  und verlinke vom Rest Controler in diese Klasse, um die Funktionalität zu sammeln an einem Ort
		}
		else if(!n.isGeloest())
		{
			;
		}
		else if(!n.isBestaetigt())
		{
			;
		}
	}

}
