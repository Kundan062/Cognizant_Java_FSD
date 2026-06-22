class SearchProduct {
    int productId;
    String productName;
    String category;

    SearchProduct(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }
}

public class EcommerceSearchFunction {

    static int linearSearch(SearchProduct[] products, String name) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].productName.equals(name))
                return i;
        }
        return -1;
    }

    static int binarySearch(SearchProduct[] products, String name) {
        int low = 0;
        int high = products.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            int result = products[mid].productName.compareTo(name);

            if (result == 0)
                return mid;
            else if (result < 0)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return -1;
    }

    public static void main(String[] args) {
        SearchProduct[] products = {
                new SearchProduct(1, "Apple", "Fruit"),
                new SearchProduct(2, "Banana", "Fruit"),
                new SearchProduct(3, "Mango", "Fruit")
        };

        System.out.println(linearSearch(products, "Mango"));
        System.out.println(binarySearch(products, "Mango"));
    }
}