package com.icannhas.readysaster;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.icannhas.readysaster.LocationDetails;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table LOCATION_DETAILS.
*/
public class LocationDetailsDao extends AbstractDao<LocationDetails, Long> {

    public static final String TABLENAME = "LOCATION_DETAILS";

    /**
     * Properties of entity LocationDetails.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Lat = new Property(1, Double.class, "lat", false, "LAT");
        public final static Property Lng = new Property(2, Double.class, "lng", false, "LNG");
        public final static Property PhotoFile = new Property(3, String.class, "photoFile", false, "PHOTO_FILE");
    };


    public LocationDetailsDao(DaoConfig config) {
        super(config);
    }
    
    public LocationDetailsDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'LOCATION_DETAILS' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'LAT' REAL," + // 1: lat
                "'LNG' REAL," + // 2: lng
                "'PHOTO_FILE' TEXT);"); // 3: photoFile
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'LOCATION_DETAILS'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, LocationDetails entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Double lat = entity.getLat();
        if (lat != null) {
            stmt.bindDouble(2, lat);
        }
 
        Double lng = entity.getLng();
        if (lng != null) {
            stmt.bindDouble(3, lng);
        }
 
        String photoFile = entity.getPhotoFile();
        if (photoFile != null) {
            stmt.bindString(4, photoFile);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public LocationDetails readEntity(Cursor cursor, int offset) {
        LocationDetails entity = new LocationDetails( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getDouble(offset + 1), // lat
            cursor.isNull(offset + 2) ? null : cursor.getDouble(offset + 2), // lng
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // photoFile
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, LocationDetails entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setLat(cursor.isNull(offset + 1) ? null : cursor.getDouble(offset + 1));
        entity.setLng(cursor.isNull(offset + 2) ? null : cursor.getDouble(offset + 2));
        entity.setPhotoFile(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(LocationDetails entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(LocationDetails entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
