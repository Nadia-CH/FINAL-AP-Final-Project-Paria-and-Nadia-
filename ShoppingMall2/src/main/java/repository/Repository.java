package repository;

import java.util.List;

public interface Repository<T> {

    public void addItem(T item);


    public void removeItem(T item);


    public void updateItem(T item);


    public T getById(int id);


    public List<T>  getAll();

}