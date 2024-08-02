package homework;

import java.util.Map;
import java.util.TreeMap;

//@SuppressWarnings({"java:S1186", "java:S1135", "java:S1172"}) // при выполнении ДЗ эту аннотацию надо удалить
public class CustomerService {

    // todo: 3. надо реализовать методы этого класса
    // важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    private final TreeMap<Customer, String> customers = new TreeMap<>();

    public Map.Entry<Customer, String> getSmallest() {
        return customers.firstEntry();
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return customers.higherEntry(customer);
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }
}
