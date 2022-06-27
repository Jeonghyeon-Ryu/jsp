package crawling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadList {
	// 필드
//	private static String path = StoreDivision.class.getResource("/").getPath()+"/resource/StoreList.txt";
	private String zooPath = "C:\\Users\\admin\\git\\jsp\\Test2\\src\\main\\java\\resource\\ZooList.db";
	private String aquariumPath = "C:\\Users\\admin\\git\\jsp\\Test2\\src\\main\\java\\resource\\AquariumList.db";
	private String amusementParkPath = "C:\\Users\\admin\\git\\jsp\\Test2\\src\\main\\java\\resource\\AmusementPark.db";
	private File file = null;
	private List<String> storeDivisionList = null;
	
	// 생성자
	public ReadList(String path) {
		if(path.equals("아쿠아리움")) 
			this.file = new File(aquariumPath);
		else if(path.equals("동물원"))
			this.file = new File(zooPath);
		else if(path.equals("놀이공원"))
			this.file = new File(amusementParkPath);
		storeDivisionList = new ArrayList<>();
	}
	// 메소드
	private void readStoreDivision() {
		try {
			String line;
			if(!file.exists()) {
				file.createNewFile();
			}
			FileInputStream fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			while((line=br.readLine()) != null) {
				storeDivisionList.add(line);
			}
			br.close();
		
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	public List<String> getStoreDivision(){
		readStoreDivision();
		return storeDivisionList;
	}
	
	public void printStoreDivision() {
		for(String s : storeDivisionList)
			System.out.println(s);
	}
}
