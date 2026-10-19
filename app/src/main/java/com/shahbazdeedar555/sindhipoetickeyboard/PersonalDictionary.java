package com.shahbazdeedar555.sindhipoetickeyboard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class PersonalDictionary extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "personal_dictionary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_WORDS = "learned_words";
    private static final String COL_ID = "id";
    private static final String COL_LANGUAGE = "language";
    private static final String COL_WORD = "word";
    private static final String COL_NORMALIZED = "normalized_word";
    private static final String COL_COUNT = "usage_count";
    private static final String COL_LAST_USED = "last_used";
    private static final String COL_CREATED = "created_at";

    public static final String ENGLISH = "en";
    public static final String SINDHI = "sd";
    public static final String URDU = "ur";

    public PersonalDictionary(Context context) {
        super(context.getApplicationContext(),
                DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_WORDS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_LANGUAGE + " TEXT NOT NULL, " +
                COL_WORD + " TEXT NOT NULL, " +
                COL_NORMALIZED + " TEXT NOT NULL, " +
                COL_COUNT + " INTEGER NOT NULL DEFAULT 1, " +
                COL_LAST_USED + " INTEGER NOT NULL, " +
                COL_CREATED + " INTEGER NOT NULL, " +
                "UNIQUE (" + COL_LANGUAGE + ", " +
                COL_NORMALIZED + "))");

        db.execSQL("CREATE INDEX idx_learned_words_lookup ON " +
                TABLE_WORDS + " (" + COL_LANGUAGE + ", " +
                COL_NORMALIZED + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
        // Keep learned words when adding future database features.
    }

    private boolean isValidLanguage(String language) {
        return ENGLISH.equals(language)
                || SINDHI.equals(language)
                || URDU.equals(language);
    }

    private String normalize(String word, String language) {
        String result = word.trim();

        if (ENGLISH.equals(language)) {
            result = result.toLowerCase(Locale.ROOT);
        }

        return result;
    }

    /**
     * Stores one completed word.
     * Call only for completed words and never for sensitive fields.
     */
    public synchronized void learnWord(String word, String language) {
        if (word == null || language == null
                || !isValidLanguage(language)) {
            return;
        }

        String cleaned = word.trim();

        if (cleaned.isEmpty() || cleaned.length() > 100
                || cleaned.matches(".*\\s+.*")) {
            return;
        }

        String normalized = normalize(cleaned, language);
        long now = System.currentTimeMillis();

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_LANGUAGE, language);
        values.put(COL_WORD, cleaned);
        values.put(COL_NORMALIZED, normalized);
        values.put(COL_COUNT, 1);
        values.put(COL_LAST_USED, now);
        values.put(COL_CREATED, now);

        long inserted = db.insertWithOnConflict(
                TABLE_WORDS, null, values,
                SQLiteDatabase.CONFLICT_IGNORE);

        if (inserted == -1) {
            ContentValues update = new ContentValues();
            update.put(COL_COUNT, COL_COUNT + " + 1");
            update.put(COL_LAST_USED, now);

            db.execSQL(
                    "UPDATE " + TABLE_WORDS +
                    " SET " + COL_COUNT + " = " + COL_COUNT +
                    " + 1, " + COL_LAST_USED + " = ?" +
                    " WHERE " + COL_LANGUAGE + " = ?" +
                    " AND " + COL_NORMALIZED + " = ?",
                    new Object[]{now, language, normalized});
        }
    }

    /**
     * Finds learned words beginning with the supplied prefix.
     */
    public synchronized List<String> getSuggestions(
            String prefix, String language, int limit) {

        List<String> results = new ArrayList<>();

        if (prefix == null || language == null
                || !isValidLanguage(language) || limit <= 0) {
            return results;
        }

        String cleanedPrefix = normalize(prefix, language);

        if (cleanedPrefix.isEmpty()) {
            return results;
        }

        String escapedPrefix = cleanedPrefix
                .replace("\\", "\\\\")
                .replace("%", "\\%")
                .replace("_", "\\_");

        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT " + COL_WORD +
                " FROM " + TABLE_WORDS +
                " WHERE " + COL_LANGUAGE + " = ?" +
                " AND " + COL_NORMALIZED + " LIKE ? ESCAPE '\\'" +
                " ORDER BY " + COL_COUNT + " DESC, " +
                COL_LAST_USED + " DESC LIMIT ?";

        try (Cursor cursor = db.rawQuery(
                sql,
                new String[]{
                        language,
                        escapedPrefix + "%",
                        String.valueOf(limit)
                })) {

            while (cursor.moveToNext()) {
                results.add(cursor.getString(0));
            }
        }

        return results;
    }

    public synchronized void clearLanguage(String language) {
        if (!isValidLanguage(language)) {
            return;
        }

        getWritableDatabase().delete(
                TABLE_WORDS, COL_LANGUAGE + " = ?",
                new String[]{language});
    }

    public synchronized int getWordCount(String language) {
        if (!isValidLanguage(language)) {
            return 0;
        }

        try (Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT COUNT(*) FROM " + TABLE_WORDS +
                        " WHERE " + COL_LANGUAGE + " = ?",
                new String[]{language})) {

            if (cursor.moveToFirst()) {
                return cursor.getInt(0);
            }
        }

        return 0;
    }
}
