package Sqlite;

import java.util.ArrayList;

import model.Hospital;
import model.HospitalList;

/**
 * Created by mpx-pawpaw on 1/4/17.
 */

public class SetHospitalToDatabase {


    public static void setHospToDb(ArrayList<HospitalList> hosp, DatabaseHandler databaseH) {


        for (int x = 0; x < hosp.size(); x++) {

            databaseH.insertHospital(new HospitalList(
                            hosp.get(x).getPhoneNo(),
                            hosp.get(x).getRegion(),
                            hosp.get(x).getStreetAddress(),
                            hosp.get(x).getCategory(),
                            hosp.get(x).getFaxno(),
                            hosp.get(x).getAlias(),
                            hosp.get(x).getKeyword(),
                            hosp.get(x).getProvince(),
                            hosp.get(x).getHospitalName(),
                            hosp.get(x).getHospitalCode(),
                            hosp.get(x).getCoordinator(),
                            hosp.get(x).getContactPerson(),
                            hosp.get(x).getCity()

                    )
            );


        }


    }
}
