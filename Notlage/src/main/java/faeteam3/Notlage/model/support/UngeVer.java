package faeteam3.Notlage.model.support;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UngeVer 
{
	
	public Long id;
	
	public String extraInfo;
	
	public String data2;
	
	public Long dvp_id;
	
	public UngeVer()
	{
		
	}
	
	public UngeVer(
			@JsonProperty("id") Long id,
			@JsonProperty("extraInfo") String extraInfo,
			@JsonProperty("data2") String data2,
			@JsonProperty("dvp_id") Long dvp_id)
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
