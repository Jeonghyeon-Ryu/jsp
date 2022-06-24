package crawling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class StoreDivision {
	// 필드
//	private static String path = StoreDivision.class.getResource("/").getPath()+"/resource/StoreList.txt";
	private static String path = "C:\\Users\\admin\\git\\jsp\\Test2\\src\\main\\java\\resource\\StoreList.txt";
	private static File file = new File(path);
	static List<String> storeDivisionList = new ArrayList<>();
	
	// 생성자
	public StoreDivision() {}
	// 메소드
	public static void readStoreDivision() {
		try {
			storeDivisionList = new ArrayList<>();
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
			System.out.println("Store 파일 읽기 오류 : " + e.toString());
		}
	}
	public static List<String> getStoreDivision(){
		return storeDivisionList;
	}
	
	public void printStoreDivision() {
		for(String s : storeDivisionList)
			System.out.println(s);
	}
	public static void setStoreDivision(String[] divs) {
		FileOutputStream fos;
		try {
			if(!file.exists()) {
				file.createNewFile();
			} else {
				file.delete();
				file.createNewFile();
			}
			fos = new FileOutputStream(file);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			for(String div : divs) {
				bw.write(div);
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (FileNotFoundException e) {
			System.out.println("Store 파일 읽기 오류");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("파일 생성 오류");
			e.printStackTrace();
		}		
	}
}
