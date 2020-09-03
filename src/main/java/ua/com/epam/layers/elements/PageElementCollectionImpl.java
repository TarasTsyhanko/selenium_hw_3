package ua.com.epam.layers.elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ua.com.epam.factory.DriverContainer;
import ua.com.epam.factory.Wait;
import ua.com.epam.pagefactory.WrapperFactory;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Log4j2
public class PageElementCollectionImpl<T> implements PageElementCollection<T> {
    private List<T> webElements;
    private final By locator;
    private final Class<T> tClass;

    public PageElementCollectionImpl(By locator, Class<T> tClass) {
        this.locator = locator;
        this.tClass = tClass;
    }

    private List<T> refreshElements() {
        List<WebElement> list = DriverContainer.getDriver().findElements(locator);
        addList(list);
        return webElements;
    }

    public void addList(List<WebElement> elementList) {
        webElements = new ArrayList<>();
        elementList.forEach(element -> webElements.add(WrapperFactory.createInstance(tClass, element, null)));
    }

    @Override
    public <S> List<S> map(Function<? super T, ? extends S> mapper) {
        return refreshElements().stream().map(mapper).collect(Collectors.toList());
    }

    @Override
    public T get(int index) {
        return refreshElements().get(index);
    }

    @Override
    public List<T> getWithLimit(int limit) {
        return refreshElements().stream().limit(limit).collect(Collectors.toList());
    }

    @Override
    public PageElementCollection<T> waitUntilVisible() {
        addList(Wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)));
        return this;
    }

    @Override
    public PageElementCollection<T> waitUntilPresent() {
        addList(Wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator)));
        return this;
    }

    @Override
    public int size() {
        return refreshElements().size();
    }

    @Override
    public boolean isEmpty() {
        return refreshElements().isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return refreshElements().contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new PageElementIterator();
    }

    private class PageElementIterator implements Iterator<T> {
        Iterator<T> iterator = refreshElements().iterator();

        @Override
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override
        public T next() {
            return this.iterator.next();
        }

        @Override
        public void remove() {
            this.iterator.remove();
        }
    }

    @Override
    public Object[] toArray() {
        return refreshElements().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return refreshElements().toArray(a);
    }

    @Override
    public boolean add(T element) {
        return webElements.add(element);
    }

    @Override
    public boolean remove(Object o) {
        return refreshElements().remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return refreshElements().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return refreshElements().addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return refreshElements().removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return refreshElements().retainAll(c);
    }

    @Override
    public void clear() {
        refreshElements().clear();
    }
}
