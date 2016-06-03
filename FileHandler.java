import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;


public class FileHandler {
	private String file = "";

	public String getFile() {
		return file;
	}

	public void setFile(String _file) {
		this.file = _file;
			 
	}
	
	
	public ArrayList<String> getData(){
		ArrayList<String> data = new ArrayList<String>();
		FileReader fr;
		BufferedReader br;
		try{
			fr = new FileReader(file);
			 br= new BufferedReader(fr);
			String line = br.readLine();
			int i = 1;
			while(  line != null ||  i> 20){
				i++;
				 data.add(line);
				 line = br.readLine();
				 
			}
		}catch(Exception e){
			if( e != null)
			System.out.println("error : "+e.toString() + " " + e.getMessage());
		}
		return data;
	}
	public void saveData(ArrayList<String> data)  {

		try{
			FileWriter fw = new FileWriter(file);
			PrintWriter pw= new PrintWriter(fw);
			 for(String l : data){
				 pw.println(l);
			}
			fw.flush();
			pw.flush();
			fw.close();
			pw.close();
		}catch(Exception e){
			if( e != null)
				System.out.println("error : "+e.toString() + " " + e.getMessage());
		}
 
				 
		
		
	}
}
