package crawling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadStoreDivision {
	// 필드
	String path = this.getClass().getResource("/").getPath()+"/resource/StoreList.txt";
	private File file = new File(path);
	private List<String> storeDivisionList = new ArrayList<>();
	// 생성자
	public ReadStoreDivision() {
		try {
			String line;
			FileInputStream fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			if(!file.exists()) {
				file.createNewFile();
			} else {
				while((line=br.readLine()) != null) {
					storeDivisionList.add(line);
				}
				br.close();
			}
		} catch(Exception e) {
			System.out.println("Store 파일 읽기 오류 : " + e.toString());
		}
	}
	// 메소드
	public List<String> getStoreDivision(){
		return storeDivisionList;
	}
	
	public void printStoreDivision() {
		for(String s : storeDivisionList)
			System.out.println(s);
	}
}
