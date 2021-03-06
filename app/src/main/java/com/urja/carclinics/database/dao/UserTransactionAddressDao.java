package com.urja.carclinics.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.urja.carclinics.database.UserTransactionAddress;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "USER_TRANSACTION_ADDRESS".
 */
public class UserTransactionAddressDao extends AbstractDao<UserTransactionAddress, Long> {

    public static final String TABLENAME = "USER_TRANSACTION_ADDRESS";

    public UserTransactionAddressDao(DaoConfig config) {
        super(config);
    }


    public UserTransactionAddressDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_TRANSACTION_ADDRESS\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"ADDRESS_LINE1\" TEXT," + // 1: addressLine1
                "\"ADDRESS_LINE2\" TEXT," + // 2: addressLine2
                "\"LANDMARK\" TEXT," + // 3: landmark
                "\"CITY\" TEXT," + // 4: city
                "\"STATE\" TEXT," + // 5: state
                "\"PIN\" TEXT," + // 6: pin
                "\"CAR_NUMBER\" TEXT," + // 7: carNumber
                "\"MOBILE_NUMBER\" TEXT);"); // 8: mobileNumber
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_TRANSACTION_ADDRESS\"";
        db.execSQL(sql);
    }

    /**
     * @inheritdoc
     */
    @Override
    protected void bindValues(SQLiteStatement stmt, UserTransactionAddress entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }

        String addressLine1 = entity.getAddressLine1();
        if (addressLine1 != null) {
            stmt.bindString(2, addressLine1);
        }

        String addressLine2 = entity.getAddressLine2();
        if (addressLine2 != null) {
            stmt.bindString(3, addressLine2);
        }

        String landmark = entity.getLandmark();
        if (landmark != null) {
            stmt.bindString(4, landmark);
        }

        String city = entity.getCity();
        if (city != null) {
            stmt.bindString(5, city);
        }

        String state = entity.getState();
        if (state != null) {
            stmt.bindString(6, state);
        }

        String pin = entity.getPin();
        if (pin != null) {
            stmt.bindString(7, pin);
        }

        String carNumber = entity.getCarNumber();
        if (carNumber != null) {
            stmt.bindString(8, carNumber);
        }

        String mobileNumber = entity.getMobileNumber();
        if (mobileNumber != null) {
            stmt.bindString(9, mobileNumber);
        }
    }

    /**
     * @inheritdoc
     */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    /**
     * @inheritdoc
     */
    @Override
    public UserTransactionAddress readEntity(Cursor cursor, int offset) {
        UserTransactionAddress entity = new UserTransactionAddress( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // addressLine1
                cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // addressLine2
                cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // landmark
                cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // city
                cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // state
                cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // pin
                cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // carNumber
                cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // mobileNumber
        );
        return entity;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void readEntity(Cursor cursor, UserTransactionAddress entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAddressLine1(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAddressLine2(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setLandmark(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCity(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setState(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setPin(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setCarNumber(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setMobileNumber(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
    }

    /**
     * @inheritdoc
     */
    @Override
    protected Long updateKeyAfterInsert(UserTransactionAddress entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    /**
     * @inheritdoc
     */
    @Override
    public Long getKey(UserTransactionAddress entity) {
        if (entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /**
     * @inheritdoc
     */
    @Override
    protected boolean isEntityUpdateable() {
        return true;
    }

    /**
     * Properties of entity UserTransactionAddress.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property AddressLine1 = new Property(1, String.class, "addressLine1", false, "ADDRESS_LINE1");
        public final static Property AddressLine2 = new Property(2, String.class, "addressLine2", false, "ADDRESS_LINE2");
        public final static Property Landmark = new Property(3, String.class, "landmark", false, "LANDMARK");
        public final static Property City = new Property(4, String.class, "city", false, "CITY");
        public final static Property State = new Property(5, String.class, "state", false, "STATE");
        public final static Property Pin = new Property(6, String.class, "pin", false, "PIN");
        public final static Property CarNumber = new Property(7, String.class, "carNumber", false, "CAR_NUMBER");
        public final static Property MobileNumber = new Property(8, String.class, "mobileNumber", false, "MOBILE_NUMBER");
    }

}
