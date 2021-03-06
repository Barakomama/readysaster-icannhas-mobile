package com.icannhas.readysaster;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;

import com.icannhas.readysaster.Data;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table DATA.
*/
public class DataDao extends AbstractDao<Data, Long> {

    public static final String TABLENAME = "DATA";

    /**
     * Properties of entity Data.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Personal_id = new Property(1, Long.class, "personal_id", false, "PERSONAL_ID");
        public final static Property Location_id = new Property(2, Long.class, "location_id", false, "LOCATION_ID");
        public final static Property Structure_id = new Property(3, Long.class, "structure_id", false, "STRUCTURE_ID");
    };

    private DaoSession daoSession;


    public DataDao(DaoConfig config) {
        super(config);
    }
    
    public DataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'DATA' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'PERSONAL_ID' INTEGER," + // 1: personal_id
                "'LOCATION_ID' INTEGER," + // 2: location_id
                "'STRUCTURE_ID' INTEGER);"); // 3: structure_id
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'DATA'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Data entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long personal_id = entity.getPersonal_id();
        if (personal_id != null) {
            stmt.bindLong(2, personal_id);
        }
 
        Long location_id = entity.getLocation_id();
        if (location_id != null) {
            stmt.bindLong(3, location_id);
        }
 
        Long structure_id = entity.getStructure_id();
        if (structure_id != null) {
            stmt.bindLong(4, structure_id);
        }
    }

    @Override
    protected void attachEntity(Data entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Data readEntity(Cursor cursor, int offset) {
        Data entity = new Data( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // personal_id
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // location_id
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3) // structure_id
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Data entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPersonal_id(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setLocation_id(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setStructure_id(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Data entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Data entity) {
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
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getPersonalDetailsDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getLocationDetailsDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T2", daoSession.getStructureDetailsDao().getAllColumns());
            builder.append(" FROM DATA T");
            builder.append(" LEFT JOIN PERSONAL_DETAILS T0 ON T.'PERSONAL_ID'=T0.'_id'");
            builder.append(" LEFT JOIN LOCATION_DETAILS T1 ON T.'LOCATION_ID'=T1.'_id'");
            builder.append(" LEFT JOIN STRUCTURE_DETAILS T2 ON T.'STRUCTURE_ID'=T2.'_id'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Data loadCurrentDeep(Cursor cursor, boolean lock) {
        Data entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        PersonalDetails personalDetails = loadCurrentOther(daoSession.getPersonalDetailsDao(), cursor, offset);
        entity.setPersonalDetails(personalDetails);
        offset += daoSession.getPersonalDetailsDao().getAllColumns().length;

        LocationDetails locationDetails = loadCurrentOther(daoSession.getLocationDetailsDao(), cursor, offset);
        entity.setLocationDetails(locationDetails);
        offset += daoSession.getLocationDetailsDao().getAllColumns().length;

        StructureDetails structureDetails = loadCurrentOther(daoSession.getStructureDetailsDao(), cursor, offset);
        entity.setStructureDetails(structureDetails);

        return entity;    
    }

    public Data loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Data> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Data> list = new ArrayList<Data>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Data> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Data> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
