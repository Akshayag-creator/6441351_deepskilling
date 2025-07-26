public class ProxyPatternExample 
{
    interface Image 
    {
        void display();
    }

    static class RealImage implements Image 
    {
        private String filename;

        public RealImage(String filename) 
        {
            this.filename = filename;
            loadFromRemoteServer();
        }

        private void loadFromRemoteServer() 
        {
            System.out.println("Loading image from remote server: " + filename);
            try 
            {
                Thread.sleep(1000);
            } catch (InterruptedException e) 
            {
                System.out.println("Loading interrupted");
            }
        }

        @Override
        public void display() 
        {
            System.out.println("Displaying image: " + filename);
        }
    }

    static class ProxyImage implements Image 
    {
        private String filename;
        private RealImage realImage;

        public ProxyImage(String filename) 
        {
            this.filename = filename;
        }

        @Override
        public void display() 
        {
            if (realImage == null) 
            {
                realImage = new RealImage(filename); 
            } else 
            {
                System.out.println("Image already loaded (cached): " + filename);
            }
            realImage.display();
        }
    }

    public static void main(String[] args) {
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");

        System.out.println("== First time displaying photo1 ==");
        image1.display();  

        System.out.println("\n== Displaying photo1 again ==");
        image1.display();  

        System.out.println("\n== First time displaying photo2 ==");
        image2.display();  
    }
}


