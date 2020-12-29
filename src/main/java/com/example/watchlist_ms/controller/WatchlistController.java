package com.example.watchlist_ms.controller;

import com.example.watchlist_ms.model.Watchlist;
import com.example.watchlist_ms.respository.WatchlistRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class WatchlistController {
    @Autowired
    private WatchlistRespository watchlistRespository;

    @GetMapping("/watchlist/all")
    public List<Watchlist> getAll(){
        return watchlistRespository.findAll();
    }

    @GetMapping("/watchlist/{uuid}")
    public Watchlist getOne(@PathVariable UUID uuid){
        return watchlistRespository.findFirstByUuid(uuid.toString());
    }

    @GetMapping("/watchlist/watched")
    public List<Watchlist> getWatchlistWatched(){
        return watchlistRespository.findAllByIsWatched(true);
    }

    @PostMapping("watchlist/{movieUuid}")
    public Watchlist add(@PathVariable UUID movieUuid){
        Watchlist watchlist = new Watchlist(movieUuid);
        watchlistRespository.save(watchlist);
        return watchlist;
    }

    @DeleteMapping("watchlist/{uuid}")
    public String delete(@PathVariable UUID uuid){
        watchlistRespository.deleteWatchlistByMovieUuid(uuid.toString());
        return "Movie successfully removed from watchlist";
    }
}