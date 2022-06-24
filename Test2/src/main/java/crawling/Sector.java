package crawling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sector {
	Set<String> sectorList = new HashSet<>();
	String path = this.getClass().getResource("/").getPath()+"/resource/SectorList.txt";
	private File file = new File(path);
	public Sector() {
		readSectorFile();
	}
	public void readSectorFile() {
		String line;
		String[] tmp = new String[3];
		try {
			if(!file.exists()) {
				file.createNewFile();
			} else {
				FileInputStream fis = new FileInputStream(file);
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
				while((line=br.readLine()) != null) {
					tmp = line.split("\t");
					if(tmp.length==3) {
						sectorList.add(tmp[0]+" "+tmp[1]+" "+tmp[2]);
					}
				}
				br.close();
			}
		} catch (Exception e) {
			System.out.println("Sector 읽기 실패 : " + e.toString());
		}
	}
	public void printSectorList() {
		List<String> list = new ArrayList<>(sectorList);
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println(list.size());
	}
	
	public List<String> getSectorOne(String sector) {
		List<String> list = new ArrayList<>(sectorList);
		List<String> result = new ArrayList<>();
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).indexOf(sector)>=0) {
				result.add(list.get(i));
			}
		}
		return result;
	}
	public List<String> getSectorAll() {
		return new ArrayList<String>(sectorList);
	}
	
}
