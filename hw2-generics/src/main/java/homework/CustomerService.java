package homework;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

//@SuppressWarnings({"java:S1186", "java:S1135", "java:S1172"}) // при выполнении ДЗ эту аннотацию надо удалить
public class CustomerService {

    // todo: 3. надо реализовать методы этого класса
    // важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    private final Comparator<Customer> customerComparator = Comparator.comparingLong(Customer::getScores);
    private final TreeMap<Customer, String> customers = new TreeMap<>(customerComparator);

    public Map.Entry<Customer, String> getSmallest() {
        return customers.firstEntry();
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> nextEntry = customers.higherEntry(customer);
        if (nextEntry == null && !customers.containsKey(customer)) {
            return null;
        }
        return (nextEntry != null) ? nextEntry : customers.lastEntry();
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }
}
