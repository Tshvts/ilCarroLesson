package utilits;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakeScreenshot {
    private static String createFileName() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
        Date date = new Date(System.currentTimeMillis());
        String currentDate = formater.format(date);
        String fileName = "src/test/resources/screenshots/" + currentDate + ".png";

        return fileName;
    }

    public static void takeScreenshots(TakesScreenshot screenshot)
    {
        String fileName = createFileName();
        File scrFile = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(scrFile,new File(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
