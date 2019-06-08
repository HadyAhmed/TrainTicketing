package com.hadi.trainticketing.passenger.home.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hadi.trainticketing.passenger.home.data.PassengerRepo;
import com.hadi.trainticketing.passenger.home.model.pojo.enquire.ResultArray;
import com.hadi.trainticketing.passenger.home.model.pojo.profile.UserResponse;
import com.hadi.trainticketing.passenger.home.model.pojo.reservation.request.ReservationRequest;
import com.hadi.trainticketing.passenger.home.model.pojo.reservation.response.reservation.Reservation;
import com.hadi.trainticketing.passenger.home.model.pojo.reservation.response.reserve_seat.ReservationResponse;
import com.hadi.trainticketing.passenger.home.model.pojo.stations.Station;
import com.hadi.trainticketing.passenger.home.model.pojo.ticket.TicketHistoryModel;
import com.hadi.trainticketing.passenger.login.pojo.login.PassengerLoginResponse;
import com.hadi.trainticketing.passenger.login.pojo.login.SignInFields;
import com.hadi.trainticketing.passenger.login.pojo.signup.SignUpFields;
import com.hadi.trainticketing.passenger.login.pojo.signup.SignUpResponse;

import java.util.List;

public class PassengerViewModel extends ViewModel {
    private PassengerRepo passengerRepo;
    private LiveData<List<Station>> stations;
    private LiveData<UserResponse> userInfo;
    private LiveData<List<TicketHistoryModel>> ticketsHistory;

    public PassengerViewModel() {
        passengerRepo = new PassengerRepo();
    }

    public LiveData<PassengerLoginResponse> loginWithCredentials(SignInFields signInFields) {
        return passengerRepo.login(signInFields);
    }

    public void requestStations() {
        this.stations = passengerRepo.getStations();
    }

    public void requestUserInfo(String token) {
        this.userInfo = passengerRepo.getUserInfo(token);
    }

    public void requestTickets(String uid) {
        this.ticketsHistory = passengerRepo.getTicketHistory(uid);
    }

    public LiveData<SignUpResponse> registerNewAccount(SignUpFields signUpFields) {
        return passengerRepo.registerAccount(signUpFields);
    }

    public LiveData<List<ResultArray>> getEnquireSearchResult(String from, String to, String date, String classType) {
        return this.passengerRepo.getEnquireTickets(from, to, date, classType);
    }

    public LiveData<List<Station>> getStations() {
        return stations;
    }

    public LiveData<ReservationResponse> getReservationSeats(int classType, String trainId, String[] arrayResults) {
        return this.passengerRepo.getSeats(classType, trainId, arrayResults);
    }

    public LiveData<UserResponse> getUserInfo() {
        return userInfo;
    }

    public LiveData<Reservation> getReservationResult(ReservationRequest reservation) {
        return passengerRepo.getReservationResult(reservation);
    }

    public LiveData<List<TicketHistoryModel>> getTicketsHistory() {
        return ticketsHistory;
    }
}
