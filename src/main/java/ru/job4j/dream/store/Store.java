package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.util.Collection;

public interface Store {
    Collection<Post> findAllPosts();

    Collection<Candidate> findAllCandidates();

    Collection<User> findAllUsers();

    User save(User user);

    User findUserById(int id);

    User findUserByEmail(String email);

    Post save(Post post);

    Candidate save(Candidate post);

    Post findPostById(int id);

    Candidate findCandidateById(int id);

    void deleteCandidateById(int id);
}
