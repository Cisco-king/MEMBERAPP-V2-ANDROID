package InterfaceService;

/**
 * Created by mpx-pawpaw on 2/20/17.
 */

public interface SortLoaReqCallback {
    void onSortListener(String string);

    void onSortStatus(String string);

    void onSortServiceType(String string);

    void datePickerEndDateError();

    void datePickerStartDateError();
}
