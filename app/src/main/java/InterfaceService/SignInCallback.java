package InterfaceService;

import model.City;
import model.Province;
import model.SignInDetails;
import model.SpecializationList;

/**
 * Created by mpx-pawpaw on 1/24/17.
 */

public interface SignInCallback {

    void onSuccessProvince(Province province);
    void onErrorProvince(String message);
    void onSuccessCity(City city);
    void onErrorCity(String message);

    void onProvincetoDbListener();
    void onCitytoDbListener();

    void onErrorSpecs(String message);

    void onSuccessSpecs(SpecializationList specializationList);

    void onLoadProcedureSuccess(SignInDetails signInDetails);
    void onLoadProcedureError(String message);


    void onSpecsToDBListener();
}
