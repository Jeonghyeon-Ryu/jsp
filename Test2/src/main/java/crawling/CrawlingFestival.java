package crawling;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import common.BaseInfo;
import stores.Store;
import stores.StoreDAO;

public class CrawlingFestival {
	private String path = this.getClass().getResource("/").getPath()+"/resource/chromedriver.exe";
	private final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	private final String WEB_DRIVER_PATH = path;
	private WebDriver driver;
	private String searchUrlList = "";
	List<BaseInfo> baseInfoList = null;
	
	public CrawlingFestival() throws InterruptedException {
		StoreDivision.readStoreDivision();
		searchUrlList = "https://search.naver.com/search.naver?sm=tab_sug.top&where=nexearch&query=축제";
			
		// 웹 드라이버가 설치된 경로에서 드라이버.exe 파일 실행
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("--headless");
		driver = new ChromeDriver(options);
		int pageNum = Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"main_pack\"]/div[3]/div[2]/div/div/div[3]/div[2]/div/span/span[3]")).getAttribute("innerText"));
			
		baseInfoList = new ArrayList<>();
		// ******************************* 지도 좌표 Open
		driver.get(searchUrlList);
		for(int i=0; i<pageNum; i++) {
			// 가게 리스트 스크롤
				
			BaseInfo info = new BaseInfo();
			WebElement element;
			try {
				element = driver.findElement(By.xpath("//*[@id=\"mflick\"]/div/div/div/div[1]/div[1]/div[1]/div/div[1]/div/strong/a"));	// 축제명
				info.setName(element.getAttribute("innerText"));
			} catch (Exception e) {
				info.setName("");
			}
			try {
				element = driver.findElement(By.xpath("//*[@id=\"mflick\"]/div/div/div/div[1]/div[1]/div[1]/div/div[2]/dl/dd[1]"));	// 기간
				info.setPhone(element.getAttribute("innerText"));
			} catch (Exception e) {
				info.setPhone("");
			}
			try {
				element = driver.findElement(By.xpath("//*[@id=\"mflick\"]/div/div/div/div[1]/div[1]/div[1]/div/div[2]/dl/dd[2]"));	// 주소
				info.setAddress(element.getAttribute("innerText"));
			} catch (Exception e) {
				info.setAddress("");
			}
			
			try {
				element = driver.findElement(By.className("_3_09q"));
				element.click();
			} catch (Exception e) {
				System.out.println("소개글 더보기 없음");
			}
			try {
				element = driver.findElement(By.className("WoYOw"));	// 가게 소개
				info.setNotice(element.getAttribute("innerText"));
			} catch (Exception e) {
				info.setNotice("");
			}
			baseInfoList.add(info);
		}
	}
	private void inputDB(String storeDivision) {
		if(storeDivision.equals("동물원")) {
			for(BaseInfo baseinfo : baseInfoList) {
				Store store = new Store();
				store.setCategory_id(1);
				store.setName(baseinfo.getName());
				store.setPhone(baseinfo.getPhone());
				store.setAddress(baseinfo.getAddress());
				store.setNotice(baseinfo.getNotice());
				StoreDAO.getInstance().insert(store);
				System.out.println("insert 중");
			}
			System.out.println("insert 완료");
		} else if(storeDivision.equals("놀이공원")) {
			for(BaseInfo baseinfo : baseInfoList) {
				Store store = new Store();
				store.setCategory_id(3);
				store.setName(baseinfo.getName());
				store.setPhone(baseinfo.getPhone());
				store.setAddress(baseinfo.getAddress());
				store.setNotice(baseinfo.getNotice());
				StoreDAO.getInstance().insert(store);
				System.out.println("insert 중");
			}
			System.out.println("insert 완료");
		} else if(storeDivision.equals("아쿠아리움")) {
			for(BaseInfo baseinfo : baseInfoList) {
				Store store = new Store();
				store.setCategory_id(2);
				store.setName(baseinfo.getName());
				store.setPhone(baseinfo.getPhone());
				store.setAddress(baseinfo.getAddress());
				store.setNotice(baseinfo.getNotice());
				StoreDAO.getInstance().insert(store);
				System.out.println("insert 중");
			}
			System.out.println("insert 완료");
		} else if(storeDivision.equals("체험")) {
			for(BaseInfo baseinfo : baseInfoList) {
				Store store = new Store();
				store.setCategory_id(4);
				store.setName(baseinfo.getName());
				store.setPhone(baseinfo.getPhone());
				store.setAddress(baseinfo.getAddress());
				store.setNotice(baseinfo.getNotice());
				StoreDAO.getInstance().insert(store);
				System.out.println("insert 중");
			}
			System.out.println("insert 완료");
		}
	}
}
