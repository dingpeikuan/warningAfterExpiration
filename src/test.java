import java.text.SimpleDateFormat;
import java.util.Date;

public class test {

    public static void main(String[] args) {
        Date currentDate = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("yyy-MM-dd:"+sdf1.format(currentDate));
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("yyy-MM-dd:"+sdf2.format(currentDate));
    }

}
