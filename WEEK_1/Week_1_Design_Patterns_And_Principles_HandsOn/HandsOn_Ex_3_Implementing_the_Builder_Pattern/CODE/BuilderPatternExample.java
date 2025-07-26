public class BuilderPatternExample 
{
    static class Computer 
    {
        private final String CPU;
        private final String RAM;
        private final String storage;
        private final String graphicsCard;
        private final String keyboard;

        private Computer(Builder builder) 
        {
            this.CPU = builder.CPU;
            this.RAM = builder.RAM;
            this.storage = builder.storage;
            this.graphicsCard = builder.graphicsCard;
            this.keyboard = builder.keyboard;
        }

        public static class Builder 
        {
            private String CPU;
            private String RAM;
            private String storage;
            private String graphicsCard;
            private String keyboard;

            public Builder setCPU(String CPU) 
            {
                this.CPU = CPU;
                return this;
            }

            public Builder setRAM(String RAM) 
            {
                this.RAM = RAM;
                return this;
            }

            public Builder setStorage(String storage) 
            {
                this.storage = storage;
                return this;
            }

            public Builder setGraphicsCard(String graphicsCard) 
            {
                this.graphicsCard = graphicsCard;
                return this;
            }

            public Builder setKeyboard(String keyboard) 
            {
                this.keyboard = keyboard;
                return this;
            }

            public Computer build() 
            {
                return new Computer(this);
            }
        }

        @Override
        public String toString() 
        {
            return "Computer Configuration:\n" +
                   "CPU: " + CPU + "\n" +
                   "RAM: " + RAM + "\n" +
                   "Storage: " + storage + "\n" +
                   "Graphics Card: " + graphicsCard + "\n" +
                   "Keyboard: " + keyboard;
        }
    }

    public static void main(String[] args) 
    {
        Computer basicComputer = new Computer.Builder()
                .setCPU("Intel i5")
                .setRAM("8GB")
                .setStorage("256GB SSD")
                .build();

        Computer gamingComputer = new Computer.Builder()
                .setCPU("AMD Ryzen 9")
                .setRAM("32GB")
                .setStorage("1TB SSD")
                .setGraphicsCard("NVIDIA RTX 4080")
                .setKeyboard("Mechanical RGB Keyboard")
                .build();

        System.out.println("Basic Computer:\n" + basicComputer);
        System.out.println("\n----------------------------\n");
        System.out.println("Gaming Computer:\n" + gamingComputer);
    }
}


