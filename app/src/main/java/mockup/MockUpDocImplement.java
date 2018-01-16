package mockup;

import android.content.Context;

import model.Doctors;
import model.LogIn;
import utilities.PhoneInformations;

/**
 * Created by IPC on 1/16/2018.
 */

public class MockUpDocImplement {
    private Context context;

    public MockUpDocImplement(Context context) {
        this.context = context;
    }

    public void loadFirst(String hospitalCode, String count, String offset, String searchString, final MockUpDocImplement.MockUpCallback callback) {
        MockUpAPI.getFirstLoad(hospitalCode,count,offset,searchString,callback);
    }

    public void loadMore(String hospitalCode,String count,String offset,String searchString, final MockUpDocImplement.MockUpCallback callback) {
        MockUpAPI.getDoctorsToHospitalPaginated(hospitalCode,count,offset,searchString,callback);
    }


    public interface MockUpCallback {
        void onSuccessFirstLoad(Doctors body);

        void onSuccessLoadMore(Doctors body);

        void onError(String message);
    }
}
