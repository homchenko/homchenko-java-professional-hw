package homework;

import java.util.AbstractMap;
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
        Map.Entry<Customer, String> entry = customers.firstEntry();
        if (entry == null) {
            return null;
        }
        return new AbstractMap.SimpleEntry<>(entry.getKey().clone(), entry.getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> nextEntry = customers.higherEntry(customer);
        if (nextEntry == null && !customers.containsKey(customer)) {
            return null;
        }
        if (nextEntry != null) {
            return new AbstractMap.SimpleEntry<>(nextEntry.getKey().clone(), nextEntry.getValue());
        } else {
            Map.Entry<Customer, String> lastEntry = customers.lastEntry();
            return lastEntry != null ? new AbstractMap.SimpleEntry<>(lastEntry.getKey().clone(), lastEntry.getValue()) : null;
        }
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }
}
