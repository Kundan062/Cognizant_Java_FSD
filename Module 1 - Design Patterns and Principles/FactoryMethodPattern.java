interface Document {
    void open();
}

class WordDocument implements Document {
    public void open() {
        System.out.println("Word Document");
    }
}

class PdfDocument implements Document {
    public void open() {
        System.out.println("PDF Document");
    }
}

class ExcelDocument implements Document {
    public void open() {
        System.out.println("Excel Document");
    }
}

abstract class DocumentFactory {
    abstract Document createDocument();
}

class WordFactory extends DocumentFactory {
    Document createDocument() {
        return new WordDocument();
    }
}

class PdfFactory extends DocumentFactory {
    Document createDocument() {
        return new PdfDocument();
    }
}

class ExcelFactory extends DocumentFactory {
    Document createDocument() {
        return new ExcelDocument();
    }
}

public class FactoryMethodPattern {
    public static void main(String[] args) {
        DocumentFactory factory = new PdfFactory();
        Document doc = factory.createDocument();
        doc.open();
    }
}