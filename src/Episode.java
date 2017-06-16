import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Episode {
	public void play(){
		File[] listOfFiles=new ReadFile().getAllFiles();
		//listOfFiles=new FileSorter().sortByNumber(listOfFiles);
		Arrays.sort(listOfFiles, new AlphanumFileComparator() );
		for(File f : listOfFiles) {
            System.out.println(f.getName());
        }
		String currFileName=new ReadFile().currentFile();
		if(currFileName.equals("Empty") || new ReadFile().isSeasonChanged()){
			System.out.println("Running First Episode");
			String runF="";
			String runP="";
			for (int i = 0; i < listOfFiles.length; i++) {
			      if (listOfFiles[i].isFile()) {
			    	  runF = listOfFiles[i].getName();
			    	  System.out.println("Request Play :"+runF);
			    	  runP=listOfFiles[i].getAbsolutePath();
			    	  break;
			      } else if (listOfFiles[i].isDirectory()) {
			        continue;
			      }
			    }
			File mp = new File(runP);
				try {
					Desktop.getDesktop().open(mp);
					new ReadFile().writeEpisode(runF);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		else{
			System.out.println("Last Played:"+currFileName);
			boolean playNext=false;
			for (int i = 0; i < listOfFiles.length; i++) {
				
			      if (listOfFiles[i].isFile()) {
			        /*System.out.println("File " + listOfFiles[i].getName());*/
			    	  System.out.println("Reading file:"+listOfFiles[i].getName());
			    	  if(currFileName.equals(listOfFiles[i].getName())){
			    		  System.out.println("Last Played:"+currFileName + "Now Playing the next one!");
			    		  playNext=true;
			    	  }
			    	  else if(playNext){
			    		  playNext=false;
			    		  String runF = listOfFiles[i].getName();
			    		  System.out.println("Request Play :"+runF);
			    		  String runP=listOfFiles[i].getAbsolutePath();
			    		  File mp = new File(runP);
							try {
								Desktop.getDesktop().open(mp);
								new ReadFile().writeEpisode(runF);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			    	  }
			    	  
			      } else if (listOfFiles[i].isDirectory()) {
			        //System.out.println("Directory " + listOfFiles[i].getName());
			      }
			    }
			
		}
	    
	}
	public static void main(String[] args) {
		new Episode().play();
	}
}
				