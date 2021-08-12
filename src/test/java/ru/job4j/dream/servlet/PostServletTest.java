package ru.job4j.dream.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.MemStore;
import ru.job4j.dream.store.PsqlStore;
import ru.job4j.dream.store.Store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)
public class PostServletTest {

    @Test
    public void whenCreatePost() throws IOException {
        Store store = MemStore.instOf();

        PowerMockito.mockStatic(PsqlStore.class);
        PowerMockito.when(PsqlStore.instOf()).thenReturn(store);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        Post testPost = new Post(12, "mock_name", "mock_description");

        PowerMockito.when(req.getParameter("id")).thenReturn(String.valueOf(testPost.getId()));
        PowerMockito.when(req.getParameter("name")).thenReturn(testPost.getName());
        PowerMockito.when(req.getParameter("description")).thenReturn(testPost.getDescription());

        new PostServlet().doPost(req, resp);

        Post result = store.findPostById(testPost.getId());
        assertEquals(result.getName(), testPost.getName());
        assertEquals(result.getDescription(), testPost.getDescription());
    }
}