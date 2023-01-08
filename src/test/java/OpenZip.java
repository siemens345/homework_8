import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class OpenZip {
    InputStream getFile (String archiveName, String fileName) throws Exception {
        ZipFile archive = new ZipFile(new File("src/test/resources/"+archiveName));
        InputStream is = archive.getInputStream(archive.getEntry(fileName));
        return is;
    }
}
