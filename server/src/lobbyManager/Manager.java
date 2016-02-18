package src.lobbyManager;

import java.net.InetAddress;
import java.util.ArrayList;

/**
 * 
 * @author ludwigfriborg
 * 
 * methods:
 * 		initiateClient
 * 		initiateLobby
 * 		addClientToLobby
 * 		removeClientFromLobby
 * 		removeClient
 * 		removeLobby
 * 		getClient
 * 		getLobby
 * 		toString
 *
 */
public class Manager {
	private ArrayList<ManagerItem> list;
	public Manager() {
		list = new ArrayList<ManagerItem>();

		Client stavros = initiateClient("Stavros", 135,  null, 1234);
		Client clarance = initiateClient("Clarence", 56,  null, 1234);
		Client edgar = initiateClient("Edgar", 24,  null, 1234);
		Client yotsuba = initiateClient("Yotsuba", 45,  null, 1234);
		initiateClient("Josh", 12,  null, 1234);
		initiateClient("Arvid", 9,  null, 1234);
		
		Lobby bombardistisk = initiateLobby("BomSchak al Lacka", 5, 1);

		System.out.println(toString());
		addClientToLobby(stavros, bombardistisk);
		addClientToLobby(stavros, bombardistisk);
		addClientToLobby(clarance, bombardistisk);
		addClientToLobby(edgar, bombardistisk);
		addClientToLobby(yotsuba, bombardistisk);
		removeClientFromLobby(stavros, bombardistisk);
		System.out.println(bombardistisk.toString());
		removeClient(stavros.getId());
		removeClient(stavros.getId());
		System.out.println(toString());
		removeLobby(bombardistisk.getId());
		
		System.out.println(toString());
	}
	
	/*
	 * Creates a new lobby
	 */
	public Lobby initiateLobby(String name, int maxClients, int id){
		Lobby lobby = new Lobby(name, maxClients, id); 
		
		list.add(lobby);
		System.out.println("<Manager> "+name+" has been created.");
		return lobby;
	}
	
	/*
	 * Initiates a new client
	 */
	public Client initiateClient(String name, int id, InetAddress ip, int port){
		Client client = new Client(name, id, ip, port);
		list.add(client);
		System.out.println("<Manager> "+name+" has joined the server");
		return client;
	}
	
	/*
	 * Adds client to a lobby
	 */
	public void addClientToLobby(Client client, Lobby lobby){
		if(lobby.add(client)){
			list.remove(client);
		}
	}
	
	/*
	 * Removes client from a lobby
	 */
	public void removeClientFromLobby(Client client, Lobby lobby){
		if(lobby.remove(client)){
			list.add(client);
		}
	}

	/*
	 * Remove item
	 */
	public boolean removeClient(int id){
		ManagerItem l = getClient(id);
		if(l == null){
			System.out.println("<Manager> Client does not exist.");
			return false;
		}else{
			list.remove(l);
			System.out.println("<Manager> "+l.getName()+" has been removed.");
			return true;
		}
	}
	/*
	 * Remove item
	 */
	public boolean removeLobby(int id){
		ManagerItem l = getLobby(id);
		if(l == null){
			System.out.println("<Manager> Client does not exist.");
			return false;
		}else{
			list.remove(l);
			System.out.println("<Manager> "+l.getName()+" has been removed.");
			return true;
		}
	}

	/*
	 * Returns the Client if it exists
	 */
	public Client getClient(int id){
		for(ManagerItem l : list){
			if(l.getClass() == Client.class){
				if(l.getId() == id){
					return (Client)l;
				}
			}
		}
		return null;
	}
	
	/*
	 * Returns the Lobby if it exists
	 */
	public Lobby getLobby(int id){
		for(ManagerItem l : list){
			if(l.getClass() == Lobby.class){
				if(l.getId() == id){
					return (Lobby)l;
				}
			}
		}
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String text = "<Manager> Contains: \n";
		for(ManagerItem l : list){
			text += "           -" + l.getName();
			if(list.size()-1 == list.indexOf(l)){
				if(l.getClass() == Client.class){
					text += "(Client)";
				}
			}else{
				if(l.getClass() == Client.class){
					text += "(Client)";
				}
				text += ", \n";
			}
		}
		return text;
	}

	public ArrayList<ManagerItem> getList(){
		return list;
	}
}
