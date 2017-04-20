package database.table;

/**
 * <p>
 *     The table name and fields
 * </p>
 *
 * @author John Paul Cas
 */
public interface Table {

    interface Specialization {

        String TABLE_NAME = "SPECIALIZATION";

        String CODE = "specializationCode";

        String DESCRIPTION = "specializationDescription";

        String COLUMNS[] = {CODE, DESCRIPTION};

    }

}
