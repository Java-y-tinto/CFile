package CFile;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;

public class FileAttributes {
    private final Path path;
    private final BasicFileAttributes attrs;

    public FileAttributes(Path path) throws IOException {
        this.path = path;
        this.attrs = Files.readAttributes(path, BasicFileAttributes.class);
    }

    public Path getPath() {
        return path;
    }

    public boolean isRegularFile() {
        return attrs.isRegularFile();
    }

    public boolean isDirectory() {
        return attrs.isDirectory();
    }

    public boolean isSymbolicLink() {
        return attrs.isSymbolicLink();
    }

    public long getSize() {
        return attrs.size();
    }

    public FileTime getCreationTime() {
        return attrs.creationTime();
    }

    public FileTime getLastAccessTime() {
        return attrs.lastAccessTime();
    }

    public FileTime getLastModifiedTime() {
        return attrs.lastModifiedTime();
    }
}
