package cphbusiness.groupone.dao;

public interface IDAO<T, IDType>{
    void create(T in);

    T read(IDType id, Class<T> in);

    T update(T obj, IDType id);

    void delete(Class<T> tClass, IDType id);
}
