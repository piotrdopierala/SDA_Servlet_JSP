package sda.javapoz12;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleServlet extends HttpServlet {
    private Path tempdir;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        File directory = getDirectory().orElseThrow(() -> new IllegalStateException("Cannot create temporary directory."));
        String[] split = pathInfo.split("/");
        List<String> pathElements =
                Stream
                        .of(split)
                        .filter(str -> str != null && !str.trim().isEmpty())
                        .collect(Collectors.toList());
        Path path = Paths.get(directory.getPath(), pathElements.toArray(new String[0]));
        File file = path.toFile();
        file.createNewFile();
        try (ServletInputStream inputStream = req.getInputStream()) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                final byte[] buffor = new byte[1024];
                while ((inputStream.read(buffor)) != -1) {
                    fileOutputStream.write(buffor);
                }
            }
        }

    }

    private Optional<File> getDirectory() {
        if (tempdir == null) {
            try {
                tempdir = Files.createTempDirectory("test");
            } catch (IOException e) {
                Logger.getAnonymousLogger().severe("error");
            }
        }
        return Optional.ofNullable(tempdir).map(Path::toFile);
    }
}
