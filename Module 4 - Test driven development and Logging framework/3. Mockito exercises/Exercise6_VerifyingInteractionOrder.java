import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

public class Exercise6_VerifyingInteractionOrder {
    interface Workflow {
        void start();

        void process();

        void finish();
    }

    @Test
    void testInteractionOrder() {
        Workflow workflow = mock(Workflow.class);

        workflow.start();
        workflow.process();
        workflow.finish();

        InOrder order = inOrder(workflow);
        order.verify(workflow).start();
        order.verify(workflow).process();
        order.verify(workflow).finish();
    }
}
