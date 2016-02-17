package src.lobbyManager;

import java.util.ArrayList;

/**
 * 
 * @author ludwigfriborg
 *
 */
public class Manager {
	private ArrayList<Lobby> list;
	public Manager() {
		list = new ArrayList<Lobby>();
	}
	
	/*
	 * Creates a new lobby
	 */
	public Lobby addNewLobby(String name, int maxClients, int id){
		Lobby lobby = new Lobby(name, maxClients, id); 
		list.add(lobby);
		System.out.println("<Manager> "+name+" has been created.");
		return lobby;
	}
	
	/*
	 * Returns the Lobby if it exists
	 */
	public Lobby getLobbyById(int id){
		for(Lobby l : list){
			if(l.getId() == id){
				return l;
			}
		}
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String text = "";
		for(Lobby l : list){
			if(list.size()-1 == list.indexOf(l)){
				text = text + l.getName();
			}else{
				text = text + l.getName()+ ", ";
			}
		}
		return text;
	}

	public ArrayList<Lobby> getList(){
		return list;
	}
}
