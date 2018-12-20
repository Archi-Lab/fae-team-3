package faeteam3.Notlage.model;

import javax.persistence.Embeddable;

@Embeddable
public class ExtraData {
	
	private String data="";
	
	
	public ExtraData()
	{
		
	}
	
	
	public ExtraData(String data)
	{
		this.setData(data);
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String toString()
	{
		return data;
	}

}
