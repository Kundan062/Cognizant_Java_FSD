import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class Exercise3_MockingFileIO {
    interface FileReader {
        String read();
    }

    interface FileWriter {
        void write(String content);
    }

    static class FileService {
        private final FileReader reader;
        private final FileWriter writer;

        FileService(FileReader reader, FileWriter writer) {
            this.reader = reader;
            this.writer = writer;
        }

        String processFile() {
            String processed = "Processed " + reader.read();
            writer.write(processed);
            return processed;
        }
    }

    @Test
    void testServiceWithMockFileIO() {
        FileReader mockFileReader = mock(FileReader.class);
        FileWriter mockFileWriter = mock(FileWriter.class);
        when(mockFileReader.read()).thenReturn("Mock File Content");
        FileService fileService = new FileService(mockFileReader, mockFileWriter);

        String result = fileService.processFile();

        assertEquals("Processed Mock File Content", result);
        verify(mockFileWriter).write("Processed Mock File Content");
    }
}
