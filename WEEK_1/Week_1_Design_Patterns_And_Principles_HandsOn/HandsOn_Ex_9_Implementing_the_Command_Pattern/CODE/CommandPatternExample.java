public class CommandPatternExample 
{
    interface Command {
        void execute();
    }

    static class Light 
    {
        public void turnOn() 
        {
            System.out.println("Light is ON");
        }
        public void turnOff() 
        {
            System.out.println("Light is OFF");
        }
    }

    static class LightOnCommand implements Command 
    {
        private Light light;

        public LightOnCommand(Light light) 
        {
            this.light = light;
        }

        @Override
        public void execute() 
        {
            light.turnOn();
        }
    }

    static class LightOffCommand implements Command 
    {
        private Light light;

        public LightOffCommand(Light light) 
        {
            this.light = light;
        }

        @Override
        public void execute() 
        {
            light.turnOff();
        }
    }

    static class RemoteControl 
    {
        private Command command;

        public void setCommand(Command command) 
        {
            this.command = command;
        }

        public void pressButton() 
        {
            if (command != null) 
            {
                command.execute();
            } 
            else 
            {
                System.out.println("No command set!");
            }
        }
    }

    public static void main(String[] args) 
    {
        Light livingRoomLight = new Light();

        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);

        RemoteControl remote = new RemoteControl();

        System.out.println("== Pressing ON button ==");
        remote.setCommand(lightOn);
        remote.pressButton();

        System.out.println("\n== Pressing OFF button ==");
        remote.setCommand(lightOff);
        remote.pressButton();
    }
}


