package XMLDAO.Compress;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Created by Alexey on 16.04.2017.
 */
public class Compressor {
    public void compress(String filePath, String archivePath, int capacity) {
        try {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(archivePath));
            //out.setLevel(capacity);
            File file = new File(filePath);
            if (file.exists()) {
                out.putNextEntry(new ZipEntry(file.getPath()));
                write(new FileInputStream(file), out);
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void write(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) >= 0)
            outputStream.write(buffer, 0, length);
        inputStream.close();
    }

    public void decompress(String archivePath,String filePath) {
        File file = new File(archivePath);
        if (!file.exists() || !file.canRead()) {
            System.out.println("File cannot be read");
            return;
        }
        try {
            ZipFile zip = new ZipFile(archivePath);
            Enumeration entries = zip.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                System.out.println(entry.getName());

                if (entry.isDirectory()) {
                    new File(file.getParent(), entry.getName()).mkdirs();
                } else {
                    dewrite(zip.getInputStream(entry),
                            new BufferedOutputStream(new FileOutputStream(
                                    new File(file.getParent(), entry.getName()))));
                }
            }
            zip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void dewrite(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) >= 0)
            out.write(buffer, 0, len);
        out.close();
        in.close();
    }
}
