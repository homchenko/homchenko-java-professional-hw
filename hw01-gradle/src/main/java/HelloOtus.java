import com.google.common.base.Joiner;
import java.util.ArrayList;
import java.util.List;
public class HelloOtus {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("Otus");

        String result = Joiner.on(", ").join(list);
        System.out.println(result);
    }
}
