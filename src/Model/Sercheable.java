package Model;

public interface Sercheable<E> {
    E searchById(int id);
    E searchByName(String name);
}
