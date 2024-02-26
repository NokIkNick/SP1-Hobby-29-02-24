package cphbusiness.groupone.dao;

public interface IDAO<T>{
    void create(T in);

    T read(int id);

    T update(T obj,int id);

    void delete(int id);
}
