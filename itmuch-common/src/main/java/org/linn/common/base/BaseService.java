package org.linn.common.base;

import java.util.Optional;

/**
 * created by linn  20/3/12 21:30
 **/
public interface BaseService<T> {

    T save(T t);

    Optional<?> findById(String id);


}
