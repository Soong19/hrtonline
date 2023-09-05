import java.io.File;
import java.net.URI;
import java.nio.file.Path;

public class Test {
    @org.junit.Test
    public void test(){
        String fileName="12345.txt";
        File file = new File(fileName);
        URI uri = file.toURI();
        String s = uri.toString();

        System.out.println(uri);

    }
}
