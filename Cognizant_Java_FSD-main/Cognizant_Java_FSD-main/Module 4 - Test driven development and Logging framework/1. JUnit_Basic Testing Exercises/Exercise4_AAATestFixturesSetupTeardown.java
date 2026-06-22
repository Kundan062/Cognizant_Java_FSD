import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Exercise4_AAATestFixturesSetupTeardown {
    private ShoppingCart cart;

    static class ShoppingCart {
        private int itemCount;

        void addItem() {
            itemCount++;
        }

        int getItemCount() {
            return itemCount;
        }

        void clear() {
            itemCount = 0;
        }
    }

    @Before
    public void setUp() {
        cart = new ShoppingCart();
    }

    @After
    public void tearDown() {
        cart.clear();
    }

    @Test
    public void testAddItemUsingAAA() {
        cart.addItem();

        int count = cart.getItemCount();

        assertEquals(1, count);
    }
}
