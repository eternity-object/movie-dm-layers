package org.eternity.domainmodel.movie.service;

import jakarta.transaction.Transactional;
import org.eternity.domainmodel.movie.domain.*;
import org.eternity.domainmodel.movie.persistence.*;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    private CustomerRepository customerRepository;
    private ScreeningRepository screeningRepository;
    private MovieRepository movieRepository;
    private ReservationRepository reservationRepository;

    public ReservationService(CustomerRepository customerRepository,
                              ScreeningRepository screeningRepository,
                              MovieRepository movieRepository,
                              ReservationRepository reservationRepository) {
        this.customerRepository = customerRepository;
        this.screeningRepository = screeningRepository;
        this.movieRepository = movieRepository;
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    public Reservation reserveScreening(Long customerId, Long screeningId, Integer audienceCount) {
        Customer customer = customerRepository.findById(customerId).get();
        Screening screening = screeningRepository.findById(screeningId).get();
        Movie movie = movieRepository.findById(screening.getId()).get();

        Reservation reservation = screening.reserve(movie, customer, audienceCount);

        reservationRepository.save(reservation);

        return reservation;
    }
}
