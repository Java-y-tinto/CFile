# **JFile - Manipulación de Archivos en Java**

JFile es una biblioteca de Java que proporciona una serie de funciones para facilitar la manipulación y gestión de archivos y directorios en aplicaciones Java. Esta biblioteca ofrece métodos simples y eficientes para leer y escribir archivos, obtener información sobre archivos y directorios, así como crear, copiar y eliminar archivos y directorios.

## **Características**

Lectura y escritura de archivos de manera sencilla y eficiente.
Obtención de información detallada sobre archivos y directorios, como tamaño, fecha de creación y fecha de modificación.
Funciones para trabajar con directorios, como crear, copiar y eliminar directorios.
Soporte para manipulación de archivos basada en rutas de archivos y objetos Path o String.

## **Uso**
Para utilizar JFile en tu proyecto, simplemente agrega la biblioteca al classpath de tu aplicación y comienza a usar sus funciones según sea necesario. A continuación, se muestra un ejemplo de cómo usar algunas de las funciones de JFile:

```java
Copy code
import CFile.*;

public class Main {
    public static void main(String[] args) {
        // Leer el contenido de un archivo
        JFile.readFileFromPathString("archivo.txt", content -> {
            if (content != null) {
                System.out.println("Contenido del archivo:");
                System.out.println(content);
            } else {
                System.out.println("No se pudo leer el archivo.");
            }
        });

        // Obtener información sobre un archivo
        try {
            FileAttributes fileInfo = JFile.getFileInfo("archivo.txt");
            System.out.println("Tamaño del archivo: " + fileInfo.getSize() + " bytes");
            System.out.println("Fecha de creación: " + fileInfo.getCreationTime());
        } catch (Exception e) {
            System.out.println("Error al obtener información del archivo: " + e.getMessage());
        }
    }
}
```
