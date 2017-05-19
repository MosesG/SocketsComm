import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Myserverudp {

	public static void main(String args[]) throws Exception
    {
       @SuppressWarnings("resource")
       DatagramSocket serverSocket = new DatagramSocket(9876);
       

          byte[] receiveData = new byte[1024];
          byte[] sendData = new byte[1024];
          
          while(true)
             {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                
                String sentence = new String( receivePacket.getData());
                System.out.println("input matrix as a string: "+ sentence);
                
                double inputmat[][] = STM(sentence); 
                
                getDecDet(inputmat);
                
                double det = getDecDet(inputmat);
                
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                

    	    	String out = "Your Determinant is:  "+ det;
                sendData = out.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
 
                
                
             }
    }
	
	//we convert the string back to a matrix
	public static double[][] STM(String input){
		   String str = "";
		   String[] array = input.split(",");
		   int n = (int) Math.sqrt(array.length);
		   double [][] matrix = new double[n][n];
		   
		   int counter = 0;
		   for(int i = 0; i < n; i++){
			   for(int j = 0; j < n; j++){
				   matrix[i][j] = Double.parseDouble(array[counter]);
				   counter++;
			   }
		   }
		   
		   System.out.println("Matrix Generated:");
	    	for(int i=0; i<n; i++){
	    		System.out.println("");
	    		for(int j=0; j<n; j++){
	    			
	    			System.out.print("\t["+matrix[i][j]+"] ");
			    	
	    		}
	    	}
	    	
		   
		   return matrix;
		   
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
