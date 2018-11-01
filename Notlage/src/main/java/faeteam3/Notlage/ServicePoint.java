package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public interface ServicePoint {
	
	
	public void neue_Person(DVP np);

	public void neue_Notlage(Notlage no);
	

	public void notlage_behoben(DVP np);

}
