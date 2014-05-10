package com.icannhas.readysaster;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.icannhas.readysaster.Structure;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table STRUCTURE.
*/
public class StructureDao extends AbstractDao<Structure, Long> {

    public static final String TABLENAME = "STRUCTURE";

    /**
     * Properties of entity Structure.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property House_size = new Property(1, Integer.class, "house_size", false, "HOUSE_SIZE");
        public final static Property Building_material = new Property(2, String.class, "building_material", false, "BUILDING_MATERIAL");
        public final static Property Structure_type = new Property(3, String.class, "structure_type", false, "STRUCTURE_TYPE");
        public final static Property Number_storey = new Property(4, Integer.class, "number_storey", false, "NUMBER_STOREY");
    };


    public StructureDao(DaoConfig config) {
        super(config);
    }
    
    public StructureDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'STRUCTURE' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'HOUSE_SIZE' INTEGER," + // 1: house_size
                "'BUILDING_MATERIAL' TEXT," + // 2: building_material
                "'STRUCTURE_TYPE' TEXT," + // 3: structure_type
                "'NUMBER_STOREY' INTEGER);"); // 4: number_storey
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'STRUCTURE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Structure entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer house_size = entity.getHouse_size();
        if (house_size != null) {
            stmt.bindLong(2, house_size);
        }
 
        String building_material = entity.getBuilding_material();
        if (building_material != null) {
            stmt.bindString(3, building_material);
        }
 
        String structure_type = entity.getStructure_type();
        if (structure_type != null) {
            stmt.bindString(4, structure_type);
        }
 
        Integer number_storey = entity.getNumber_storey();
        if (number_storey != null) {
            stmt.bindLong(5, number_storey);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Structure readEntity(Cursor cursor, int offset) {
        Structure entity = new Structure( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // house_size
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // building_material
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // structure_type
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4) // number_storey
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Structure entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setHouse_size(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setBuilding_material(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setStructure_type(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setNumber_storey(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Structure entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Structure entity) {
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
