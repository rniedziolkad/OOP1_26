import java.util.function.Predicate;

public class CustomPredicate<T extends Comparable<T>> implements Predicate<T> {
    private T min;
    private T max;

    public CustomPredicate(T min, T max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean test(T t) {
        return (t.compareTo(max) < 0 && t.compareTo(min) > 0);
    }

    @Override
    public Predicate<T> and(Predicate<? super T> other) {
        return Predicate.super.and(other);
    }

    @Override
    public Predicate<T> negate() {
        return Predicate.super.negate();
    }

    @Override
    public Predicate<T> or(Predicate<? super T> other) {
        return Predicate.super.or(other);
    }
}
