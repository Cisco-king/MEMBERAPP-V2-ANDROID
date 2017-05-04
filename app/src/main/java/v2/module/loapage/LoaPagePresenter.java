package v2.module.loapage;

import android.util.Log;

import com.medicard.member.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import constants.OutPatientConsultationForm;
import model.DoctorNORoom;
import model.GetUSER;
import model.MemberInfo;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import services.AppInterface;
import services.AppService;
import timber.log.Timber;
import utilities.PdfGenerator;

/**
 * Created by John Paul Cas on 4/18/2017.
 */

public class LoaPagePresenter
        implements LoaPage.Presenter<LoaPage.View> {

    public static final String TAG = LoaPagePresenter.class.getSimpleName();

    private LoaPage.View loaPageView;

    public LoaPagePresenter() {}

    @Override
    public void attachView(LoaPage.View view) {
        loaPageView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void initUserInformation(String id) {
        AppInterface appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.getMemberInfo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetUSER>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: User Information");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                        loaPageView.onNetworkError();
                    }

                    @Override
                    public void onNext(GetUSER getUSER) {
                        initRemarks(getUSER.getMemberInfo());
                        loaPageView.userInformation(getUSER);
                    }
                });
    }

    @Override
    public void requestDoctorByCode(final String doctorCode) {
        AppInterface appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.getDoctorData(doctorCode)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DoctorNORoom>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DoctorNORoom doctorNORoom) {
                        Log.d(TAG, "onNext: under requestDoctorCode");
                        loaPageView.displayDoctor(doctorNORoom.getDoctor());
                    }
                });
    }

    @Override
    public void generateLoaForm(final OutPatientConsultationForm outPatientConsultationForm, final InputStream stream) {
        Observable.create(new Observable.OnSubscribe<Boolean>() {

            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                try {
                    PdfGenerator.generatePdfLoaConsultationForm(
                            outPatientConsultationForm, stream);
                    subscriber.onNext(Boolean.TRUE);
                } catch (Exception e) {
                    Timber.d("error %s", e.toString());
                    subscriber.onNext(Boolean.FALSE);
                }
            }
        }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Boolean>() {
            @Override
            public void onCompleted() {
                Timber.d("completed process");
            }

            @Override
            public void onError(Throwable e) {
                Timber.d("error message %s", e.toString());
                loaPageView.onGenerateLoaFormError();
            }

            @Override
            public void onNext(Boolean success) {
                Timber.d("onNext");
                if (success) {
                    loaPageView.onGenerateLoaFormSuccess();
                } else {
                    loaPageView.onGenerateLoaFormError();
                }
            }
        });
    }

    private void initRemarks(MemberInfo memberInfo) {

        List<String> remarks = Arrays.asList(
                new String[]{
                        memberInfo.getID_REM(),
                        memberInfo.getID_REM2(),
                        memberInfo.getID_REM3(),
                        memberInfo.getID_REM4(),
                        memberInfo.getID_REM5(),
                        memberInfo.getID_REM6(),
                        memberInfo.getID_REM7()
                });

        Observable.from(remarks)
                .subscribeOn(Schedulers.newThread())
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String remarks) {
                        return !remarks.isEmpty();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String remark) {
                        loaPageView.displayRemarks(remark);
                    }
                });
                
    }
    
}
