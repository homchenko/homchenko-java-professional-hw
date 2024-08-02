package homework;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//@SuppressWarnings({"java:S1135"}) // при выполнении ДЗ эту аннотацию надо удалить
public class Customer implements Comparable<Customer>{
    private long id;
    private String name;
    private long scores;

    private static final Set<Long> existingIds = new HashSet<>();
    private static long nextId;

    // todo: 1. в этом классе надо исправить ошибки

    public Customer(long id, String name, long scores) {
        this.id = id; //generateUniqueId(id);
        this.name = name;
        this.scores = scores;

        existingIds.add(this.id);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getScores() {
        return scores;
    }

    public void setScores(long scores) {
        this.scores = scores;
    }

    private static long generateUniqueId(Long id) {
        if (!existingIds.contains(id)) {
            return id;
        }
        nextId = id;
        while (existingIds.contains(nextId)) {
            nextId++;
        }
        return nextId;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name='" + name + '\'' + ", scores=" + scores + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        /*if (id != customer.id) return false;
        if (scores != customer.scores) return false;
        return Objects.equals(name, customer.name);*/
        return id == customer.id;
    }

    @Override
    public int hashCode() {
        /*int result = Long.hashCode(id);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + Long.hashCode(scores);
        return result;*/
        return  Long.hashCode(id);
    }

    @Override
    public int compareTo(Customer c) {
        return Long.compare(this.scores, c.scores);
    }
}
