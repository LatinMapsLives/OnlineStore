package by.demidov_a_r.onlinestore.mapper;

public interface Mapper<F, T>{

    T mapTo(F object);
}