package v2.module.loapage;

import model.Doctor;
import model.GetUSER;

/**
 * Created by John Paul Cas on 4/18/2017.
 */

public interface LoaPage {

    interface View {
        /**
         *
         * @param getUSER
         * The Member Information
         */
        void userInformation(GetUSER getUSER);
        void displayRemarks(String remarks);

        /**
         * Network Error
         */
        void onNetworkError();

        void displayDoctor(Doctor doctor);
    }

    interface Presenter<T> {

        /**
         *
         * @param view
         * The View who implement the {@link LoaPage.View}
         */
        void attachView(T view);

        /**
         * detach all view reference
         */
        void detachView();

        /**
         *
         * @param id
         * The Member Id
         */
        void initUserInformation(String id);

        void requestDoctorByCode(String doctorCode);
    }

}
