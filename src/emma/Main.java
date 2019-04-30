package emma;

import java.io.IOException;


import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextField;

import com.google.zxing.EncodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class Main {

		
		static JTextField textField = null;
		static String filePath = "myQRCode.png";
		static String qrCodeData;
		
		//Qrcode1
		
		//static String qrCodeData = "Day: Tuesday\nTime: 09:00 to 11:00\nSubject: Software Engineering\nRoom: E2004";
		//QRcode2
		//static String qrCodeData = "Day: Wenesday\nTime: 14:00 to 16:00\nSubject: Microcontroller Programming\nRoom: A0006";
		
		//QRcode3
		//static String qrCodeData = "HELLO WORLD\nMy name is\n Emma\n Dollé";
		//static String qrCodeData = "Day Wenesday\nTime: 14:00 to 16:00\nSubject: Microcontroller Programming\nRoom: A0006";
		
		
		static QR qr ;
		static Room room = new Room();
		static Directions directions = new Directions();
		static Day date = new Day();
		static Hour hour = new Hour();

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public static void main(String[] args) throws WriterException, IOException, NotFoundException {
			// Initial hardcoded data for test program 
			
			qrCodeData = "Day: Tuesday\nTime: 09:00 to 11:00\nSubject: Software Engineering\nRoom : E2004\n";
			//qrCodeData = "Day: Monday\nTime: 11:00 to 13:00\nSubject: English\nRoom :A0004\n";
			//qrCodeData = "Day: Tuesday\nTime: 09:00 to 11:00\nSubject: Software Engineering\nRoom :E004";
			
			System.out.println(qrCodeData);
			
			String theRoom = room.get(qrCodeData);
			if(directions.validate(theRoom)==false){
				String error ="error";
				qrCodeData = error;
				QRSound.main('w');
			}else{
				
				String direction ="\nDirections: " + "\n" + directions.toBuilding() + "\n" + directions.toFloor() + "\n" + directions.toLocation();
				qrCodeData = qrCodeData + direction;
				//System.out.println(qrCodeData);
			}
			
			
			 qr = new QR(qrCodeData, filePath);
			
			
			
			
			
			Map hintMap = new HashMap();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

			// create the QR barcode
			qr.createQRCode(qrCodeData, filePath, hintMap, 200, 200);
			System.out.println("QR Code image created successfully!");

			// read the barcode
			String QRdata = qr.readQRCode(filePath, hintMap);
			System.out.println(QRdata);
			
			//Is it a QRCode the type we want
//			String delims = "[\n]+";
//			String[] tokens = QRdata.split(delims);
//			
//			String line0=tokens[0];
//			String line1=tokens[1];
//			String line2=tokens[2];
//			String line3=tokens[3];
//			String delims2 = "[ ]+";
//			String[] line00 = line0.split(delims2);
//			String[] line01 = line1.split(delims2);
//			String[] line02 = line2.split(delims2);
//			String[] line03 = line3.split(delims2);
//			
//			if((line00[0].equals("Day:")!=true)||(line01[0].equals("Time:")!=true)||(line02[0].equals("Subject:")!=true)||(line03[0].equals("Room:")!=true)){
//				QRSound.main('w');
//				//QRSound.main('z');
//				System.exit(0);
//			}
//			
//			
//			
//			//Find the hour
//			String thehour = hour.get(qrCodeData);
//			System.out.println("The hour is " + thehour);
//			int Currenthour = Integer.parseInt(hour.hour);
//			int QRhour = Integer.parseInt(thehour);
//			
//			//Find the day
//			
//			String QRday = date.get(QRdata);
//			
//			if(date.Currentdate==QRday){
//				System.out.println("The lesson is Today");
//				QRSound.main('Y');
//				
//				if(Currenthour==QRhour){
//					System.out.println("You're late, hurry up");
//					QRSound.main('m');
//				}
//				else if(Currenthour>QRhour){
//					String endhour = hour.get(QRdata);
//					int QRhour2 = Integer.parseInt(endhour);
//					if (Currenthour>QRhour2){
//						System.out.println("It's too late, the lesson has allready ended");
//						QRSound.main('l');
//					}
//					else{
//						System.out.println("You're more than 1 hour late =(");
//						QRSound.main('s');
//					}
//				}
//				else {
//					System.out.println("The lesson starts at " + QRhour);
//					QRSound.main('9');
//				}
//			}
//			else{
//				System.out.println("The lesson is not Today");
//				QRSound.main('N');
//			}
//			
//			
//			
//			
//			// Find the room
//			String theRoom1 = room.get(QRdata);
//			System.out.println("The room is " + theRoom1);
//			
//			
//			// get directions
//			if (directions.validate(theRoom1) == false) {
//				//System.out.println("The directions to this room are unknown");
//				String error = "Hello, I'm sorry, the QR code you scanned is not recognised. Please go to the reception for further help.";
//				qrCodeData = error;
//			}
//			else {
//				System.out.println("DIRECTIONS");
//				System.out.println(directions.toBuilding());
//				System.out.println(directions.toFloor());
//				System.out.println(directions.toLocation());
//				/*String direction = "\nDirections : " +"\n"+directions.toBuilding() +"\n"+ directions.toFloor()+"\n"+ directions.toLocation();
//				qrCodeData = qrCodeData + direction;*/
//				//System.out.println(qrCodeData);
//			} 
			
			filePath = "myQRCode.png";
		}
		 
}