package grafos;
import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;  

public class index {  

		public static void main(String[] args){  
			String line = "";  
			String splitBy = ";";  
		
			try{  
				//parsing a CSV file into BufferedReader class constructor  
				BufferedReader br = new BufferedReader(new FileReader("br.csv"));  
		
				while ((line = br.readLine()) != null){  
					String[] employee = line.split(splitBy);    // use comma as separator  
					System.out.println("Latitude=" + employee[1] + ", Longitude=" + employee[2]);  
				}  
			}   
			catch (IOException e){  
				e.printStackTrace();  
			}  
		}  
	}    