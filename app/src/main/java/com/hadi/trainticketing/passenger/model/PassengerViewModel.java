package com.hadi.trainticketing.passenger.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hadi.trainticketing.passenger.data.PassengerRepo;
import com.hadi.trainticketing.passenger.model.pojo.enquire.ResultArray;
import com.hadi.trainticketing.passenger.model.pojo.enquire.TicketModel;
import com.hadi.trainticketing.passenger.model.pojo.login.SignInFields;
import com.hadi.trainticketing.passenger.model.pojo.login.SignInResponse;
import com.hadi.trainticketing.passenger.model.pojo.profile.UserResponse;
import com.hadi.trainticketing.passenger.model.pojo.reservation.request.ReservationRequest;
import com.hadi.trainticketing.passenger.model.pojo.reservation.response.reservation.Reservation;
import com.hadi.trainticketing.passenger.model.pojo.reservation.response.reserve_seat.ReservationResponse;
import com.hadi.trainticketing.passenger.model.pojo.signup.SignUpFields;
import com.hadi.trainticketing.passenger.model.pojo.signup.SignUpResponse;
import com.hadi.trainticketing.passenger.model.pojo.stations.Station;

import java.util.List;

public class PassengerViewModel extends ViewModel {
    private PassengerRepo passengerRepo;
    private LiveData<List<Station>> stations;
    private LiveData<UserResponse> userInfo;
    private LiveData<List<TicketModel>> ticketsHistory;

    public PassengerViewModel() {
        passengerRepo = new PassengerRepo();
    }

    public LiveData<SignInResponse> loginWithCredentials(SignInFields signInFields) {
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

    public LiveData<List<TicketModel>> getTicketsHistory() {
        return ticketsHistory;
    }
}
