package com.omellete.watchlistlive;

import static com.omellete.watchlistlive.data.WatchlistEntity.TYPE_MOVIE;
import static com.omellete.watchlistlive.data.WatchlistEntity.TYPE_SHOW;

import com.omellete.watchlistlive.data.WatchlistEntity;

public class LocalData {

    public static WatchlistEntity getDummyMovie() {
        return new WatchlistEntity(
                370172,
                "/iUgygt3fscRoKWCV1d0C7FbM9TP.jpg",
                "/u5Fp9GBy9W8fqkuGfLBuuoJf57Z.jpg",
                "No Time to Die",
                "No Time to Die",
                TYPE_MOVIE,
                "Adventure, Action, Thriller",
                "Bond has left active service and is enjoying a tranquil life in Jamaica. His peace is short-lived when his old friend Felix Leiter from the CIA turns up asking for help. The mission to rescue a kidnapped scientist turns out to be far more treacherous than expected, leading Bond onto the trail of a mysterious villain armed with dangerous new technology.",
                "2021 ",
                "74%"
        );
    }

    public static WatchlistEntity getDummyShows() {
        return new WatchlistEntity(
                93405,
                "/dDlEmu3EZ0Pgg93K2SVNLCjCSvE.jpg",
                "/qw3J9cNeLioOLoR68WX7z79aCdK.jpg",
                "오징어 게임",
                "Squid Game",
                TYPE_SHOW,
                "Action & Adventure, Mystery, Drama",
                "Hundreds of cash-strapped players accept a strange invitation to compete in children's games—with high stakes. But, a tempting prize awaits the victor.",
                "2021 ",
                "78%"
        );
    }
}
