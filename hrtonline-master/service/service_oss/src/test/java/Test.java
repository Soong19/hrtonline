import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
    @org.junit.Test
    public void test(){
        SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmss");//设置日期格式
        String time = df.format(new Date());
        System.out.println(time);
    }

}
