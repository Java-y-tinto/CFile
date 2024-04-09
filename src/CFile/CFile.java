package CFile;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import java.util.List;
import java.util.function.Consumer;

public class CFile {
    public static <arg> void readFileFromPathString(String path, Consumer<arg> callback){
        Path file = Paths.get(path);
        boolean success;
        String fileContent;
        success = Files.exists(file);
        System.out.println(success);
        System.out.println(file.toAbsolutePath().toString());

        if (success){
            try {
                fileContent = Files.readString(file);
                callback.accept((arg) fileContent);
            } catch (IOException e){
                System.out.println("Couldn't read file.");
                System.out.println(e.getMessage());
                callback.accept(null);
            }
        } else{
            callback.accept(null );
        }
    }

    public static <arg> void readFileFromPath(Path path, Consumer<arg> callback) throws IOException {
        boolean success;
        String fileContent;
        success = Files.exists(path);
        System.out.println(success);
        if (success){
            try {
                fileContent = Files.readString(path);
                callback.accept((arg) fileContent);
            } catch (IOException e){
                throw e;
            }
        } else{
            callback.accept(null );
        }
    }
    public static <arg> void getFilesFromDirectoryPathString(String path,Consumer<arg> callback) throws IOException {
        Path directory = Paths.get(path);
        List<Path> filesInDirectory = new ArrayList<Path>();
        if (Files.isDirectory(directory)){
            try(DirectoryStream<Path> files = Files.newDirectoryStream(directory)) {
                for(Path file:files){
                    filesInDirectory.add(file.toAbsolutePath());                }
            } catch (DirectoryIteratorException ex){
                throw ex.getCause();
            }
            callback.accept((arg) filesInDirectory);
        } else {
            callback.accept(null);
        }
    }
    public static <arg> void getFilesFromDirectory(Path path,Consumer<arg> callback) throws IOException {

        List<Path> filesInDirectory = new ArrayList<Path>();
        if (Files.isDirectory(path)){
            try(DirectoryStream<Path> files = Files.newDirectoryStream(path)) {
                for(Path file:files){
                    filesInDirectory.add(file.toAbsolutePath());                }
            } catch (DirectoryIteratorException ex){
                throw ex.getCause();
            }
            callback.accept((arg) filesInDirectory);
        } else {
            callback.accept(null);
        }
    }
    public static <arg> void getFilesFromDirectory(Path path,String searchMethod,Consumer<arg> callback) throws IOException {

        List<Path> filesInDirectory = new ArrayList<Path>();
        if (Files.isDirectory(path)){
            try(DirectoryStream<Path> files = Files.newDirectoryStream(path,searchMethod)) {
                for(Path file:files){
                    filesInDirectory.add(file.toAbsolutePath());                }
            } catch (DirectoryIteratorException ex){
                throw ex.getCause();
            }
            callback.accept((arg) filesInDirectory);
        } else {
            callback.accept(null);
        }
    }
    public static <arg> void getFilesFromDirectory(Path path,long fileSize,Consumer<arg> callback) throws IOException {

        List<Path> filesInDirectory = new ArrayList<Path>();
        if (Files.isDirectory(path)){
            try(DirectoryStream<Path> files = Files.newDirectoryStream(path)) {
                for(Path file:files) {
                    if (Files.size(file) <= fileSize) {
                        filesInDirectory.add(file.toAbsolutePath());
                    }
                }
            } catch (DirectoryIteratorException ex){
                throw ex.getCause();
            }
            callback.accept((arg) filesInDirectory);
        } else {
            callback.accept(null);
        }
    }
    public static <arg> void getFilesFromDirectory(Path path, LocalDateTime creationTime, Consumer<arg> callback) throws IOException {

        List<Path> filesInDirectory = new ArrayList<Path>();
        if (Files.isDirectory(path)){
            try(DirectoryStream<Path> files = Files.newDirectoryStream(path)) {
                for(Path file:files) {
                    LocalDateTime fileCreationTime = LocalDateTime.ofInstant(Files.readAttributes(file, BasicFileAttributes.class).creationTime().toInstant(), ZoneId.systemDefault());
                    if (fileCreationTime.equals(creationTime) || fileCreationTime.isBefore(creationTime) ) {
                        filesInDirectory.add(file.toAbsolutePath());
                    }
                }
            } catch (DirectoryIteratorException ex){
                throw ex.getCause();
            }
            callback.accept((arg) filesInDirectory);
        } else {
            callback.accept(null);
        }
    }
    public static <arg> void getFilesFromDirectoryFromModifiedTime(Path path, LocalDateTime updatedTime, Consumer<arg> callback) throws IOException {

        List<Path> filesInDirectory = new ArrayList<Path>();
        if (Files.isDirectory(path)){
            try(DirectoryStream<Path> files = Files.newDirectoryStream(path)) {
                for(Path file:files) {
                    LocalDateTime fileCreationTime = LocalDateTime.ofInstant(Files.readAttributes(file, BasicFileAttributes.class).lastModifiedTime().toInstant(), ZoneId.systemDefault());
                    if (fileCreationTime.equals(updatedTime) || fileCreationTime.isBefore(updatedTime) ) {
                        filesInDirectory.add(file.toAbsolutePath());
                    }
                }
            } catch (DirectoryIteratorException ex){
                throw ex.getCause();
            }
            callback.accept((arg) filesInDirectory);
        } else {
            callback.accept(null);
        }
    }
    public static void overwriteFile(Path path,String fileContent) throws IOException{
        try(BufferedWriter escritura = Files.newBufferedWriter(path)){
            escritura.write(fileContent);
        }
    }
    public static void overwriteFile(String path,String fileContent) throws IOException{
        Path file = Paths.get(path);
        try(BufferedWriter escritura = Files.newBufferedWriter(file)){
            escritura.write(fileContent);
        }
    }
    public static void appendFile(String path,String fileContent) throws IOException{
        Path file = Paths.get(path);
        try(BufferedWriter escritura = Files.newBufferedWriter(file,StandardOpenOption.APPEND)){
            escritura.write(fileContent);
        }
    }
    public static void appendFile(Path path,String fileContent) throws IOException{
        try(BufferedWriter escritura = Files.newBufferedWriter(path,StandardOpenOption.APPEND)){
            escritura.write(fileContent);
        }
    }

    public static FileAttributes getFileInfo(Path path) throws Exception{
        if (!Files.isRegularFile(path)){
            throw new Exception("Regular file expected");
        }
        BasicFileAttributes fileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
       return new FileAttributes(path);
    }
    public static FileAttributes getFileInfo(String path) throws Exception{
        Path file = Paths.get(path);
        if (!Files.isRegularFile(file)){
            throw new Exception("Regular file expected");
        }
        BasicFileAttributes fileAttributes = Files.readAttributes(file, BasicFileAttributes.class);
        return new FileAttributes(file);
    }
    public static void createDirectory(Path path) throws Exception{
        if (!Files.isDirectory(path)){
            throw new Exception("Directory expected");
        }
        Files.createDirectory(path);
    }
    public static void createDirectory(String path) throws Exception{
        Path directory = Paths.get(path);
        if (!Files.isDirectory(directory)){
            throw new Exception("Directory expected");
        }
        Files.createDirectory(directory);
    }
    public static void deleteDirectory(Path path) throws Exception{
        if (!Files.isDirectory(path)){
            throw new Exception("Directory expected");
        }
        Files.delete(path);
    }
    public static void deleteDirectory(String path) throws Exception{
        Path directory = Paths.get(path);
        if (!Files.isDirectory(directory)){
            throw new Exception("Directory expected");
        }
        Files.delete(directory);
    }
    public  static void copy(Path source,Path target) throws IOException{
        Files.copy(source,target,StandardCopyOption.REPLACE_EXISTING);
    }
    public  static void copy(String source,String target) throws IOException{
        Path fSource = Paths.get(source);
        Path fTarget = Paths.get(target);
        Files.copy(fSource,fTarget,StandardCopyOption.REPLACE_EXISTING);
    }

    public  static Path move(Path source,Path target) throws IOException{
        return Files.move(source,target,StandardCopyOption.REPLACE_EXISTING);
    }
    public  static Path move(String source,String target) throws IOException{
        Path fSource = Paths.get(source);
        Path fTarget = Paths.get(target);
        return Files.move(fSource,fTarget,StandardCopyOption.REPLACE_EXISTING);
    }
}
