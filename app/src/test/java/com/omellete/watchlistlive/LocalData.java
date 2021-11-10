package com.omellete.watchlistlive;

import static com.omellete.watchlistlive.data.WatchlistEntity.TYPE_MOVIE;
import static com.omellete.watchlistlive.data.WatchlistEntity.TYPE_SHOW;

import com.omellete.watchlistlive.api.WatchlistResponse;
import com.omellete.watchlistlive.data.WatchlistEntity;

import java.util.ArrayList;

public class LocalData {

    public static ArrayList<WatchlistEntity> getDummyMovies() {
        ArrayList<WatchlistEntity> movies = new ArrayList<>();

        movies.add(new WatchlistEntity(
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
        ));
        movies.add(new WatchlistEntity(
                438631,
                "/d5NXSklXo0qyIYkgV94XAgMIckC.jpg",
                "/eeijXm3553xvuFbkPFkDG6CLCbQ.jpg",
                "Dune",
                "Dune",
                TYPE_MOVIE,
                "Action, Adventure, Science Fiction",
                "Paul Atreides, a brilliant and gifted young man born into a great destiny beyond his understanding, must travel to the most dangerous planet in the universe to ensure the future of his family and his people. As malevolent forces explode into conflict over the planet's exclusive supply of the most precious resource in existence-a commodity capable of unlocking humanity's greatest potential-only those who can conquer their fear will survive.",
                "2021 ",
                "80%"
        ));
        movies.add(new WatchlistEntity(
                580489,
                "/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg",
                "/lNyLSOKMMeUPr1RsL4KcRuIXwHt.jpg",
                "Venom: Let There Be Carnage",
                "Venom: Let There Be Carnage",
                TYPE_MOVIE,
                "Science Fiction, Action",
                "After finding a host body in investigative reporter Eddie Brock, the alien symbiote must face a new enemy, Carnage, the alter ego of serial killer Cletus Kasady.",
                "2021 ",
                "68%"
        ));

        return movies;
    }

    public static ArrayList<WatchlistEntity> getDummyTvShows() {
        ArrayList<WatchlistEntity> tvShows = new ArrayList<>();

        tvShows.add(new WatchlistEntity(
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
        ));
        tvShows.add(new WatchlistEntity(
                131927,
                "/9EBKgrFIsCFSV1RZKWhYUdbtGiv.jpg",
                "/3STIpPmCd0qCl2DfeKxdM3NDK8e.jpg",
                "Dexter: New Blood",
                "Dexter: New Blood",
                TYPE_SHOW,
                "Drama, Crime",
                "10 years after Dexter went missing in the eye of Hurricane Laura, we find him living under an assumed name in the small town of Iron Lake, New York.  Dexter may be embracing his new life, but in the wake of unexpected events in this close-knit community, his Dark Passenger beckons.",
                "2021 ",
                "93%"
        ));
        tvShows.add(new WatchlistEntity(
                94605,
                "/fqldf2t8ztc9aiwn3k6mlX3tvRT.jpg",
                "/rkB4LyZHo1NHXFEDHl9vSD9r1lI.jpg",
                "Arcane",
                "Arcane",
                TYPE_SHOW,
                "Action & Adventure, Mystery, Drama",
                "Amid the stark discord of twin cities Piltover and Zaun, two sisters fight on rival sides of a war between magic technologies and clashing convictions.",
                "2021 ",
                "92%"
        ));

        return tvShows;
    }

    public static ArrayList<WatchlistResponse> getRemoteDummyMovies() {
        ArrayList<WatchlistResponse> movies = new ArrayList<>();

        movies.add(new WatchlistResponse(
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
        ));
        movies.add(new WatchlistResponse(
                438631,
                "/d5NXSklXo0qyIYkgV94XAgMIckC.jpg",
                "/eeijXm3553xvuFbkPFkDG6CLCbQ.jpg",
                "Dune",
                "Dune",
                TYPE_MOVIE,
                "Action, Adventure, Science Fiction",
                "Paul Atreides, a brilliant and gifted young man born into a great destiny beyond his understanding, must travel to the most dangerous planet in the universe to ensure the future of his family and his people. As malevolent forces explode into conflict over the planet's exclusive supply of the most precious resource in existence-a commodity capable of unlocking humanity's greatest potential-only those who can conquer their fear will survive.",
                "2021 ",
                "80%"
        ));
        movies.add(new WatchlistResponse(
                580489,
                "/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg",
                "/lNyLSOKMMeUPr1RsL4KcRuIXwHt.jpg",
                "Venom: Let There Be Carnage",
                "Venom: Let There Be Carnage",
                TYPE_MOVIE,
                "Science Fiction, Action",
                "After finding a host body in investigative reporter Eddie Brock, the alien symbiote must face a new enemy, Carnage, the alter ego of serial killer Cletus Kasady.",
                "2021 ",
                "68%"
        ));

        return movies;
    }

    public static ArrayList<WatchlistResponse> getRemoteDummyTvShows() {
        ArrayList<WatchlistResponse> tvShows = new ArrayList<>();

        tvShows.add(new WatchlistResponse(
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
        ));
        tvShows.add(new WatchlistResponse(
                131927,
                "/9EBKgrFIsCFSV1RZKWhYUdbtGiv.jpg",
                "/3STIpPmCd0qCl2DfeKxdM3NDK8e.jpg",
                "Dexter: New Blood",
                "Dexter: New Blood",
                TYPE_SHOW,
                "Drama, Crime",
                "10 years after Dexter went missing in the eye of Hurricane Laura, we find him living under an assumed name in the small town of Iron Lake, New York.  Dexter may be embracing his new life, but in the wake of unexpected events in this close-knit community, his Dark Passenger beckons.",
                "2021 ",
                "93%"
        ));
        tvShows.add(new WatchlistResponse(
                94605,
                "/fqldf2t8ztc9aiwn3k6mlX3tvRT.jpg",
                "/rkB4LyZHo1NHXFEDHl9vSD9r1lI.jpg",
                "Arcane",
                "Arcane",
                TYPE_SHOW,
                "Action & Adventure, Mystery, Drama",
                "Amid the stark discord of twin cities Piltover and Zaun, two sisters fight on rival sides of a war between magic technologies and clashing convictions.",
                "2021 ",
                "92%"
        ));


        return tvShows;
    }
}
