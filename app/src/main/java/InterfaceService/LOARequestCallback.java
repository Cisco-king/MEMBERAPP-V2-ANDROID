package InterfaceService;

import java.util.ArrayList;

import model.Loa;
import model.LoaFetch;

/**
 * Created by mpx-pawpaw on 1/17/17.
 */

public interface LOARequestCallback {


    void onErrorLoaListener(String message);

    void onSuccessLoaListener(Loa loa);

    void onDbLoaSuccessListener();

    void gotoLoaPage(ArrayList<LoaFetch> arrayList, int adapterPosition);
}
