import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Myserverside {

	public static void main(String[] args) {
		
	// Opening the socket.
	ServerSocket MyService = null;
	//listen for and accept connections from clients. 
    Socket clientSocket = null;
    // receive input from the client: IFC stands for In From Client
    DataInputStream IFC = null;
    //SenIFCg to Client: OTC means Out to Client.
    DataOutputStream OTC = null;
	
    try { 
    	 
    	MyService = new ServerSocket(1025);
    	clientSocket = MyService.accept();
    	
       
        }
    catch (IOException e) {
        	
           System.out.println(e);
        }
	    		
		
	    //working logic.
	    
	    try {

	    	IFC = new DataInputStream(clientSocket.getInputStream());
	    	OTC = new DataOutputStream(clientSocket.getOutputStream());
	    	ObjectInputStream OIFC = new ObjectInputStream(clientSocket.getInputStream());
	    	
	    	int input;
	    	String in = IFC.readUTF();
	    	
	    	System.out.println("User name: "+in);
	    	
	    	String out = "Welcome "+ in + " to coding.\nEnter matrix size: ";
	    	OTC.writeUTF(out);
	    	OTC.flush();
	    	
	    	input = IFC.readInt();
	    	
	    	System.out.println("User input: "+input);
	    	
	    	String tellsum = input+" by "+input+" matrix creaded.\nEnter matrix values." ;
	    	OTC.writeUTF(tellsum);
	    	OTC.flush();
	    	
	    	
	    	double myArray[][] = new double[input][input];
	    	myArray= (double[][]) OIFC.readObject();
	    	int i, j;
	    	
	    	for(i=0; i<input; i++){
	    		System.out.println("");
	    		for(j=0; j<input; j++){
	    			
	    			System.out.print("["+myArray[i][j]+"] ");
			    	
	    		}
	    	}
	    	System.out.println("");
	    	
	    	try {
	    		double det;
		    	
		    	det = getDecDet(myArray);
		    	
		    	System.out.println("\nDeterminant: "+ det);
		    	
		    	
			} catch (Exception e) {
				System.out.println(e);
			}
	    	
	    	
		} catch (Exception e) {
			System.out.println(e);
		}
	    
	    
    //Closing the connections and in/output streams
    try {
    	OTC.close();
    	IFC.close();
        clientSocket.close();
        MyService.close();
     } 
     catch (IOException e) {
        System.out.println(e);
     }

	}
	
	 static double getDecDet (double [][] a) {
         int n = a.length - 1;
         if (n < 0) return 0;
         double M [][][] = new double [n+1][][];
        
         M[n] = a;  // init first, largest, M to a
        
         // create working arrays
         for (int i = 0; i < n; i++)
            M[i] = new double [i+1][i+1];
        
         return getDecDet (M, n);
      } // end method getDecDet double [][] parameter
     
      static double getDecDet (double [][][] M, int m) {
        if (m == 0) return M[0][0][0];
        int e = 1;
       
        // init subarray to upper left mxm submatrix
        for (int i = 0; i < m; i++)
           for (int j = 0; j < m; j++)
              M[m-1][i][j] = M[m][i][j];
        double sum = M[m][m][m] * getDecDet (M, m-1);
       
        // walk through rest of rows of M
        for (int i = m-1; i >= 0; i--) {
          for (int j = 0; j < m; j++)
             M[m-1][i][j] = M[m][i+1][j];
          e = -e;
          sum += e * M[m][i][m] * getDecDet (M, m-1);
        } // end for each row of matrix
       
        return sum;
      } // end getDecDet double [][][], int

}


