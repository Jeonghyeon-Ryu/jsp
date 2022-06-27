package crawling;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;

import common.BaseInfo;
import foodstore.FoodStore;
import foodstore.FoodStoreDAO;
import stores.Store;
import stores.StoreDAO;

public class Crawling {
	private String path = this.getClass().getResource("/").getPath()+"/resource/chromedriver.exe";
	private final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	private final String WEB_DRIVER_PATH = path;
	private WebDriver driver;
	private List<String> searchUrlList = null;
	List<BaseInfo> baseInfoList = null;
	
	public Crawling(String sector) throws InterruptedException {
		String storeDivision = null;
		Sector rs = new Sector();
		rs.readSectorFile();
		StoreDivision.readStoreDivision();
		List<String> searchSector = rs.getSectorOne(sector);
		List<String> searchStoreDivision = StoreDivision.getStoreDivision();
		
		// 웹 드라이버가 설치된 경로에서 드라이버.exe 파일 실행
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("--headless");
		driver = new ChromeDriver(options);
		
		for(int i=0; i<searchStoreDivision.size(); i++ ) {
			searchUrlList = new ArrayList<>();
			for(int j=0; j<searchSector.size(); j++) {
				 searchUrlList.add("https://map.naver.com/v5/search/"+searchSector.get(j)+" "+searchStoreDivision.get(i)+" 맛집");
			}
			storeDivision=searchStoreDivision.get(i);
			for(int j=0; j<searchUrlList.size(); j++) {
				driver.get(searchUrlList.get(j));
				Thread.sleep(2000);
				// 가게 리스트 스크롤
				driver.switchTo().frame(driver.findElement(By.id("searchIframe")));	// 가게 리스트 iframe 커서 이동
				WebElement scroll;
				try{
					scroll = driver.findElement(By.className("_1Az1K"));	// 가게 리스트 요소
				} catch(Exception e) {
					continue;
				}
				WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromElement(scroll);
				new Actions(driver).scrollFromOrigin(scrollOrigin, 0, 3000).perform();
				new Actions(driver).scrollFromOrigin(scrollOrigin, 0, 3000).perform();
				new Actions(driver).scrollFromOrigin(scrollOrigin, 0, 3000).perform();
				new Actions(driver).scrollFromOrigin(scrollOrigin, 0, 3000).perform();
				new Actions(driver).scrollFromOrigin(scrollOrigin, 0, 3000).perform();
				new Actions(driver).scrollFromOrigin(scrollOrigin, 0, 3000).perform();
				
//				driver.switchTo().defaultContent();
//				WebElement mainElement = driver.findElement(By.xpath("//*[@id=\"baseMap\"]/div[1]/div"));
//				mainElement.click();
//				Thread.sleep(2000);
//				Robot robot = new Robot();
//				
//				robot.keyPress(39);
//				Thread.sleep(800);
//				robot.keyRelease(39);
			
				// ******************************* 페이지 가게 정보 GET
				String preWebElement=null;
				List<WebElement> we = null;
				while(true) {
					try {
						we = driver.findElements(By.className("OXiLu"));
					} catch (NoSuchElementException e) {
						System.out.println("해당 지역에 검색결과가 없습니다.");
					}
					
					baseInfoList = new ArrayList<>();
					if(we.size()==0) {
						break;
					}
					if(we.get(0).getAttribute("innerText").equals(preWebElement)) {
						break;
					}
					// 세부 가게정보 Get 들어갈곳
					for(WebElement s : we) {
						try {
							s.click();
						} catch(ElementClickInterceptedException e) {
							System.out.println("클릭오류 발생");
						}
						driver.switchTo().defaultContent();
						Thread.sleep(2000);
						driver.switchTo().frame(driver.findElement(By.xpath("/html/body/app/layout/div[3]/div[2]/shrinkable-layout/div/app-base/search-layout/div[2]/entry-layout/entry-place-bridge/div/nm-external-frame-bridge/nm-iframe/iframe")));
						
						// 가게 이름
						BaseInfo baseInfo = new BaseInfo();
						WebElement storeElementSelector = driver.findElement(By.xpath("//*[@id=\"_title\"]/span[1]"));
						baseInfo.setName(storeElementSelector.getAttribute("innerText"));
						// 가게 주소
						try {
							storeElementSelector = driver.findElement(By.className("_2yqUQ"));
							baseInfo.setAddress(storeElementSelector.getAttribute("innerText"));
						} catch (NoSuchElementException e) {
							baseInfo.setAddress("");
						}
						
						// 가게 전화번호
						try {
							storeElementSelector = driver.findElement(By.className("_3ZA0S"));
							baseInfo.setPhone(storeElementSelector.getAttribute("innerText"));
						} catch (NoSuchElementException e) {
							baseInfo.setPhone("");
						}
						// 소개글
						try {
							storeElementSelector = driver.findElement(By.className("_3_09q"));
							storeElementSelector.click();
							storeElementSelector = driver.findElement(By.className("WoYOw"));
							baseInfo.setNotice(storeElementSelector.getAttribute("innerText"));
						} catch (org.openqa.selenium.NoSuchElementException e) {
							baseInfo.setNotice("");
						}
						baseInfoList.add(baseInfo);
						System.out.println("=======================================");
						driver.switchTo().defaultContent();
						driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"searchIframe\"]")));
					}
					if(baseInfoList.size()!=0) {
						inputDB(sector,storeDivision);
					}
					preWebElement = we.get(0).getAttribute("innerText");
					driver.findElements(By.className("_2bgjK")).get(1).click();
					Thread.sleep(2000);
					we.clear();
				}
			}
		}
		
		// ******************************* 지도 좌표 Open
		
	}
	private void inputDB(String sector,String storeDivision) {
		System.out.println("inputDB 진입");
		if(storeDivision.equals("카페")) {
			for(BaseInfo baseinfo : baseInfoList) {
				FoodStore store = new FoodStore();
				store.setCategory_id(1);
				store.setName(baseinfo.getName());
				store.setPhone(baseinfo.getPhone());
				store.setAddress(baseinfo.getAddress());
				store.setNotice(baseinfo.getNotice());
				FoodStoreDAO.getInstance().insert(store);
				System.out.println("insert 중");
			}
			System.out.println("insert 완료");
		} else if(storeDivision.equals("한식")) {
			for(BaseInfo baseinfo : baseInfoList) {
				FoodStore store = new FoodStore();
				store.setCategory_id(2);
				store.setName(baseinfo.getName());
				store.setPhone(baseinfo.getPhone());
				store.setAddress(baseinfo.getAddress());
				store.setNotice(baseinfo.getNotice());
				FoodStoreDAO.getInstance().insert(store);
				System.out.println("insert 중");
			}
			System.out.println("insert 완료");
		} else if(storeDivision.equals("중식")) {
			for(BaseInfo baseinfo : baseInfoList) {
				FoodStore store = new FoodStore();
				store.setCategory_id(3);
				store.setName(baseinfo.getName());
				store.setPhone(baseinfo.getPhone());
				store.setAddress(baseinfo.getAddress());
				store.setNotice(baseinfo.getNotice());
				FoodStoreDAO.getInstance().insert(store);
				System.out.println("insert 중");
			}
			System.out.println("insert 완료");
		} else if(storeDivision.equals("일식")) {
			for(BaseInfo baseinfo : baseInfoList) {
				FoodStore store = new FoodStore();
				store.setCategory_id(4);
				store.setName(baseinfo.getName());
				store.setPhone(baseinfo.getPhone());
				store.setAddress(baseinfo.getAddress());
				store.setNotice(baseinfo.getNotice());
				FoodStoreDAO.getInstance().insert(store);
				System.out.println("insert 중");
			}
			System.out.println("insert 완료");
		} else if(storeDivision.equals("양식")) {
			for(BaseInfo baseinfo : baseInfoList) {
				FoodStore store = new FoodStore();
				store.setCategory_id(5);
				store.setName(baseinfo.getName());
				store.setPhone(baseinfo.getPhone());
				store.setAddress(baseinfo.getAddress());
				store.setNotice(baseinfo.getNotice());
				FoodStoreDAO.getInstance().insert(store);
				System.out.println("insert 중");
			}
			System.out.println("insert 완료");
		}
	}
}
