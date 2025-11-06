package com.fernando.sprinboot.restaurant.proyect.restaurant.mappers.interfaces;

import java.util.List;
import java.util.Set;

public interface BaseMapper<E, D> {;
    public D toDto(E entity);
    public List<D> toListDto(List<E> entities);
    public Set<D> toSetDto(Set<E> entities);
}
