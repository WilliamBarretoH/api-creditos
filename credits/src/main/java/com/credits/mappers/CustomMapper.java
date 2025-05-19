package com.credits.mappers;

public interface CustomMapper<D, E> {

    D toDto(E entity);
}
