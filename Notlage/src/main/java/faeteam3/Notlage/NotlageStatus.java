package faeteam3.Notlage;

public class NotlageStatus 
{
	private boolean bpBenachrichtigt=false;
	private boolean notlageGelöst=false;
	
	public NotlageStatus()
	{
		;
	}
	
	public void setBpBenachrichtigt( )
	{
		bpBenachrichtigt=true;
	}
	
	public void setNotlageGelöst( )
	{
		notlageGelöst=true;
	}
	
	public boolean getBpBenachrichtigt()
	{
		return bpBenachrichtigt;
	}
	
	public boolean getNotlageGelöstt()
	{
		return notlageGelöst;
	}
	
	@Override
	public String  toString()
	{
		return bpBenachrichtigt+" "+ notlageGelöst;
	}

}
