import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileObject extends File {
    String name;
    Long size;
    String md5;
    File f;

    public FileObject(String path) {
        super(path);
        this.f = new File(path);
        this.name = f.getName();
        this.size = f.length();

    }

    public String get_hash() {
        System.out.println(this.name);
        try (InputStream is = Files.newInputStream(Paths.get(String.valueOf(f)))) {
            md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(is);
            return md5;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
