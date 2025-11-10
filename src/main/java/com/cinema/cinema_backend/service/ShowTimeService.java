package com.cinema.cinema_backend.service;

import com.cinema.cinema_backend.dto.ShowTimeUpdateRequest;
import com.cinema.cinema_backend.model.Hall;
import com.cinema.cinema_backend.model.Seat;
import com.cinema.cinema_backend.model.ShowTime;
import com.cinema.cinema_backend.model.Film;
import com.cinema.cinema_backend.repository.HallRepository;
import com.cinema.cinema_backend.repository.SeatRepository;
import com.cinema.cinema_backend.repository.ShowTimeRepository;
import com.cinema.cinema_backend.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShowTimeService {

    private ShowTimeRepository showTimeRepository;
    private FilmRepository filmRepository;
    private HallRepository hallRepository;
    private SeatRepository seatRepository;

    public ShowTimeService(ShowTimeRepository showTimeRepository, FilmRepository filmRepository, HallRepository hallRepository, SeatRepository seatRepository){
        this.showTimeRepository = showTimeRepository;
        this.filmRepository = filmRepository;
        this.hallRepository = hallRepository;
        this.seatRepository = seatRepository;
    }

    public ShowTime save(ShowTime showTime){
        return showTimeRepository.save(showTime);
    }

    public List<ShowTime> findAll(){
        return showTimeRepository.findAll();
    }

    public Optional<ShowTime> findById(Long id){
        return showTimeRepository.findById(id);
    }

    public ShowTime updateShowTime(Long id, ShowTimeUpdateRequest request) {
        ShowTime foundShowTime = showTimeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ShowTime not found with id: " + id));

        // Hall 更新
        request.getHallId().ifPresent(hallId -> {
            Hall hall = hallRepository.findById(hallId)
                    .orElseThrow(() -> new NoSuchElementException("Hall not found with id: " + hallId));
            foundShowTime.setHall(hall);
        });

        // Film 更新
        request.getFilmId().ifPresent(filmId -> {
            Film film = filmRepository.findById(filmId)
                    .orElseThrow(() -> new NoSuchElementException("Film not found with id: " + filmId));
            foundShowTime.setFilm(film);
        });

        // 时间更新
        request.getStartTime().ifPresent(foundShowTime::setStartTime);
        request.getEndTime().ifPresent(foundShowTime::setEndTime);


        return showTimeRepository.save(foundShowTime);
    }

    public boolean deleteById(Long id) {
        if (showTimeRepository.existsById(id)) {
            showTimeRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
