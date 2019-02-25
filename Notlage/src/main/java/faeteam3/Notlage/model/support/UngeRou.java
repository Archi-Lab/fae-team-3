package faeteam3.Notlage.model.support;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
* Helper Klasse zum testen
*/
public class UngeRou 
{
	
	public String id;
	
	public String extraInfo;
	
	public String data2;
	
	public String dvp_id;
	
	public UngeRou()
	{
		
	}
	
	public UngeRou(
			@JsonProperty("id") String id,
			@JsonProperty("extraInfo") String extraInfo,
			@JsonProperty("data2") String data2,
			@JsonProperty("dvp_id") String dvp_id)
	{
		this.id=id;
		this.extraInfo=extraInfo;
		this.data2=data2;
		this.dvp_id=dvp_id;
	}
	
	@Override
	public String toString()
	{
		return id +" "+ extraInfo+" "+data2+" "+dvp_id;
	}

	
}
