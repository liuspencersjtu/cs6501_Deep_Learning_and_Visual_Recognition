import org.junit.Before;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;
import sun.misc.BASE64Decoder;
import java.io.OutputStream;
import java.io.FileOutputStream;

public class getPicture {
    private WebDriver driver;
    private String url = "https://make.girls.moe/#/history";

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","/Users/rouman/Documents/GitHub/cs6501_Deep_Learning_and_Visual_Recognition/getPicture/bin/chromedriver");
        driver = new ChromeDriver();
    }

    @After
    public void teardown(){
        driver.quit();
    }

    @Test
    public void getPic(){
        int i = 900;
        String last = "";
        while(i<2000) {
            driver = new ChromeDriver();
            driver.get(url);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
            }
            while (true) {
                driver.findElement(By.xpath("//button[@class='btn btn-primary ']")).click();
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (Exception e) {
                }
                String ret = driver.findElement(By.xpath("//img[@alt='result']")).getAttribute("src");
                ret = ret.split(",")[1];
                if (ret != null && !ret.equals(last)) {
                    last = ret;
                    BASE64Decoder decoder = new BASE64Decoder();
                    String imgFilePath = "./output/" + (i + 1) + ".png";//新生成的图片
                    i++;
                    try {
                        byte[] b = decoder.decodeBuffer(ret);
                        OutputStream out = new FileOutputStream(imgFilePath);
                        out.write(b);
                        out.flush();
                        out.close();
                    } catch (Exception e) {

                    }
                }
                if(i%100==99){
                    break;
                }
            }
            driver.quit();
        }
    }
}
