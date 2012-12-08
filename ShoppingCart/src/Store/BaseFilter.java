package Store;

import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * @author volen
 */
// Implemented filter logic using the Strategy, Composite and Factory Design Patterns
public abstract class BaseFilter {

    // This is what is displayed on the screen when a filter is added
    @Override
    public abstract String toString();

    public abstract Boolean isMatch(String color, ProductSize size, String query);

    public BaseFilter combineFilter(BaseFilter filter) {
        if (this instanceof AggregateFilter) {
            AggregateFilter left = (AggregateFilter) this;
            return left.combine(filter);
        } else {
            AggregateFilter left = new AggregateFilter(this);
            return left.combine(filter);
        }
    }

    public static BaseFilter CreateQueryFilter(String query) {
        assert (query != null);

        return new QueryFilter(query);
    }

    public static BaseFilter createColorFilter(String color) {
        assert (color != null);
        return new ColorFilter(color);
    }

    public static BaseFilter createSizeFilter(ProductSize size) {
        return new SizeFilter(size);
    }

    private static class QueryFilter extends BaseFilter {

        public final String Query;

        QueryFilter(String query) {
            Query = query;
        }

        @Override
        public String toString() {
            return String.format("All products containing: %s");
        }

        @Override
        public Boolean isMatch(String color, ProductSize size, String query) {
            if (query == null) {
                return false;
            }
            return query.contains(Query);
        }
    }

    private static class ColorFilter extends BaseFilter {

        public final String ProductColor;

        ColorFilter(String color) {
            ProductColor = color;
        }

        @Override
        public String toString() {
            return String.format("All products with color: %s", ProductColor.toString());
        }

        @Override
        public Boolean isMatch(String color, ProductSize size, String query) {
            return color.equals(ProductColor);
        }
    }

    private static class SizeFilter extends BaseFilter {

        public final ProductSize ProductSize;

        public SizeFilter(ProductSize size) {
            ProductSize = size;
        }

        @Override
        public String toString() {
            return String.format("All products with size: %s", ProductSize);
        }

        @Override
        public Boolean isMatch(String color, ProductSize size, String query) {
            if (ProductSize == ProductSize.All) {
                return true;
            }
            return ProductSize == size;
        }
    }

    private static class AggregateFilter extends BaseFilter {

        private final HashSet<BaseFilter> mSubFilters;

        public AggregateFilter(BaseFilter firstFilter) {
            mSubFilters = new HashSet<>();
            mSubFilters.add(firstFilter);
        }

        public BaseFilter combine(BaseFilter filter) {
            mSubFilters.add(filter);
            return this;
        }

        @Override
        public String toString() {
            return Arrays.toString(mSubFilters.toArray());
        }

        @Override
        public Boolean isMatch(String color, ProductSize size, String query) {
            for (BaseFilter filter : mSubFilters) {
                if (filter.isMatch(color, size, query)) {
                    return true;
                }
            }
            return false;
        }
    }
}