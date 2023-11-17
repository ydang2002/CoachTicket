package com.example.coachticket.viewmodels;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coachticket.SharedPreferences.SharedPreferencesUtil;
import com.example.coachticket.api.ApiUtils;
import com.example.coachticket.api.ILoginService;
import com.example.coachticket.models.BookingSeat;
import com.example.coachticket.models.BookingSeatDetails;
import com.example.coachticket.models.Carriers;
import com.example.coachticket.models.Info;
import com.example.coachticket.models.Province;
import com.example.coachticket.models.ProvinceBookingSeat;
import com.example.coachticket.models.Routes;
import com.example.coachticket.models.RoutesBookingSeat;
import com.example.coachticket.models.Seat;
import com.example.coachticket.models.Trip;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoBookingSeatViewModel extends ViewModel {

    private MutableLiveData<Routes> routesLiveData = new MutableLiveData<>();
    private MutableLiveData<String> seatIds = new MutableLiveData<>("");
    private List<BookingSeatDetails> bookingSeatDetails = new ArrayList<>();
    private String locationOrigin = new MutableLiveData<>().toString();
    private String locationDestination = new MutableLiveData<>().toString();
    private String name = new MutableLiveData<>().toString();
    private String email = new MutableLiveData<>().toString();
    private String phone = new MutableLiveData<>().toString();
    private String id = new MutableLiveData<>().toString();
    private String token = new MutableLiveData<>().toString();
    private int price;
    private int size;
    private ILoginService iLoginService;
    private BookingSeat bookingSeat = new BookingSeat();
    private Context context;
    public ObservableInt btn = new ObservableInt(View.VISIBLE);
    public ObservableInt progressBar = new ObservableInt(View.GONE);
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private Routes routes = new Routes();

    public void

    setContext(Context context) {
        this.context = context;
    }
    public Context getContext() {
        return context;
    }

    public LiveData<Routes> getRoutesLiveData() {
        return routesLiveData;
    }

    public void setRoutes(Routes routes) {
        routesLiveData.setValue(routes);
    }

    public LiveData<String> getSeatIds() {
        return seatIds;
    }

    public String getLocationOrigin() {
        return locationOrigin;
    }

    public void setLocationOrigin(String locationOrigin) {
        this.locationOrigin = locationOrigin;
    }

    public String getLocationDestination() {
        return locationDestination;
    }

    public void setLocationDestination(String locationDestination) {
        this.locationDestination = locationDestination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    //    public InfoBookingSeatViewModel(Context context) {
//        this.context = (Activity) context;
//    }

    public void updateSeatIds(ArrayList<Seat> selectedSeats) {
        StringBuilder builder = new StringBuilder();
        for (Seat seat : selectedSeats) {
            builder.append(seat.getId()).append(", ");
        }
        if (builder.length() > 0) {
            builder.setLength(builder.length() - 2); // Xóa dấu phẩy cuối cùng
        }
        seatIds.setValue(builder.toString());
    }

    public void insertBookingSeat(/*BookingSeat bookingSeat Context context*/) {
//        bookingSeat = new BookingSeat();
        btn.set(View.GONE);
        progressBar.set(View.VISIBLE);
        updateSeat();
        bookingSeat.setCustomerId(getId());
        bookingSeat.setTotalSeats(getSize());
        bookingSeat.setTotalPrice(getPrice());
        bookingSeat.setRoutes(createRoute());
        bookingSeat.setInfo(createInfo());
        bookingSeat.setBookingSeatDetails(bookingSeatDetails);

        String token1 = SharedPreferencesUtil.getToken(context);
        iLoginService = ApiUtils.getApiService(token1);
        iLoginService.insertBookingSeat(bookingSeat).enqueue(new Callback<BookingSeat>() {
            @Override
            public void onResponse(Call<BookingSeat> call, Response<BookingSeat> response) {
                if (response.isSuccessful()) {
                    progressBar.set(View.GONE);
                    Toast.makeText(getContext(), "Đặt vé thành công", Toast.LENGTH_SHORT).show();
//                    btn.set(View.GONE);
                } else {
                    errorMessage.setValue("Error inserting booking seat: " + response.errorBody().toString());
                    Toast.makeText(getContext(), "Đặt vé không thành công"+ response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                    Log.d("ERRORInsert", response.errorBody().toString());
                }

            }

            @Override
            public void onFailure(Call<BookingSeat> call, Throwable t) {
                Toast.makeText(getContext(), "Đặt vé thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private RoutesBookingSeat createRoute() {
        RoutesBookingSeat route = new RoutesBookingSeat();

        //set origin
        ProvinceBookingSeat origin = new ProvinceBookingSeat();
        for (Province provinceOrigin : routesLiveData.getValue().getOrigin()) {
            String nameProvinces = provinceOrigin.getNameProvinces();
            origin.setNameProvinces(nameProvinces);
        }
        origin.setLocations(getLocationOrigin());
        route.setOrigin(origin);

//        //set destination
        ProvinceBookingSeat destination = new ProvinceBookingSeat();
        for (Province provinceDestination : routesLiveData.getValue().getDestination()) {
            String nameProvinces2 = provinceDestination.getNameProvinces();
            destination.setNameProvinces(nameProvinces2);
        }
        destination.setLocations(getLocationDestination());
        route.setDestination(destination);

        //
        route.setDuration(routesLiveData.getValue().getDuration());
        route.setDistance(routesLiveData.getValue().getDistance());
        route.setPrice(routesLiveData.getValue().getPrice());

        //set trips
//        Trip trips = new Trip();
//        for (Trip trip : routesLiveData.getValue().getTrips()) {
//            String originTime = trip.getOriginTime();
//            String destinationTime = trip.getDestinationTime();
//            String originDate = trip.getOriginDate();
//            String destinationDate = trip.getDestinationDate();
//            trips.setOriginTime(originTime);
//            trips.setDestinationTime(destinationTime);
//            trips.setOriginDate(originDate);
//            trips.setDestinationDate(destinationDate);
//        }
//        route.setTrips(trips);

        Trip trips = new Trip();
            String originTime = routesLiveData.getValue().getTrips().getOriginTime();
            String destinationTime = routesLiveData.getValue().getTrips().getDestinationTime();
            String originDate = routesLiveData.getValue().getTrips().getOriginDate();
            String destinationDate = routesLiveData.getValue().getTrips().getDestinationDate();
            trips.setOriginTime(originTime);
            trips.setDestinationTime(destinationTime);
            trips.setOriginDate(originDate);
            trips.setDestinationDate(destinationDate);
        route.setTrips(trips);

        //set carriers
        Carriers carrier = new Carriers();
        for (Carriers carriers : routesLiveData.getValue().getCarriers()) {
            String nameCarriers = carriers.getName();
            carrier.setName(nameCarriers);
        }
        route.setCarriers(carrier);

        return route;
    }

    private Info createInfo() {
        Info info = new Info();
        info.setName(getName());
        info.setEmail(getEmail());
        info.setPhone(getPhone());
        return info;
    }

    public void createBookingSeatDetails(List<Seat> seats) {
        // Tạo một list mới để lưu trữ các đối tượng BookingSeatDetails
//        List<BookingSeatDetails> bookingSeatDetails = new ArrayList<>();
        // Duyệt qua danh sách các đối tượng Seat
        for (Seat seat : seats) {
            // Tạo một đối tượng BookingSeatDetails mới với id của ghế
            BookingSeatDetails bookingSeatDetailsItem = new BookingSeatDetails(seat.getId());
            // Thêm đối tượng BookingSeatDetails mới vào list
            bookingSeatDetails.add(bookingSeatDetailsItem);
        }
        // Trả về danh sách BookingSeatDetails
//        return bookingSeatDetails;
    }

    public void updateSeat() {

        Trip trips = new Trip();
        String originTime = routesLiveData.getValue().getTrips().getOriginTime();
        String destinationTime = routesLiveData.getValue().getTrips().getDestinationTime();
        String originDate = routesLiveData.getValue().getTrips().getOriginDate();
        String destinationDate = routesLiveData.getValue().getTrips().getDestinationDate();
        List<Seat> seats = routesLiveData.getValue().getTrips().getSeats();
        int availableSeats = routesLiveData.getValue().getTrips().getAvailableSeats() - getSize();
        trips.setOriginTime(originTime);
        trips.setDestinationTime(destinationTime);
        trips.setOriginDate(originDate);
        trips.setDestinationDate(destinationDate);
        trips.setSeats(seats);
        trips.setAvailableSeats(availableSeats);

        routes.setTrips(trips);
        routes.setId(routesLiveData.getValue().get_id());
        routes.setPrice(routesLiveData.getValue().getPrice());

        String token1 = SharedPreferencesUtil.getToken(context);
        iLoginService = ApiUtils.getApiService(token1);
        iLoginService.updateSeat(routes).enqueue(new Callback<Routes>() {
            @Override
            public void onResponse(Call<Routes> call, Response<Routes> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(getContext(), "update thành công", Toast.LENGTH_SHORT).show();
                } else {
                    errorMessage.setValue("Error update booking seat: " + response.errorBody().toString());
//                    Toast.makeText(getContext(), "update không thành công"+ response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                    Log.d("ERRORUpdate", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Routes> call, Throwable t) {
                Toast.makeText(getContext(), "update thất bại", Toast.LENGTH_SHORT).show();
            }
        });

    }

//    // Phương thức để cập nhật statusSeat
//    public void updateStatusSeats(ArrayList<Seat> selectedSeats) {
//        Routes routes = routesLiveData.getValue();
//        if (routes != null) {
//            for (Trip trip : routes.getTrips()) {
//                for (Seat seat : trip.getSeats()) {
//                    for (Seat selectedSeat : selectedSeats) {
//                        if (seat.getId().equals(selectedSeat.getId())) {
//                            seat.setStatusSeat(selectedSeat.isStatusSeat());
//                        }
//                    }
//                }
//            }
//            routesLiveData.setValue(routes);
//        }
//    }
}
