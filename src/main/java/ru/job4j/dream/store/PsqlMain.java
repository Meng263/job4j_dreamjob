package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

public class PsqlMain {
    public static void main(String[] args) {
        Store store = PsqlStore.instOf();
        store.save(new Post("Java Job Junior"));
        Post javaJobMiddle = store.save(new Post("Java Job Middle"));
        store.save(new Post("Java Job Senior"));

        store.findAllPosts().forEach(post -> System.out.println(post.toString()));

        System.out.println("fond post by id " + store.findPostById(javaJobMiddle.getId()));

        store.save(new Candidate("Jorra"));
        Candidate ivan = store.save(new Candidate("Ivan"));
        Candidate goro = store.save(new Candidate("Goro"));
        store.save(new Candidate(goro.getId(), "goro_new"));

        store.findAllCandidates().forEach(candidate -> System.out.println(candidate.toString()));

        System.out.println("fond candidate by id " + store.findCandidateById(ivan.getId()));
    }
}
