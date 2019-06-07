package com.hadi.trainticketing.passenger.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hadi.trainticketing.datasource.webservice.WebServices;
import com.hadi.trainticketing.passenger.model.pojo.enquire.EnquireResponse;
import com.hadi.trainticketing.passenger.model.pojo.enquire.ResultArray;
import com.hadi.trainticketing.passenger.model.pojo.login.SignInFields;
import com.hadi.trainticketing.passenger.model.pojo.login.SignInResponse;
import com.hadi.trainticketing.passenger.model.pojo.profile.UserResponse;
import com.hadi.trainticketing.passenger.model.pojo.reservation.request.ReservationRequest;
import com.hadi.trainticketing.passenger.model.pojo.reservation.response.reservation.Reservation;
import com.hadi.trainticketing.passenger.model.pojo.reservation.response.reservation.ReservationResult;
import com.hadi.trainticketing.passenger.model.pojo.reservation.response.reserve_seat.ReservationResponse;
import com.hadi.trainticketing.passenger.model.pojo.signup.SignUpFields;
import com.hadi.trainticketing.passenger.model.pojo.signup.SignUpResponse;
import com.hadi.trainticketing.passenger.model.pojo.stations.Station;
import com.hadi.trainticketing.passenger.model.pojo.stations.StationsResponse;
import com.hadi.trainticketing.passenger.model.pojo.ticket.Ticket;
import com.hadi.trainticketing.passenger.model.pojo.ticket.TicketHistoryModel;
import com.hadi.trainticketing.passenger.model.pojo.ticket.TicketHistoryResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerRepo {
    private static final String TAG = "PassengerRepo";
    private MutableLiveData<SignInResponse> signInResponse;
    private MutableLiveData<SignUpResponse> signUpResponse;
    private MutableLiveData<List<ResultArray>> tickets;
    private MutableLiveData<List<Station>> stations;
    private MutableLiveData<ReservationResponse> seats;
    private MutableLiveData<UserResponse> profileInfo;
    private MutableLiveData<Reservation> reservationResult;
    private MutableLiveData<List<TicketHistoryModel>> ticketsHistory;

    private WebServices webServices = WebServices.serverConnection.create(WebServices.class);

    public LiveData<SignInResponse> login(SignInFields signInFields) {
        if (signInResponse == null) {
            signInResponse = new MutableLiveData<>();
        }
        webServices.getLoginResponse(signInFields)
                .enqueue(new Callback<SignInResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<SignInResponse> call, @NonNull Response<SignInResponse> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            signInResponse.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<SignInResponse> call, @NonNull Throwable t) {
                        signInResponse.setValue(null);
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });
        return signInResponse;
    }

    public LiveData<SignUpResponse> registerAccount(SignUpFields signUpFields) {
        if (signUpResponse == null) {
            signUpResponse = new MutableLiveData<>();
        }
        webServices.getSignUpResponse(signUpFields)
                .enqueue(new Callback<SignUpResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<SignUpResponse> call, @NonNull Response<SignUpResponse> response) {
                        if (response.body() != null) {
                            Log.d(TAG, "onResponse: account was created");
                            signUpResponse.setValue(response.body());
                        } else {
                            Log.d(TAG, "onResponse: account already exists");
                            signUpResponse.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<SignUpResponse> call, @NonNull Throwable t) {
                        signUpResponse.setValue(null);
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });
        return signUpResponse;
    }

    public LiveData<List<ResultArray>> getEnquireTickets(String from, String to, String date, String classType) {
        if (tickets == null) {
            tickets = new MutableLiveData<>();
        }

        webServices.getTripEnquire(from, to, date, classType).enqueue(new Callback<EnquireResponse>() {
            @Override
            public void onResponse(@NonNull Call<EnquireResponse> call, @NonNull Response<EnquireResponse> response) {
                if (response.body() != null && response.body().getResultArray() != null) {
                    tickets.setValue(response.body().getResultArray());
                }
            }

            @Override
            public void onFailure(@NonNull Call<EnquireResponse> call, @NonNull Throwable t) {
                tickets.setValue(null);
            }
        });

        return tickets;
    }

    public LiveData<List<Station>> getStations() {
        if (stations == null) {
            stations = new MutableLiveData<>();
        }
        webServices.getStations().enqueue(new Callback<StationsResponse>() {
            @Override
            public void onResponse(@NonNull Call<StationsResponse> call, @NonNull Response<StationsResponse> response) {
                if (response.body() != null) {
                    stations.setValue(response.body().getStationList());
                }
            }

            @Override
            public void onFailure(@NonNull Call<StationsResponse> call, @NonNull Throwable t) {
                stations.setValue(null);
            }
        });

        return stations;
    }

    public LiveData<ReservationResponse> getSeats(int classType, String trainId, String[] ids) {
        if (seats == null) {
            seats = new MutableLiveData<>();
        }
        webServices.getSeats(classType, trainId, ids).enqueue(new Callback<ReservationResponse>() {
            @Override
            public void onResponse(@NonNull Call<ReservationResponse> call, @NonNull Response<ReservationResponse> response) {
                if (response.body() != null) {
                    seats.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ReservationResponse> call, @NonNull Throwable t) {
                seats.setValue(null);
            }
        });
        return seats;
    }

    public LiveData<UserResponse> getUserInfo(String token) {
        if (profileInfo == null) {
            profileInfo = new MutableLiveData<>();
        }

        webServices.getProfileInfo("Bearer " + token)
                .enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                        if (response.body() != null) {
                            profileInfo.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
                        profileInfo.setValue(null);
                    }
                });
        return profileInfo;
    }

    public LiveData<Reservation> getReservationResult(ReservationRequest reservation) {
        if (reservationResult == null) {
            reservationResult = new MutableLiveData<>();
        }

        webServices.getReservationResult(reservation)
                .enqueue(new Callback<ReservationResult>() {
                    @Override
                    public void onResponse(@NonNull Call<ReservationResult> call, @NonNull Response<ReservationResult> response) {
                        if (response.body() != null && !response.body().getMessage().equals("Sorry Seat is taken")) {
                            reservationResult.setValue(response.body().getReservation());
                        } else {
                            reservationResult.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ReservationResult> call, @NonNull Throwable t) {
                        reservationResult.setValue(null);
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });
        return reservationResult;
    }

    public LiveData<List<TicketHistoryModel>> getTicketHistory(String uid) {
        if (ticketsHistory == null) {
            ticketsHistory = new MutableLiveData<>();
        }
        Log.d(TAG, "getTicketHistory: fetching tickets for user: " + webServices.getTicketHistory(uid).request().url());
        webServices.getTicketHistory(uid).enqueue(new Callback<TicketHistoryResponse>() {
            @Override
            public void onResponse(@NonNull Call<TicketHistoryResponse> call, @NonNull Response<TicketHistoryResponse> response) {
                if (response.body() != null) {
                    List<TicketHistoryModel> ticketModels = new ArrayList<>();
                    for (int i = 0; i < response.body().getReservation().size(); i++) {
                        boolean ticketValidation = response.body().getReservation().get(i).getValid();
                        String startTime = response.body().getReservation().get(i).getArrivalTime();
                        String arrivalTime = response.body().getReservation().get(i).getEndTime().substring(11, 16);
                        Ticket ticket = response.body().getReservation().get(i).getTicket();
                        String reservationId = response.body().getReservation().get(i).getId();

                        ticketModels.add(new TicketHistoryModel(ticketValidation, startTime, arrivalTime, ticket, reservationId));

                        Log.d(TAG, "start time" + startTime + " Arrival Time: " + arrivalTime);
                    }
                    ticketsHistory.setValue(ticketModels);
                } else {
                    Log.d(TAG, "onResponse: response was null");
                }
            }

            @Override
            public void onFailure(@NonNull Call<TicketHistoryResponse> call, @NonNull Throwable t) {
                ticketsHistory.setValue(null);
            }
        });
        return ticketsHistory;
    }
}
