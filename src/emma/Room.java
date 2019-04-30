package emma;

public class Room {
	public String room;

	public Room() {
		
	}
	
	public String get(String data)
	{
		//room = "E2004";
		
		String delims = "[\n]+";
		String[] tokens = data.split(delims);
		
		String phrase2=tokens[3];
		String delims2 = "[: ]+";
		String[] room = phrase2.split(delims2);
	
		return(room[1]);
	}

}


