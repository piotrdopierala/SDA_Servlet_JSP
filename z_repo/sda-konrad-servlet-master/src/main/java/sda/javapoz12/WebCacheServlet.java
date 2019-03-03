package sda.javapoz12;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class WebCacheServlet extends HttpServlet {

    private LoadingCache<String, String> cache
            = CacheBuilder
            .newBuilder()
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .maximumSize(1000)
            .recordStats()
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return WebCacheServlet.this.load(key);
                }
            });

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String pathInfo = req.getPathInfo();
        try {
            String page = cache.get(pathInfo.substring(1));
            if (page == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            try (ServletOutputStream outputStream = resp.getOutputStream()) {
                resp.setStatus(HttpServletResponse.SC_OK);
                outputStream.println(page);
            }
        } catch (ExecutionException e) {
            throw new ServletException(e);
        }
    }

    private String load(String key) throws IOException {
        final HttpClient client = HttpClients.createDefault();
        final HttpGet get = new HttpGet("http://" + key);
        final HttpResponse execute = client.execute(get);
        final InputStream content = execute.getEntity().getContent();
        return new Scanner(content, "UTF8").useDelimiter("\\A").next();
    }
}
