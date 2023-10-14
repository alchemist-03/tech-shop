import java.io.BufferedReader;
import java.io.InputStreamReader;

public class test {
    public static void main(String[] args) {
        try {
            // Lấy quản lý tiến trình của hệ thống
            Process process = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");

            // Đọc kết quả trả về từ lệnh tasklist
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            // Đọc và hiển thị thông tin về từng tiến trình
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Đóng luồng đọc
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
