import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Myclientside {

	public static void main(String[] args) {
	// Open the socket.
		Socket MyClient = null;
	 //Sending to server: OTS means Out To Server.
	    DataOutputStream OTS = null;
	  //receive response from the server: IFS means In From Server
	    DataInputStream IFS = null;
	    
	    
	    
	    try {
	           MyClient = new Socket("localhost", 1025);
	           
	           	OTS = new DataOutputStream(MyClient.getOutputStream());
		    	
		    	IFS = new DataInputStream(MyClient.getInputStream());
		    	
		    	
		    	 }
	    catch (IOException e) {
	    	
	        System.out.println(e);
	    } 
		    
		   //working logic. 
	    @SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		    
		    try {
		    	
		    	ObjectOutputStream OOTS = new ObjectOutputStream(MyClient.getOutputStream());
			 	   
		    	System.out.println("enter name: ");
		    	String str = input.next();
		    	OTS.writeUTF(str);
		    	OTS.flush();
		    	
		    	String in = IFS.readUTF();
		        			    	
		    	System.out.println(in);
		    	
		    	int num = input.nextInt();
		    	OTS.writeInt(num);
		    	
		    	String arrcreated = IFS.readUTF();
		    	
		    	System.out.println(arrcreated);
		    	
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
		    	
		    	OOTS.writeObject(myArray);
		    	
		    	
		    	String Deter = IFS.readUTF();
		    	
		    	System.out.println("\n"+Deter);
		    	
				
			} catch (Exception e) {
				System.out.println(e);
			}
		    
		    
	    //Closing the connections and in/output streams
	    try {
	    		OTS.close();
	    		IFS.close();
	       MyClient.close();
	    } 
	    catch (IOException e) {
	    	
	       System.out.println(e);
	    }
		        
		   
	}

}
