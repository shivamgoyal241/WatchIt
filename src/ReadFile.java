

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.text.AbstractDocument.BranchElement;

public class ReadFile {
	

	public String getSeasonPath() {
		
		
		/*String FILENAME = "seasonpath.txt";
		BufferedReader br = null;
		FileReader fr = null;*/
		String path = System.getProperty("user.dir") + "/seasonpath.txt";
		BufferedReader br=null;
		FileInputStream fis;
		try {
			fis = new FileInputStream(path);
			br = new BufferedReader(new InputStreamReader(fis));
			//fr = new FileReader(FILENAME);
			//br = new BufferedReader(fr);

			String sCurrentLine;

			//br = new BufferedReader(new FileReader(FILENAME));
			sCurrentLine = br.readLine();
			if(sCurrentLine !=null){
				return sCurrentLine;
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally{
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "Season Path Not found";
	}
	public File[] getAllFiles(){
		String folderPath=new ReadFile().getSeasonPath();
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		return listOfFiles;
	}
	public void writeEpisode(String episodeName){
		String path = System.getProperty("user.dir") + "/currentfile.txt";
		try{
		    PrintWriter writer = new PrintWriter(path, "UTF-8");
		    writer.println(episodeName);
		    writer.close();
		} catch (IOException e) {
		   // do something
		}
	}
	public boolean isSeasonChanged(){
		String currF=new ReadFile().currentFile();
		if(currF.equals("Empty")){
			return false;
		}
		else{
		File[] listOfFiles=new ReadFile().getAllFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  String runF = listOfFiles[i].getName();
		    	  if(runF.equals(currF)){
		    		  return false;
		    	  }
		      } else if (listOfFiles[i].isDirectory()) {
		        continue;
		      }
		    }
		return false;
		}
	}
	public String currentFile() {
		//String FILENAME = "currentfile.txt";
		BufferedReader br = null;
		FileReader fr = null;
		String path = System.getProperty("user.dir") + "/currentfile.txt";
		FileInputStream fis;
		try {

			//fr = new FileReader(FILENAME);
			//br = new BufferedReader(fr);

			String sCurrentLine;
			fis = new FileInputStream(path);
			br = new BufferedReader(new InputStreamReader(fis));
			//br = new BufferedReader(new FileReader(FILENAME));
			sCurrentLine = br.readLine();
			if(sCurrentLine !=null && !sCurrentLine.equals(" ")){
				return sCurrentLine;
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
			
		}
		return "Empty";
	}
}
