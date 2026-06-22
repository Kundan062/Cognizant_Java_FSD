class Computer {
    private String cpu;
    private int ram;
    private int storage;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
    }

    public void show() {
        System.out.println(cpu + " " + ram + "GB " + storage + "GB");
    }

    static class Builder {
        private String cpu;
        private int ram;
        private int storage;

        public Builder setCpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder setRam(int ram) {
            this.ram = ram;
            return this;
        }

        public Builder setStorage(int storage) {
            this.storage = storage;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}

public class BuilderPattern {
    public static void main(String[] args) {
        Computer pc = new Computer.Builder()
                .setCpu("i7")
                .setRam(16)
                .setStorage(512)
                .build();

        pc.show();
    }
}