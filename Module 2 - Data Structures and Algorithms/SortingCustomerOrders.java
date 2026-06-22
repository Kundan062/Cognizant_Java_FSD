class CustomerOrder {
    int orderId;
    String customerName;
    double totalPrice;

    CustomerOrder(int orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }
}

public class SortingCustomerOrders {

    static void quickSort(CustomerOrder[] orders, int low, int high) {
        if (low < high) {
            int p = partition(orders, low, high);
            quickSort(orders, low, p - 1);
            quickSort(orders, p + 1, high);
        }
    }

    static int partition(CustomerOrder[] orders, int low, int high) {
        double pivot = orders[high].totalPrice;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (orders[j].totalPrice < pivot) {
                i++;

                CustomerOrder temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }

        CustomerOrder temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {
        CustomerOrder[] orders = {
                new CustomerOrder(1, "A", 5000),
                new CustomerOrder(2, "B", 2000),
                new CustomerOrder(3, "C", 7000)
        };

        quickSort(orders, 0, orders.length - 1);

        for (CustomerOrder order : orders)
            System.out.println(order.totalPrice);
    }
}