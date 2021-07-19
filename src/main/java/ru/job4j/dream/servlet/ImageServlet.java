package ru.job4j.dream.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.dream.service.CandidateImageService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ImageServlet extends HttpServlet {
    private final CandidateImageService service = CandidateImageService.instOf();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        resp.setContentType("image/png");
        byte[] image = service.getImageOrNull(name);
        if (image != null) {
            try (ServletOutputStream outputStream = resp.getOutputStream()) {
                outputStream.write(image);
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        try {
            List<FileItem> items = upload.parseRequest(req);
            if (items.size() == 1) {
                service.saveImage(name, items.get(0).getInputStream());
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        service.deleteImage(name);
    }
}
