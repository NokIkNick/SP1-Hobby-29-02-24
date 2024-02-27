package cphbusiness.groupone.dao;

public interface IDAO<T>{
    void create(T in);

    T read(int id, T in);

    T update(T obj,int id);

    void delete(Class<T> tClass, int id);
}
