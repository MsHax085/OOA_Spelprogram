package src.lobbyManager;

public abstract class ManagerItem {
	protected String name;
	protected int id;
	
	public ManagerItem(String name, int id){
		this.name = name;
		this.id = id;
	}

	public String getName(){
		return name;
	}
	
	public int getId(){
		return id;
	}
}
