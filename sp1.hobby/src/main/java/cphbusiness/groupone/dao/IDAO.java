package cphbusiness.groupone.dao;

@SuppressWarnings("UnusedReturnValue")
public interface IDAO<T, IDType>{
    void create(T in);

    T read(IDType id, Class<T> in);
    T read(IDType id);

    T update(T obj, IDType id);
    T update(T obj);

    void delete(Class<T> tClass, IDType id);
}
