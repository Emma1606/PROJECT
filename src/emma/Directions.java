package emma;

public class Directions {
	private final int ROOM_LENGTH = 5; // size of the room string
	private char building ;
	private char floor;
	private String locaationOnFloor = null;

	public Directions() {

	}

	
	public char getBuilding() {
		return building;
	}


	public void setBuilding(char building) {
		this.building = building;
	}


	public char getFloor() {
		return floor;
	}


	public void setFloor(char floor) {
		this.floor = floor;
	}


	public String getLocaationOnFloor() {
		return locaationOnFloor;
	}

	public void setLocaationOnFloor(String locaationOnFloor) {
		this.locaationOnFloor = locaationOnFloor;
	}

	/*
	 * This method takes in a room eg E2004 and splits up into the correct block
	 * (Engineering, Science, Business, etc) The correct floor The correct room
	 * number
	 */
	public boolean validate(String room) {
		if (room.length() != ROOM_LENGTH) {
			return false;
		}
		if (Character.isLetter(room.charAt(0)) == false) {
			return false; // room must start with a letter
		}
		for (int i = 1; i < ROOM_LENGTH; i++) {
			if (Character.isDigit(room.charAt(i)) == false) {
				return false; // room must start with a letter
			}
		}
		
		// all ok - store the info
		building = room.charAt(0);
		floor = room.charAt(1);
		locaationOnFloor = room.substring(2);
		
		return true;
	}
	
	/*
	 * Get directions to building
	 */
	public String toBuilding() {
		String directions = null;
		switch (this.building) {
		case 'A':			
			directions = "From reception, walk straight ahead and then turn to your right";
			QRSound.main('A');
			break;
		case 'B':
			// TODO play the sound here for this here
			directions = "From reception, go up main stairs on your left and turn right.  Walk for 40m past Library until you read the Booknest";
			QRSound.main('B');
			break;
		case 'C':
			// TODO play the sound here for this here
			directions = "From reception, go up main stairs on your left and turn right.  Walk for 40m past Library until you read the Booknest, then turn left and continue through long corridor";
			QRSound.main('C');
			break;
		case 'D':
			// TODO play the sound here for this here
			directions = "From reception, go up main stairs on your left and turn right.  Walk for 40m past Library until you read the Booknest, then turn left and continue through long corridor";
			QRSound.main('C');
			break;
		case 'E':
			// TODO play the sound here for this here
			directions = "From reception, move to the centre of reception and turn left into the engineering building";
			QRSound.main('E');
			break;
		case 'F':
			// TODO play the sound here for this here
			directions = "From reception, walk outside and turn to your right.  Walk past the engineering building and the F block is straight in front";
			QRSound.main('F');
			break;
		default:
			directions = "Sorry, that building in not recognised";
			//QRSound.main('g');
			break;
			
		}
		return(directions);
	}
	
	/*
	 * Get directions to floor
	 */
	public String toFloor() {
		String directions = null;
		switch (this.floor) {
		case '0':
			QRSound.main('0');
			directions = "Stay on this floor";
			break;
		case '1':
			// TODO play the sound here for this here
			directions = "Ascend the stairs or take the lift to the first floor";			
			break;
		case '2':
			directions = "Ascend two flight of stairs or take the lift to the second floor";
			QRSound.main('2');
			break;
		default:
			directions = "Sorry, floor " + this.floor + " is not recognised";
			break;
			
		}
		return(directions);
	}

	/*
	 * Get directions to floor
	 */
	public String toLocation() {
		String directions = null;
		switch (this.locaationOnFloor) {
		case "006":
			QRSound.main('r');
			directions = "This is a room to the right on this level";
			break;
		case "007":
			// TODO play the sound here for this here
			directions = "This is a room to the right on this level";			
			break;
		case "003":
			// TODO play the sound here for this here
			directions = "This is the last room to the right on this level";			
			break;
		case "004":
			QRSound.main('4');
			directions = "This is the second last room to the right on this level";			
			break;
		default:
			directions = "Sorry, that room in not recognised";
			break;
			
		}
		return(directions);
	}

}
