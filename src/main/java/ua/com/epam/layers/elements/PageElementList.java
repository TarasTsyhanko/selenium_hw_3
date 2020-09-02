package ua.com.epam.layers.elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ua.com.epam.factory.DriverContainer;
import ua.com.epam.factory.Wait;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Log4j2
public class PageElementList<T> implements PageElementCollection<T> {
    private List<T> webElements;
    private final By locator;
    private final Class<T> tClass;

    public PageElementList(By locator, Class<T> tClass) {
        webElements = new ArrayList<>();
        this.locator = locator;
        this.tClass = tClass;
    }

    private List<T> getElements() {
        List<WebElement> list = DriverContainer.getDriver().findElements(locator);
        List<T> pageElementList = new ArrayList<>();

        list.forEach(el -> {
            try {
                pageElementList.add(tClass.getConstructor(WebElement.class, By.class)
                        .newInstance(el, locator));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
               log.error(e.getMessage());
            }
        });
        return pageElementList;
    }

    @Override
    public <S> List<S> map(Function<? super T, ? extends S> mapper) {
        return getElements().stream().map(mapper).collect(Collectors.toList());
    }

    @Override
    public T get(int index) {
        return getElements().get(index);
    }

    @Override
    public List<T> getWithLimit(int limit) {
        return getElements().stream().limit(limit).collect(Collectors.toList());
    }

    @Override
    public PageElementCollection<T> waitUntilVisible() {
        Wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        return this;
    }

    @Override
    public PageElementCollection<T> waitUntilPresent() {
        Wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        return this;
    }

    @Override
    public int size() {
        return getElements().size();
    }

    @Override
    public boolean isEmpty() {
        return getElements().isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return getElements().contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new PageElementIterator();
    }

    private class PageElementIterator implements Iterator<T> {
        Iterator<T> iterator = getElements().iterator();

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
        return getElements().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return getElements().toArray(a);
    }

    @Override
    public boolean add(T element) {
        return webElements.add(element);
    }

    @Override
    public boolean remove(Object o) {
        return getElements().remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return getElements().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return getElements().addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return getElements().removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return getElements().retainAll(c);
    }

    @Override
    public void clear() {
        getElements().clear();
    }
}
