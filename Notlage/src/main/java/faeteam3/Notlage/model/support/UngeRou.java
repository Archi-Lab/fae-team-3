package faeteam3.Notlage.model.support;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UngeRou 
{
	
	public Long id;
	
	public String data1;
	
	public String data2;
	
	public Long dvp_id;
	
	public UngeRou()
	{
		
	}
	
	public UngeRou(
			@JsonProperty("id") Long id,
			@JsonProperty("data1") String data1,
			@JsonProperty("data2") String data2,
			@JsonProperty("dvp_id") Long dvp_id)
	{
		this.id=id;
		this.data1=data1;
		this.data2=data2;
		this.dvp_id=dvp_id;
	}
	
	@Override
	public String toString()
	{
		return id +" "+ data1+" "+data2+" "+dvp_id;
	}

	
}
