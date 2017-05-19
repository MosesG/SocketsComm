import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Myclientudp {

	   public static void main(String args[]) throws Exception
	   {
	     
	      DatagramSocket clientSocket = new DatagramSocket();
	      
	      InetAddress IPAddress = InetAddress.getByName("localhost");
	      
	      byte[] sendData = new byte[1024];
	      byte[] receiveData = new byte[1024];
	   
	      
	      System.out.println("This is a code to get the determinant for an n by n matrix. Try it Enjoy. ");
	      @SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
	
	      System.out.println("\nEnter matrix size: ");
	      
	      int num = input.nextInt();
          
	      
	      System.out.println("\nEnter values for "+num+" by "+num+" matrix created: ");
           int i,j;
	    	double myArray[][] = new double[num][num];
	    	
	    	for(i=0; i<num; i++){
	    		for(j=0; j<num; j++){
	    			
	    			double value= input.nextDouble();
	    			
	    			
	    			
	    			myArray[i][j] = value;
			    	
	    		}
	    	}
	    	
	    	System.out.println("This is your matrix:");
	    	for(i=0; i<num; i++){
	    		System.out.println("");
	    		for(j=0; j<num; j++){
	    			
	    			System.out.print("\t["+myArray[i][j]+"] ");
			    	
	    		}
	    	}
	    	
	    	
	    	  sendData = MTS(myArray).getBytes();
		      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
		      clientSocket.send(sendPacket);
		      
		      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		      clientSocket.receive(receivePacket);
		      
		      String deter = new String(receivePacket.getData());
		      System.out.println("\n"+deter);
          
	      clientSocket.close();
	   }
	   
	   
	   public static String MTS(double[][] matrix){
		   String str = "";
		   for(int i = 0; i < matrix.length; i++){
			   for(int j = 0; j < matrix.length; j++){
				   str = str.concat(matrix[i][j] + ",");
				   
			   }
		   }
		   
		   
		   return str;
		   
	   }
	   
	   
	   
	   

}
