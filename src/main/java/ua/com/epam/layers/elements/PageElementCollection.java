package ua.com.epam.layers.elements;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public interface PageElementCollection<T> extends Collection<T> {
    <S> List<S> map(Function<? super T, ? extends S> mapper);

    T get(int index);

    List<T> getWithLimit(int limit);

    PageElementCollection<T> waitUntilVisible();

    PageElementCollection<T> waitUntilPresent();
}
