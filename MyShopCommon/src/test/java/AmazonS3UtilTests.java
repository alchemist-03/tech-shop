import com.myshop.common.AmazonS3Util;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class AmazonS3UtilTests {

    @Test
    public void testListFolder() {
        String folderName = "products-photos/2";
        List<String> listKeys = AmazonS3Util.listFolder(folderName);
        listKeys.forEach(System.out::println);

    }

    @Test
    public void testUploadFile() throws FileNotFoundException {

        String folderName = "test-upload";
        String fileName = "logitech_brand_lo.jpg";
        String filePath = "D:\\Downloads\\" + fileName;
        InputStream inputStream = new FileInputStream(filePath);
        AmazonS3Util.uploadFile(folderName, fileName, inputStream);
    }

    @Test
    public void testDeleteFile() {
        String fileName = "test-upload/bg-tech-for-project.png";
        AmazonS3Util.deleteFile(fileName);

    }

    @Test
    public void testRemoveFolder() {
        String folderName = "test-upload";
        AmazonS3Util.removeFolder(folderName);

    }
}
