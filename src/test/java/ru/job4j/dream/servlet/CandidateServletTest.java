package ru.job4j.dream.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.dream.model.Candidate;
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
public class CandidateServletTest {

    @Test
    public void whenCreateCandidate() throws IOException {
        Store store = MemStore.instOf();

        PowerMockito.mockStatic(PsqlStore.class);
        PowerMockito.when(PsqlStore.instOf()).thenReturn(store);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        Candidate testCandidate = new Candidate(12, "mock_candidate");
        PowerMockito.when(req.getParameter("id")).thenReturn(String.valueOf(testCandidate.getId()));
        PowerMockito.when(req.getParameter("name")).thenReturn(testCandidate.getName());

        new CandidateServlet().doPost(req, resp);

        Candidate result = store.findCandidateById(testCandidate.getId());
        assertEquals(result.getName(), testCandidate.getName());
    }
}

