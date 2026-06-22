class TaskNode {
    int taskId;
    String taskName;
    String status;
    TaskNode next;

    TaskNode(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }
}

public class TaskManagementSystem {

    TaskNode head;

    void add(TaskNode task) {
        task.next = head;
        head = task;
    }

    TaskNode search(int id) {
        TaskNode temp = head;

        while (temp != null) {
            if (temp.taskId == id)
                return temp;

            temp = temp.next;
        }

        return null;
    }

    void delete(int id) {
        if (head == null)
            return;

        if (head.taskId == id) {
            head = head.next;
            return;
        }

        TaskNode temp = head;

        while (temp.next != null) {
            if (temp.next.taskId == id) {
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        TaskManagementSystem tms = new TaskManagementSystem();

        tms.add(new TaskNode(1, "Coding", "Pending"));
        tms.add(new TaskNode(2, "Testing", "Done"));

        System.out.println(tms.search(1).taskName);
    }
}