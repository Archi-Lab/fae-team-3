package faeteam3.Bezugsperson.Bezugsperson.models.support;


public class BPCore 
{
	private Long id;
	
	private String name;
	
	private Long dvp_id;
	
	public BPCore()
	{
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDvp_id() {
		return dvp_id;
	}

	public void setDvp_id(Long dvp_id) {
		this.dvp_id = dvp_id;
	}

}
