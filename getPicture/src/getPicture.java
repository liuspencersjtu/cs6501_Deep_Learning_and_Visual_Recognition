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
        System.setProperty("webdriver.chrome.driver","/Users/rouman/Documents/GitHub/getPicture/bin/chromedriver");
        driver = new ChromeDriver();
        driver.get(url);
    }

    @After
    public void teardown(){
        driver.quit();
    }

    @Test
    public void getPic(){
        //assertEquals(driver.getTitle(),"hi");
        try{TimeUnit.SECONDS.sleep(5);}
        catch (Exception e){}
        for(int i = 0;i < 2000;i++) {
            driver.findElement(By.xpath("//button[@class='btn btn-primary ']")).click();
            try{
                TimeUnit.SECONDS.sleep(2);
            }
            catch (Exception e){}
            String ret = driver.findElement(By.xpath("//img[@alt='result']")).getAttribute("src");
            ret = ret.split(",")[1];
            //System.out.println(ret);
            if (ret != null){
                BASE64Decoder decoder = new BASE64Decoder();
                String imgFilePath = "./output/"+(i+1)+".png";//新生成的图片
                try {
                    byte[] b = decoder.decodeBuffer(ret);
                    OutputStream out = new FileOutputStream(imgFilePath);
                    out.write(b);
                    out.flush();
                    out.close();
                }
                catch (Exception e){

                }
            }
//            try
//            {
//                //Base64解码
//                byte[] b = decoder.decodeBuffer(imgStr);
//                for(int i=0;i<b.length;++i)
//                {
//                    if(b[i]<0)
//                    {//调整异常数据
//                        b[i]+=256;
//                    }
//                }
//                //生成jpeg图片
//               
//               
//                return true;
//            }
//            catch (Exception e)
//            {
//               return false;
//            }
        }
    }
}
