package modules.base;

/**
 * <p>
 *     Base MVP
 * </p>
 *
 * @author John Paul Cas
 * @since 4/11/2017
 */
public interface Mvp {

    interface View {

    }

    interface Presenter<T extends Mvp.View> {
        void attachView(T view);
        void detachView();
        void attachCallback();
        void detachCallback();
    }

}
