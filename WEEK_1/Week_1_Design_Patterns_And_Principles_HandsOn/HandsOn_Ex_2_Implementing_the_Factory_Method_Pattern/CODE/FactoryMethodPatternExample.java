public class FactoryMethodPatternExample
{
    interface Document
    {
        void show();
    }

    static class WordDocument implements Document
    {
        public void show()
        {
            System.out.println("Word document is opened.");
        }
    }

    static class PdfDocument implements Document
    {
        public void show()
        {
            System.out.println("Pdf document is opened.");
        }
    }

    static class ExcelDocument implements Document
    {
        public void show()
        {
            System.out.println("Excel document is opened.");
        }
    }

    static abstract class DocumentFactory
    {
        public abstract Document createDocument();
    }

    static class WordDocumentFactory extends DocumentFactory
    {
        public Document createDocument()
        {
            return new WordDocument();
        }
    }

    static class PdfDocumentFactory extends DocumentFactory
    {
        public Document createDocument()
        {
            return new PdfDocument();
        }
    }

    static class ExcelDocumentFactory extends DocumentFactory
    {
        public Document createDocument()
        {
            return new ExcelDocument();
        }
    }

    public static void main(String args[])
    {
        DocumentFactory wordFactory = new WordDocumentFactory();
        Document wordFile = wordFactory.createDocument();
        wordFile.show();

        DocumentFactory pdfFactory = new PdfDocumentFactory();
        Document pdfFile = pdfFactory.createDocument();
        pdfFile.show();

        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excelFile = excelFactory.createDocument();
        excelFile.show();
    }
}

