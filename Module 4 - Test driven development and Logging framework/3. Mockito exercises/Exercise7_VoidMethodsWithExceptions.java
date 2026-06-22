import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

public class Exercise7_VoidMethodsWithExceptions {
    interface FileCleaner {
        void clean();
    }

    @Test
    void testVoidMethodThrowsException() {
        FileCleaner cleaner = mock(FileCleaner.class);
        doThrow(new IllegalStateException("Cleanup failed")).when(cleaner).clean();

        assertThrows(IllegalStateException.class, cleaner::clean);

        verify(cleaner).clean();
    }
}
