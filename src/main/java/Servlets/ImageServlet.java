package Servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;


@WebServlet("/ver-imagen")
public class ImageServlet extends HttpServlet {

    // Ruta donde guarda la imágenes subidas por los usuarios
    private final String DIRECTO_IMAGENES = "/home/josue/Descargas";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombreImagen = request.getParameter("nombre");
        if (nombreImagen == null || nombreImagen.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Nombre de imagen no provisto");
            return;
        }

        File archivoImagen = new File(DIRECTO_IMAGENES, nombreImagen);

        if (!archivoImagen.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Imagen no encontrada");
            return;
        }

        String tipoContenido = getServletContext().getMimeType(archivoImagen.getName());
        if (tipoContenido == null) {
            tipoContenido = "application/octet-stream";
        }
        response.setContentType(tipoContenido);
        response.setContentLength((int) archivoImagen.length());

        try (FileInputStream fileInputStream = new FileInputStream(archivoImagen);
             OutputStream out = response.getOutputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}