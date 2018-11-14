package faeteam3.Notlage;

public class ReturnBP 
{
	private Long uuid;
	
	protected ReturnBP()
	{
		;
	}
	
	public ReturnBP(Long uuid)
	{
		this.setUuid(uuid);
	}

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

}
