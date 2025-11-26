package com.cinema.cinema_backend.service;

import com.cinema.cinema_backend.dto.ShowTimeCreateRequest;
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

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ShowTimeService {

    private final ShowTimeRepository showTimeRepository;
    private final FilmRepository filmRepository;
    private final HallRepository hallRepository;

    public ShowTimeService(ShowTimeRepository showTimeRepository, FilmRepository filmRepository, HallRepository hallRepository){
        this.showTimeRepository = showTimeRepository;
        this.filmRepository = filmRepository;
        this.hallRepository = hallRepository;
    }

    public ShowTime createShowTime(Long filmId, ShowTimeCreateRequest request) {
        Hall hall = hallRepository.findById(request.getHallId())
                .orElseThrow(() -> new RuntimeException("Hall not found"));
        System.out.println("Film id " + filmId);
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new RuntimeException("Film not found"));

        LocalDateTime startTime = request.getStartTime();

        LocalDateTime endTime = startTime.plusMinutes(film.getLength());

        boolean hasConflict = showTimeRepository
                .existsByHallIdAndStartTimeLessThanAndEndTimeGreaterThan(
                        hall.getId(),
                        endTime,
                        startTime
                );

        if (hasConflict) {
            throw new RuntimeException("ShowTime time conflict in this hall");
        }

        ShowTime showTime = new ShowTime();
        showTime.setHall(hall);
        showTime.setFilm(film);
        showTime.setStartTime(request.getStartTime());
        showTime.setPrice(request.getPrice());
        showTime.setEndTime(endTime);
        Set<Seat> seats = new HashSet<>(hall.getSeats());
        showTime.setSeats(seats);

        return showTimeRepository.save(showTime);
    }

    public List<ShowTime> findAll(){
        return showTimeRepository.findAll();
    }

    public List<ShowTime> findByFilmId(Long filmId) {
        return showTimeRepository.findAllByFilmId(filmId);
    }

    public Optional<ShowTime> findById(Long id){
        return showTimeRepository.findById(id);
    }

    public ShowTime updateShowTime(Long id, ShowTimeUpdateRequest request) {
        ShowTime foundShowTime = showTimeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ShowTime not found with id: " + id));

        // hallId 不为空时更新
        if (request.getHallId() != null) {
            Hall hall = hallRepository.findById(request.getHallId())
                    .orElseThrow(() -> new NoSuchElementException("Hall not found with id: " + request.getHallId()));
            foundShowTime.setHall(hall);
        }

        // filmId 不为空时更新
        if (request.getFilmId() != null) {
            Film film = filmRepository.findById(request.getFilmId())
                    .orElseThrow(() -> new NoSuchElementException("Film not found with id: " + request.getFilmId()));
            foundShowTime.setFilm(film);
        }

        boolean hasConflict = showTimeRepository
                .existsByHallIdAndStartTimeLessThanAndEndTimeGreaterThan(
                        foundShowTime.getHall().getId(),
                        foundShowTime.getEndTime(),
                        foundShowTime.getStartTime()
                );

        if (hasConflict) {
            throw new RuntimeException("ShowTime time conflict in this hall");
        }

        // startTime / endTime / price 更新
        if (request.getStartTime() != null) foundShowTime.setStartTime(request.getStartTime());
        if (request.getEndTime() != null) foundShowTime.setEndTime(request.getEndTime());
        if (request.getPrice() != null) foundShowTime.setPrice(request.getPrice());

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
